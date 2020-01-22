package com.ravindrabarthwal.superadaptercore.base

interface SuperPlugin {
    fun apply(superAdapter: SuperAdapter<*, *>)
}