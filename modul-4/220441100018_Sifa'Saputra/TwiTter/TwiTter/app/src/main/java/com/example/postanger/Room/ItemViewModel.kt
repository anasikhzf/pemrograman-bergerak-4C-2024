package com.example.twitter.Room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ItemViewModel(private val postRepository: ItemRepository) : ViewModel() {

    fun insertItem(item: ItemDatabase) {
        postRepository.insertItem(item)
    }

    fun getAllItem(): LiveData<List<ItemDatabase>> {
        return postRepository.getAllItem()
    }

    fun deleteItem(item: ItemDatabase) {
        postRepository.deleteItem(item)
    }
}