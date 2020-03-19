package com.ravindrabarthwal.superadapter_exoplayer

import android.content.Context
import android.graphics.Color
import androidx.lifecycle.Lifecycle
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class ExoPlayerPlugin(
    private val mediaResolver: MediaResolver,
    private val maxSimultaneousConnections: Int = 2
) : SuperPlugin {

    private val exoPlaybacks: MutableList<ExoPlayerPlayback> = mutableListOf()
    private val exoPlayers: MutableList<ExoPlayer> = mutableListOf()

    var isAppOnBackground: Boolean = false

    override fun apply(superAdapter: SuperAdapter<*, *>) {

    }

    override fun recycle(superAdapter: SuperAdapter<*, *>) {
        super.recycle(superAdapter)
        exoPlaybacks.forEach {
            it.release()
        }
        exoPlaybacks.clear()
    }

    fun reset(playerView: PlayerView) {
        playerView.player?.let {
            exoPlaybacks.find { e -> e.player == it }?.let {
                val index = exoPlaybacks.indexOf(it)
                if (index > -1) {
                    val playback = exoPlaybacks.removeAt(index)
                    recycleExoPlayback(playback)
                }
            }
        }
    }

    private fun recycleExoPlayback(playback: ExoPlayerPlayback) {
        playback.reset()
        if(exoPlayers.indexOf(playback.player) == -1) {
            exoPlayers.add(playback.player)
        }
    }

    private fun getIdlePlayer(): ExoPlayer? {
        var player: ExoPlayer? = null
        exoPlayers.forEach {
            if(!it.isPlaying && !it.isLoading) {
                player = it
            }
        }
        if(player != null) {
            exoPlayers.remove(player!!)
        }
        return player
    }

    fun play(
        playerView: PlayerView,
        playbackId: Any,
        position: Int,
        fileName: String,
        link: String,
        onPlaybackStateChange: (Boolean, Int) -> Unit,
        onReleaseCallback: () -> Unit
    ): Player? {

        if(isAppOnBackground) return null

        exoPlaybacks.forEach {
            if (it.playbackId == playbackId) return null
        }

        if (exoPlaybacks.size == maxSimultaneousConnections) {
            recycleExoPlayback(exoPlaybacks.removeAt(0))
        }

        val playback = ExoPlayerPlayback.create(
            getIdlePlayer(),
            playbackId, position, fileName,
            link, playerView,
            mediaResolver,
            onPlaybackStateChange,
            onReleaseCallback
        ) {
            !isAppOnBackground
        } ?: return null

        exoPlaybacks.add(playback)
        return playback.player
    }


    data class ExoPlayerPlayback(
        val playbackId: Any,
        val position: Int,
        val playerView: PlayerView,
        val player: ExoPlayer,
        val playerStateListener: Player.EventListener,
        val onReleaseCallback: () -> Unit
    ) {

        fun reset() {
            playerView.player = null
            player.removeListener(playerStateListener)
            player.stop()
            onReleaseCallback()
        }

        fun release() {
            reset()
            player.release()
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
                player: ExoPlayer?,
                playbackId: Any,
                position: Int,
                fileName: String,
                link: String,
                playerView: PlayerView,
                mediaResolver: MediaResolver,
                onPlaybackStateChange: (Boolean, Int) -> Unit,
                onReleaseCallback: () -> Unit,
                shouldPlayMedia: () -> Boolean
            ): ExoPlayerPlayback? {

                // Make Player and Set properties
                val player = player ?: createPlayer(playerView.context)
                playerView.player = player
//                playerView.setShutterBackgroundColor(Color.TRANSPARENT)
//                player.repeatMode =  Player.REPEAT_MODE_ONE

                // Add Listener
                val listener = createListener(onPlaybackStateChange)
                player.addListener(listener)

                // Resolve Media
                mediaResolver.resolveMedia(playerView.context, fileName, link, player, shouldPlayMedia)
                player.playWhenReady = true

                return ExoPlayerPlayback(
                    playbackId, position, playerView, player, playerStateListener = listener,
                    onReleaseCallback = onReleaseCallback
                )
            }
        }
    }

}