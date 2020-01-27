package com.ravindrabarthwal.superadaptercore.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.item.SuperItem

abstract class SuperViewHolder<T: SuperItem>(itemView: View, protected val adapter: SuperAdapter<*, *>): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(model: T)
    open fun recycled() {}
    open fun detached() {}
}