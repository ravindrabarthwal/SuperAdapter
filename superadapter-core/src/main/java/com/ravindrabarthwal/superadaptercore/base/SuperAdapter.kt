package com.ravindrabarthwal.superadaptercore.base

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.SuperViewHolderFactory
import com.ravindrabarthwal.superadaptercore.item.SuperItem
import java.lang.IllegalArgumentException

/**
 * Abstract Adapter class for [RecyclerView]
 *
 * @param T: [SuperItem]
 * @param VH: [SuperViewHolder]<[T]>
 */
abstract class SuperAdapter<T: SuperItem, VH: SuperViewHolder<T>>:
    ListAdapter<T, VH>(object: DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.isSame(newItem)
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.hasSameContents(newItem)
        }

    }) {
    abstract val context: Context
    abstract val superItems: MutableList<T>
    abstract val plugins: List<SuperPlugin>
    abstract val superViewHolderFactory: SuperViewHolderFactory
    var recyclerView: RecyclerView? = null

    /**
     * Apply [plugins] on the adapter. This method must be called after
     * getting an instance of [SuperAdapter] to apply plugins.
     *
     * @param recyclerView: [RecyclerView] the adapter is bound to
     */
    fun applyPlugins(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        plugins.forEach { it.apply(this) }
    }

    /**
     * Find type of plugin [T] in [plugins] if found else null
     *```
     * superAdapter.findPlugin<LinearLayoutManagerPlugin>()
     * ```
     * @param T: Plugin to find
     * @return
     */
    inline fun<reified T> findPlugin(): T? {
        val p =  plugins.find { it is T } ?: return null
        return p as T
    }

    @Throws(IllegalArgumentException::class)
    fun getLayoutManager(): RecyclerView.LayoutManager {
        val plugin = findPlugin<LayoutManagerPlugin>()
            ?: throw IllegalArgumentException("No LayoutManagerPlugin found.")
        return plugin.getLayoutManager()
    }

    /**
     * Returns an item at a specified position
     *
     * @param position
     * @return
     */
    fun getItemAt(position: Int): T {
        return getItem(position)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return superViewHolderFactory.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItemAt(position), this)
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        holder.recycled(this)
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.detached(this)
    }
}