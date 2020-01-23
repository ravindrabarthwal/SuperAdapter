package com.ravindrabarthwal.superadaptercore.plugins

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class GridLayoutManagerPlugin(private val spanCount:Int = 2,
                              private val orientation: Int = RecyclerView.VERTICAL,
                              private val reverseLayout: Boolean = false): SuperPlugin {

    override fun apply(superAdapter: SuperAdapter<*, *>) {
        val layoutManager = GridLayoutManager(superAdapter.context, spanCount, orientation, reverseLayout)
        layoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return superAdapter.getItemAt(position).spanCount()
            }
        }
        superAdapter.recyclerView?.layoutManager = layoutManager
    }

}