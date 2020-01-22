package com.ravindrabarthwal.superadapter.lib.implementations.viewholders

import android.view.View
import com.ravindrabarthwal.superadapter.lib.implementations.items.HorizontalRecyclerViewItem
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperViewHolder
import kotlinx.android.synthetic.main.layout_horizontal_rv.view.*

class HorizontalRVViewHolder(itemView: View) : SuperViewHolder<HorizontalRecyclerViewItem>(itemView) {
    override fun bind(model: HorizontalRecyclerViewItem, adapter: SuperAdapter<HorizontalRecyclerViewItem, *>) {
        itemView.rv_h.adapter = model.adapter
        model.adapter.recyclerView =  itemView.rv_h
        model.adapter.applyPlugins(itemView.rv_h)
    }

    companion object {
        fun create(view: View): HorizontalRVViewHolder {
            return HorizontalRVViewHolder(
                view
            )
        }
    }
}