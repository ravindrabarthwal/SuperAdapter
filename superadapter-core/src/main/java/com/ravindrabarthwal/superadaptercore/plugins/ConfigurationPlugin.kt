package com.ravindrabarthwal.superadaptercore.plugins

import com.ravindrabarthwal.superadaptercore.base.SuperAdapter
import com.ravindrabarthwal.superadaptercore.base.SuperPlugin

class ConfigurationPlugin<T>(val data: T): SuperPlugin {
    override fun apply(superAdapter: SuperAdapter<*, *>) {

    }
}