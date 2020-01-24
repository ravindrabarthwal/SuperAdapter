package com.ravindrabarthwal.superadapter_exoplayer

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.MediaSource

interface MediaResolver {
    fun resolveMedia(context: Context, fileName: String, link: String, player: ExoPlayer, shouldPlayMedia: () -> Boolean)
    fun createMediaSourceFromUri(context: Context, uri: Uri): MediaSource
}