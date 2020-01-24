package com.ravindrabarthwal.superadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ravindrabarthwal.superadapter.lib.implementations.GenericAdapter
import com.ravindrabarthwal.superadapter.lib.implementations.GenericViewHolderFactory
import com.ravindrabarthwal.superadapter.lib.implementations.items.TextItem
import com.ravindrabarthwal.superadaptercore.plugins.layoutmanagers.LinearLayoutManagerPlugin
import com.ravindrabarthwal.superadaptercore.item.SuperItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val rvHI = mutableListOf<SuperItem>()
//        for(i in 1..10) {
//            rvHI.add(ImageItem())
//        }
//        val rvHA = GenericSuperAdapter(this, null,
//            listOf(LinearLayoutManagerPlugin()),
//            rvHI, GenericViewHolderFactory())
//        val rvH = HorizontalRecyclerViewItem(adapter = rvHA)
        val superItems = mutableListOf<SuperItem>()
        for(i in 0..10) {
            superItems.add(TextItem())
        }
        val adapter = GenericAdapter(this,
            listOf(LinearLayoutManagerPlugin()),
            superItems, GenericViewHolderFactory())
        adapter.applyPlugins(rv)
        rv.adapter = adapter
    }
}
