package com.example.twitter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.Adapter.ItemAdapterRoom
import com.example.twitter.Room.ItemDatabase
import com.example.twitter.Room.ItemViewModel
import com.example.twitter.Room.ItemViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var postViewModel: ItemViewModel
    private lateinit var postAdapterRoom: ItemAdapterRoom
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = ItemViewModelFactory.getInstance(this)
        postViewModel = ViewModelProvider(this, factory)[ItemViewModel::class.java]
        recyclerView = findViewById(R.id.rv_post)
        recyclerView.layoutManager = LinearLayoutManager(this)

        postViewModel.getAllItem().observe(this) { postData ->
            if (postData != null) {
                postAdapterRoom = ItemAdapterRoom(postData)
                recyclerView.adapter = postAdapterRoom
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        postViewModel.getAllItem()
    }

    fun toAddPost(view: View) {
        val intent = Intent(this, tambah::class.java)
        startActivity(intent)
    }
}
