package com.ravindrabarthwal.superadapter.lib.implementations.plugins.exoplayerplugin

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.MediaSource

interface MediaResolver {
    fun resolveMedia(context: Context, link: String, player: ExoPlayer, shouldPlayMedia: () -> Boolean)
    fun createMediaSourceFromUri(context: Context, uri: Uri): MediaSource
}