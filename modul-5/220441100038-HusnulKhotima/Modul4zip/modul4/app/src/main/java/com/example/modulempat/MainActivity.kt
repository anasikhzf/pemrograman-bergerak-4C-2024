package com.example.modulempat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.modulempat.adapter.PostAdapterRoom
import com.example.modulempat.room.PostDatabase
import com.example.modulempat.room.PostViewModel
import com.example.modulempat.room.PostViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapterRoom: PostAdapterRoom
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = PostViewModelFactory.getInstance(this)
        postViewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]
        recyclerView = findViewById(R.id.recycler_konten)
        recyclerView.layoutManager = LinearLayoutManager(this)

        postViewModel.getAllPost().observe(this) { postData ->
            if (postData != null) {
                postAdapterRoom = PostAdapterRoom(postData, postViewModel) //ini
                recyclerView.adapter = postAdapterRoom

                postAdapterRoom.setOnItemClickCallback(object :
                    PostAdapterRoom.OnItemClickCallback {
                    override fun onMoreClicked(data: PostDatabase, position: Int) {
                        fragment_pop_up(data, position).show(supportFragmentManager, fragment_pop_up.TAG)
                    }
                })
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        postViewModel.getAllPost()
    }

    fun toAddPost(view: View) {
        val intent = Intent(this, PostActivity::class.java)
        startActivity(intent)
    }
}