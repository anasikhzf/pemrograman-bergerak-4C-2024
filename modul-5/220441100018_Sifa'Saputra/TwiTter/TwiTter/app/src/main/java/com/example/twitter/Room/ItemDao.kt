package com.example.twitter.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(itemDatabase: ItemDatabase)

    @Query("SELECT * FROM ItemDatabase ORDER BY id DESC")
    fun getAllItem() : LiveData<List<ItemDatabase>>

    @Delete
    fun deletePost(item: ItemDatabase)

    @Update
    fun updateItem(item: ItemDatabase)
}
