package com.ravindrabarthwal.superadapter.lib.implementations.viewholders

import android.view.View
import com.ravindrabarthwal.superadapter.lib.implementations.items.ImageItem
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperViewHolder

class ImageViewHolder(itemView: View) : SuperViewHolder<ImageItem>(itemView) {
    override fun bind(model: ImageItem,  adapter: SuperAdapter<ImageItem, *>) {

    }

    companion object {
        fun create(view: View): ImageViewHolder {
            return ImageViewHolder(
                view
            )
        }
    }
}