package com.ravindrabarthwal.superadapter.lib.implementations.items

import com.ravindrabarthwal.superadapter.lib.implementations.GenericItemProperties
import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperViewHolder
import com.ravindrabarthwal.superadaptercore.item.SuperItem
import com.ravindrabarthwal.superadaptercore.item.SuperItemProperties

class HorizontalRecyclerViewItem(override val itemProperties: SuperItemProperties = GenericItemProperties.HORIZONTAL_RV,
                                 val adapter: SuperAdapter<SuperItem, SuperViewHolder<SuperItem>>
) : SuperItem {

}