package com.example.modulempat.room

import androidx.lifecycle.LiveData
import com.example.modulempat.utils.AppExecutor

class PostRepository private constructor(private val postDao: PostDao, private val appExecutors: AppExecutor) {

    fun getAllPost(): LiveData<List<PostDatabase>> = postDao.getAllPost()

    fun insertPost(post: PostDatabase) {
        appExecutors.diskIO().execute { postDao.insertPost(post) }
    }

    fun deletePost(post: PostDatabase) {
        appExecutors.diskIO().execute { postDao.deletePost(post) }
    }

    fun updatePost(post: PostDatabase) {
        appExecutors.diskIO().execute { postDao.updatePost(post) }
    }

    companion object {
        @Volatile
        private var instance: PostRepository? = null

        fun getInstance(
            postDao: PostDao,
            appExecutors: AppExecutor
        ): PostRepository = instance ?: synchronized(this) {
            instance ?: PostRepository(postDao, appExecutors)
        }.also { instance = it }
    }
}