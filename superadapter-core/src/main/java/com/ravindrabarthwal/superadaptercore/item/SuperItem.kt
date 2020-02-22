package com.ravindrabarthwal.superadaptercore.item

interface SuperItem {
    fun itemProperties(): SuperItemProperties

    fun viewType(): Int {
        return itemProperties().viewType()
    }

    fun spanCount(): Int

    fun isSame(newItem: SuperItem): Boolean

    fun hasSameContents(newItem: SuperItem): Boolean
}