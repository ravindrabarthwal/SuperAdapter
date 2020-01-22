package com.ravindrabarthwal.superadapter.lib.implementations.plugins

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class StaggeredGridLayoutManagerPlugin(private val spanCount:Int = 2,
                                       private val orientation: Int = RecyclerView.VERTICAL): SuperPlugin {

    override fun apply(superAdapter: SuperAdapter<*, *>) {
        val layoutManager = StaggeredGridLayoutManager(spanCount, orientation)
        superAdapter.recyclerView?.layoutManager = layoutManager
    }

}