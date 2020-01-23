package com.ravindrabarthwal.superadapter.lib.implementations.plugins.exoplayerplugin

import android.content.Context
import android.graphics.Color
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class ExoPlayerPlugin(
    private val mediaResolver: MediaResolver,
    private val maxSimultaneousConnections: Int = 2,
    private val isLoopingEnabled: Boolean = true
) : SuperPlugin {

    private val exoPlaybacks: MutableList<ExoPlayerPlayback> = mutableListOf()
    private var isAppOnBackground: Boolean = false

    override fun apply(superAdapter: SuperAdapter<*, *>) {

    }

    fun release(playerView: PlayerView) {
        playerView.player?.let {
            exoPlaybacks.find { e -> e.player == it }?.let {
                val index = exoPlaybacks.indexOf(it)
                if (index > -1) exoPlaybacks.removeAt(index).release()
            }
        }
    }

    fun play(
        playerView: PlayerView,
        playbackId: Any,
        position: Int,
        link: String,
        onPlaybackStateChange: (Boolean, Int) -> Unit,
        onReleaseCallback: () -> Unit
    ): Boolean {
        exoPlaybacks.forEach {
            if (it.playbackId == playbackId) return true
        }

        if (exoPlaybacks.size == maxSimultaneousConnections) {
            exoPlaybacks.removeAt(0).release()
        }

        val playback = ExoPlayerPlayback.create(
            playbackId, position,
            link, playerView,
            mediaResolver, onPlaybackStateChange,
            onReleaseCallback
        ) {
            !isAppOnBackground
        } ?: return false

        exoPlaybacks.add(playback)
        return true
    }


    data class ExoPlayerPlayback(
        val playbackId: Any,
        val position: Int,
        val playerView: PlayerView,
        val player: ExoPlayer,
        val playerStateListener: Player.EventListener,
        val onReleaseCallback: () -> Unit
    ) {

        fun release() {
            player.release()
            playerView.player?.release()
            playerView.player = null
            onReleaseCallback()
        }


        companion object {
            private fun createPlayer(context: Context): ExoPlayer {
                return SimpleExoPlayer.Builder(context).build()
            }

            private fun createListener(onPlaybackStateChange: (Boolean, Int) -> Unit): Player.EventListener {
                return object : Player.EventListener {
                    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                        onPlaybackStateChange(playWhenReady, playbackState)
                    }
                }
            }

            fun create(
                playbackId: Any,
                position: Int,
                link: String,
                playerView: PlayerView,
                mediaResolver: MediaResolver,
                onPlaybackStateChange: (Boolean, Int) -> Unit,
                onReleaseCallback: () -> Unit,
                shouldPlayMedia: () -> Boolean
            ): ExoPlayerPlayback? {

                // Make Player and Set properties
                val player = createPlayer(playerView.context)
                player.playWhenReady = true
                playerView.player = player
                playerView.setShutterBackgroundColor(Color.TRANSPARENT)
                player.repeatMode = Player.REPEAT_MODE_ONE

                // Add Listener
                val listener = createListener(onPlaybackStateChange)
                player.addListener(listener)

                // Resolve Media
                mediaResolver.resolveMedia(playerView.context, link, player, shouldPlayMedia)

                return ExoPlayerPlayback(
                    playbackId, position, playerView, player, playerStateListener = listener,
                    onReleaseCallback = onReleaseCallback
                )
            }
        }
    }

}