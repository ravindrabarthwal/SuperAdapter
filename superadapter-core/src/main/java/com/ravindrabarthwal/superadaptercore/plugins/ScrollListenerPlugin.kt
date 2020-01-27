package com.ravindrabarthwal.superadaptercore.plugins

import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

abstract class ScrollListenerPlugin: SuperPlugin {
    private var scrollListener: RecyclerView.OnScrollListener? = null
    override fun apply(superAdapter: SuperAdapter<*, *>) {
        if(scrollListener == null) {
            scrollListener = createScrollListener(superAdapter)
        }
        superAdapter.recyclerView?.addOnScrollListener(scrollListener!!)
    }
    private fun createScrollListener(superAdapter: SuperAdapter<*, *>): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                this@ScrollListenerPlugin.onScrolled(recyclerView, dx, dy, superAdapter)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                onScrollStateChange(recyclerView, newState, superAdapter)
            }
        }
    }

    override fun recycle(superAdapter: SuperAdapter<*, *>) {
        super.recycle(superAdapter)
        if(scrollListener != null) {
            superAdapter.recyclerView?.removeOnScrollListener(scrollListener!!)
        }
    }
    open fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int, superAdapter: SuperAdapter<*, *>) {}
    open fun onScrollStateChange(recyclerView: RecyclerView, newState: Int, superAdapter: SuperAdapter<*, *>) {}
}