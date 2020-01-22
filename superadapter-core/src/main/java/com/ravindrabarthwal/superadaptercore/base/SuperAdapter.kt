package com.ravindrabarthwal.superadaptercore.base

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravindrabarthwal.superadaptercore.SuperViewHolderFactory
import com.ravindrabarthwal.superadaptercore.item.SuperItem

/**
 * Abstract Adapter class for [RecyclerView]
 *
 * @param T: [SuperItem]
 * @param VH: [SuperViewHolder]<[T]>
 */
abstract class SuperAdapter<T: SuperItem, VH: SuperViewHolder<T>>: RecyclerView.Adapter<VH>() {
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

    fun setItem(items: List<T>) {
        this.superItems.clear()
        this.superItems.addAll(items)
        this.notifyDataSetChanged()
    }

    fun updateItems(items: List<T>) {
        // TODO: Update items here. Must be a logic
    }

    /**
     * Returns an item at a specified position
     *
     * @param position
     * @return
     */
    fun getItemAt(position: Int): T {
        return superItems[position]
    }

    override fun getItemViewType(position: Int): Int {
        return getItemAt(position).viewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return superViewHolderFactory.create(parent, viewType)
    }

    override fun getItemCount(): Int {
        return superItems.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItemAt(position), this)
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        holder.recycled(this)
    }
}