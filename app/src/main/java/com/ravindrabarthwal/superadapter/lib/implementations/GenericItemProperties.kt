package com.ravindrabarthwal.superadapter.lib.implementations

import android.view.View
import com.ravindrabarthwal.superadapter.R
import com.ravindrabarthwal.superadapter.lib.implementations.viewholders.ExoPlayerViewHolder
import com.ravindrabarthwal.superadapter.lib.implementations.viewholders.HorizontalRVViewHolder
import com.ravindrabarthwal.superadapter.lib.implementations.viewholders.ImageViewHolder
import com.ravindrabarthwal.superadapter.lib.implementations.viewholders.TextViewHolder
import com.ravindrabarthwal.superadaptercore.base.SuperViewHolder
import com.ravindrabarthwal.superadaptercore.item.SuperItem
import com.ravindrabarthwal.superadaptercore.item.SuperItemProperties
import java.lang.IllegalStateException

enum class GenericItemProperties(override val layoutId: Int): SuperItemProperties {
    TEXT(layoutId = R.layout.layout_text_item),
    IMAGE(layoutId = R.layout.layout_image_item),
    HORIZONTAL_RV(layoutId = R.layout.layout_horizontal_rv),
    EXOPLAYER(layoutId = R.layout.layout_exoplayer_item),
    ;

    override fun viewType(): Int {
        return ordinal
    }

    override fun <T : SuperItem, VH: SuperViewHolder<T>> getViewHolder(view: View): VH{
        return when(this) {
            TEXT -> TextViewHolder.create(view)
            IMAGE -> ImageViewHolder.create(view)
            HORIZONTAL_RV -> HorizontalRVViewHolder.create(view)
            EXOPLAYER -> ExoPlayerViewHolder.create(view)
            else -> throw IllegalStateException("Can not create viewholder for ${this.name}. Implementation needed.")
        } as VH
    }

    companion object {
        fun get(position: Int): SuperItemProperties {
            return values()[position]
        }
    }
}