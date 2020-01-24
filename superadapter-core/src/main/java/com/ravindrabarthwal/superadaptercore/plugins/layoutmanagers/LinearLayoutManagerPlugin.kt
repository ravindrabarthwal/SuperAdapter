package com.ravindrabarthwal.superadaptercore.plugins.layoutmanagers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.base.LayoutManagerPlugin
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class LinearLayoutManagerPlugin(private val orientation: Int = RecyclerView.HORIZONTAL,
                                private val reverseLayout: Boolean = false):
    LayoutManagerPlugin {
    override var _layoutManager: RecyclerView.LayoutManager? = null

    override fun apply(superAdapter: SuperAdapter<*, *>) {
        if(_layoutManager == null) {
            _layoutManager = LinearLayoutManager(superAdapter.context, orientation, reverseLayout)
        }
        superAdapter.recyclerView?.layoutManager = _layoutManager
    }

}