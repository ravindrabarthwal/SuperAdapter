package com.ravindrabarthwal.superadaptercore.plugins

import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class ScrollListenerPlugin(
    private val onScrolled: (RecyclerView, Int, Int, SuperAdapter<*, *>) -> Unit = { _, _, _, _ -> },
    private val onScrollStateChange: (RecyclerView, Int, SuperAdapter<*, *>) -> Unit = { _, _, _ -> }
) : SuperPlugin {
    override fun apply(superAdapter: SuperAdapter<*, *>) {
        superAdapter.recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                this@ScrollListenerPlugin.onScrolled(recyclerView, dx, dy, superAdapter)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                onScrollStateChange(recyclerView, newState, superAdapter)
            }
        })
    }

}