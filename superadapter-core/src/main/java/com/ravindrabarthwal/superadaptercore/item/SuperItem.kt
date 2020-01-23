package com.ravindrabarthwal.superadaptercore.item

interface SuperItem {
    val itemProperties: SuperItemProperties

    fun viewType(): Int {
        return itemProperties.viewType()
    }

    fun spanCount(): Int {
        return 1
    }

    fun isSame(newItem: SuperItem): Boolean {
        return false
    }

    fun hasSameContents(newItem: SuperItem): Boolean {
        return false
    }
}