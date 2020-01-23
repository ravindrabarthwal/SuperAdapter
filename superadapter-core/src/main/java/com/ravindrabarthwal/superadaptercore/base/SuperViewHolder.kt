package com.ravindrabarthwal.superadaptercore.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.item.SuperItem

abstract class SuperViewHolder<T: SuperItem>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(model: T, adapter: SuperAdapter<T, *>)
    open fun recycled(adapter: SuperAdapter<T, *>) {}
    open fun detached(superAdapter: SuperAdapter<T, *>) {}
}