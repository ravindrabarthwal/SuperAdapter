package com.ravindrabarthwal.superadapter.lib.implementations.viewholders

import android.view.View
import com.ravindrabarthwal.superadapter.lib.implementations.items.TextItem
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperViewHolder

class TextViewHolder(itemView: View) : SuperViewHolder<TextItem>(itemView) {
    override fun bind(model: TextItem,  adapter: SuperAdapter<TextItem, *>) {

    }

    companion object {
        fun create(view: View): TextViewHolder {
            return TextViewHolder(
                view
            )
        }
    }
}