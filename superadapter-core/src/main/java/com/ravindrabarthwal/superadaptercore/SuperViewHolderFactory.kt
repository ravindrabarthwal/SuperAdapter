package com.ravindrabarthwal.superadaptercore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperViewHolder
import com.ravindrabarthwal.superadaptercore.item.SuperItem

interface SuperViewHolderFactory {
    fun inflate(parent: ViewGroup, layoutId: Int): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }

    fun<T: SuperItem, VH: SuperViewHolder<T>> create(
        parent: ViewGroup,
        viewType: Int,
        superAdapter: SuperAdapter<T, VH>
    ): VH
}