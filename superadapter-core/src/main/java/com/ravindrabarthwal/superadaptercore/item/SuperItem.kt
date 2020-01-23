package com.ravindrabarthwal.superadaptercore.item

interface SuperItem {
    val itemProperties: SuperItemProperties

    fun viewType(): Int {
        return itemProperties.viewType()
    }

    fun spanCount(): Int

    fun isSame(newItem: SuperItem): Boolean

    fun hasSameContents(newItem: SuperItem): Boolean
}