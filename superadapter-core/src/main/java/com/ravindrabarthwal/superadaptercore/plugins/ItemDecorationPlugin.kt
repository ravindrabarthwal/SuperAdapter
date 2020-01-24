package com.ravindrabarthwal.superadaptercore.plugins

import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class ItemDecorationPlugin(private val decor: RecyclerView.ItemDecoration): SuperPlugin {
    override fun apply(superAdapter: SuperAdapter<*, *>) {
        superAdapter.recyclerView?.addItemDecoration(decor)
    }
}