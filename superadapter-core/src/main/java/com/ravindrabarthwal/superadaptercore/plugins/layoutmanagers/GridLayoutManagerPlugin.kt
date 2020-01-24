package com.ravindrabarthwal.superadaptercore.plugins.layoutmanagers

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.base.LayoutManagerPlugin
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class GridLayoutManagerPlugin(
    private val spanCount: Int = 2,
    private val orientation: Int = RecyclerView.VERTICAL,
    private val reverseLayout: Boolean = false
) : LayoutManagerPlugin {

    override var _layoutManager: RecyclerView.LayoutManager? = null

    override fun apply(superAdapter: SuperAdapter<*, *>) {
        if (_layoutManager == null) {
            _layoutManager =
                GridLayoutManager(superAdapter.context, spanCount, orientation, reverseLayout)
        }
        (_layoutManager!! as GridLayoutManager).spanSizeLookup =
            object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return superAdapter.getItemAt(position).spanCount()
                }
            }
        superAdapter.recyclerView?.layoutManager = _layoutManager
    }

}