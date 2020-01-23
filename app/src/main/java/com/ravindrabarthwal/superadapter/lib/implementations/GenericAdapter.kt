package com.ravindrabarthwal.superadapter.lib.implementations

import android.content.Context
import com.ravindrabarthwal.superadaptercore.SuperViewHolderFactory
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin
import com.ravindrabarthwal.superadaptercore.base.SuperViewHolder
import com.ravindrabarthwal.superadaptercore.item.SuperItem

class GenericAdapter(
        override val context: Context,
        override val plugins: List<SuperPlugin>,
        override val superItems: MutableList<SuperItem>,
        override val superViewHolderFactory: SuperViewHolderFactory
) : SuperAdapter<SuperItem, SuperViewHolder<SuperItem>>() {
        init {
                submitList(superItems)
        }
}