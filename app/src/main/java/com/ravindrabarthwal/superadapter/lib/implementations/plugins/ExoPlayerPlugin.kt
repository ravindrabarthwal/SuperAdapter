package com.ravindrabarthwal.superadapter.lib.implementations.plugins

import android.content.Context
import android.graphics.Color
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ClippingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class ExoPlayerPlugin(
    private val maxSimultaneousConnections: Int = 2,
    private val isLoopingEnabled: Boolean = true
) : SuperPlugin {

    val exoPlaybacks: MutableList<ExoPlayerPlayback> = mutableListOf()

    override fun apply(superAdapter: SuperAdapter<*, *>) {

    }

    fun release(playerView: PlayerView) {
        playerView.player?.let {
            exoPlaybacks.find { e -> e.player == it }?.let {
                val index = exoPlaybacks.indexOf(it)
                if(index > -1) exoPlaybacks.removeAt(index).release()
            }
        }
    }

    fun play(playerView: PlayerView): Boolean {
        exoPlaybacks.forEach {
            if(it.playerView == playerView) return true
        }

        if(exoPlaybacks.size == maxSimultaneousConnections) {
            exoPlaybacks.removeAt(0).release()
        }

        val playback = ExoPlayerPlayback.create(playerView.context, playerView) ?: return false
        exoPlaybacks.add(playback)
        return true
    }

    data class ExoPlayerPlayback(val playerView: PlayerView, val player: ExoPlayer) {

        fun release() {
            player.release()
            playerView.player?.release()
            playerView.player = null
        }

        companion object {
            private fun createPlayer(context: Context): ExoPlayer {
                return SimpleExoPlayer.Builder(context).build()
            }

            fun create(
                context: Context,
                playerView: PlayerView
            ): ExoPlayerPlayback? {

                val videoLink = "https://mouve-template-image.sfo2.cdn.digitaloceanspaces.com/1080/0lFuO6MPqQ.mp4"
                val player = createPlayer(context)


                player.playWhenReady = true
                playerView.player = player
                playerView.setShutterBackgroundColor(Color.TRANSPARENT)

                player.repeatMode = Player.REPEAT_MODE_ONE
                val playback = ExoPlayerPlayback(playerView, player)

                val uri = Uri.parse(videoLink)
                createMediaSourceFromUri(uri, playerView.context, player, 10)

                return playback
            }

            private fun createMediaSourceFromUri(uri: Uri, context: Context, player: ExoPlayer, maxTimeToPlay: Long) {
//                val dataSpec = DataSpec(uri)
//                val fileDataSource = FileDataSource()
//                try {
//                    fileDataSource.open(dataSpec)
//                } catch (e: Exception) {
//                    Log.e("ExoPlayerPlugin", "Error", e)
//                }
                val mediaSource = ProgressiveMediaSource
                    .Factory(DefaultDataSourceFactory(context, "example"))
                    .createMediaSource(uri)
                val clippingMediaSource = ClippingMediaSource(mediaSource, maxTimeToPlay)
                player.prepare(mediaSource, true, false)
            }
        }
    }
}