package com.ravindrabarthwal.superadapter.lib.implementations.viewholders

import android.view.View
import com.google.android.exoplayer2.ui.PlayerView
import com.ravindrabarthwal.superadapter.R
import com.ravindrabarthwal.superadapter.lib.implementations.items.ExoPlayerItem
import com.ravindrabarthwal.superadapter.lib.implementations.plugins.ExoPlayerPlugin
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperViewHolder

class ExoPlayerViewHolder(itemView: View) : SuperViewHolder<ExoPlayerItem>(itemView) {

    val playerView: PlayerView = itemView.findViewById(R.id.player_view)

    override fun bind(
        model: ExoPlayerItem,
        adapter: SuperAdapter<ExoPlayerItem, *>
    ) {
        val exoPlayerPlugin = adapter.findPlugin<ExoPlayerPlugin>() ?: return
        exoPlayerPlugin.play(playerView)
        itemView.setOnLongClickListener {
            exoPlayerPlugin.play(playerView)
        }
        playerView.visibility = View.VISIBLE
    }

    override fun recycled(adapter: SuperAdapter<ExoPlayerItem, *>) {
        val exoPlayerPlugin = adapter.findPlugin<ExoPlayerPlugin>() ?: return
        exoPlayerPlugin.release(playerView)
        playerView.visibility = View.GONE
    }

    companion object {
        fun create(view: View): ExoPlayerViewHolder {
            return ExoPlayerViewHolder(
                view
            )
        }
    }
}