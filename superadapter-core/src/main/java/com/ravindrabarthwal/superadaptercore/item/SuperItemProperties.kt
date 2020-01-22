package com.ravindrabarthwal.superadaptercore.item

import android.view.View
import com.ravindrabarthwal.superadaptercore.base.SuperViewHolder

interface SuperItemProperties {
    val layoutId: Int

    fun viewType(): Int
    fun <T : SuperItem, VH: SuperViewHolder<T>> getViewHolder(view: View): VH
}