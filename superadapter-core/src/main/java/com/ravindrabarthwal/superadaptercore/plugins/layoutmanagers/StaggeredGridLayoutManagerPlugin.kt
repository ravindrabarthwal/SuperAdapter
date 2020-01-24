package com.ravindrabarthwal.superadaptercore.plugins.layoutmanagers

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ravindrabarthwal.superadaptercore.base.LayoutManagerPlugin
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class StaggeredGridLayoutManagerPlugin(private val spanCount:Int = 2,
                                       private val orientation: Int = RecyclerView.VERTICAL):
    LayoutManagerPlugin {
    override var _layoutManager: RecyclerView.LayoutManager? = null

    override fun apply(superAdapter: SuperAdapter<*, *>) {
        if(_layoutManager == null) {
            _layoutManager = StaggeredGridLayoutManager(spanCount, orientation)
        }
        superAdapter.recyclerView?.layoutManager = _layoutManager
    }

}