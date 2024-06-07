package com.example.twitter.Room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ItemViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    fun insertItem(item: ItemDatabase) {
        itemRepository.insertItem(item)
    }

    fun getAllItem(): LiveData<List<ItemDatabase>> {
        return itemRepository.getAllItem()
    }

    fun deleteItem(item: ItemDatabase) {
        itemRepository.deleteItem(item)
    }

    fun updateItem(item: ItemDatabase) {
        itemRepository.updateItem(item)
    }
}