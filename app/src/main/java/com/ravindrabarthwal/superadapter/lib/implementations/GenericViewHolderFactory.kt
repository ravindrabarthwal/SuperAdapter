package com.ravindrabarthwal.superadapter.lib.implementations

import android.view.ViewGroup
import com.ravindrabarthwal.superadaptercore.SuperViewHolderFactory
import com.ravindrabarthwal.superadaptercore.base.SuperViewHolder
import com.ravindrabarthwal.superadaptercore.item.SuperItem

class GenericViewHolderFactory: SuperViewHolderFactory {

    override fun <T : SuperItem, VH : SuperViewHolder<T>> create(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        val type = GenericItemProperties.get(viewType)
        val view = inflate(parent, type.layoutId)
        return type.getViewHolder(view)
    }

}