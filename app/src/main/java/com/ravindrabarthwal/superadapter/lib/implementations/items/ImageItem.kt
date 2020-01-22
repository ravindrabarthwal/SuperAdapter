package com.ravindrabarthwal.superadapter.lib.implementations.items

import com.ravindrabarthwal.superadapter.lib.implementations.GenericItemProperties
import com.ravindrabarthwal.superadaptercore.item.SuperItem
import com.ravindrabarthwal.superadaptercore.item.SuperItemProperties

class ImageItem(private val spanCount: Int = 1, override val itemProperties: SuperItemProperties = GenericItemProperties.IMAGE) :
    SuperItem {
    override fun spanCount(): Int {
        return spanCount
    }
}