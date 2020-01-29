package com.ravindrabarthwal.superadaptercore.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.item.SuperItem

interface SuperPlugin {
    fun apply(superAdapter: SuperAdapter<*, *>)
    fun recycle(superAdapter: SuperAdapter<*, *>) {}
}

/**
 * All plugins that are responsible for layout manager.
 * Must implement this Interface
 */
interface LayoutManagerPlugin: SuperPlugin {
    var _layoutManager: RecyclerView.LayoutManager?

    @Throws(IllegalAccessError::class)
    fun getLayoutManager(): RecyclerView.LayoutManager {
        if (_layoutManager == null) throw IllegalAccessError("LayoutManager is not created yet. Call superAdapter.apply(RecyclerView) before accessing this method.")
        return _layoutManager!!
    }

    override fun recycle(superAdapter: SuperAdapter<*, *>) {
        super.recycle(superAdapter)
        superAdapter.recyclerView?.layoutManager = null
    }
}

/**
 * All plugins that handles the click listener must implement this plugin.
 */
interface ClickListenerPlugin: SuperPlugin {
    fun<T: SuperItem> onClick(view: View, position: Int, model: T): Boolean
}