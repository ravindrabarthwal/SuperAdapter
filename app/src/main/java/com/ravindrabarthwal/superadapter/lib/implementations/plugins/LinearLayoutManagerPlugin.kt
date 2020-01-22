package com.ravindrabarthwal.superadapter.lib.implementations.plugins

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class LinearLayoutManagerPlugin(private val orientation: Int = RecyclerView.HORIZONTAL,
                                private val reverseLayout: Boolean = false):
    SuperPlugin {

    override fun apply(superAdapter: SuperAdapter<*, *>) {
        val layoutManager = LinearLayoutManager(superAdapter.context, orientation, reverseLayout)
        superAdapter.recyclerView?.layoutManager = layoutManager
    }

}