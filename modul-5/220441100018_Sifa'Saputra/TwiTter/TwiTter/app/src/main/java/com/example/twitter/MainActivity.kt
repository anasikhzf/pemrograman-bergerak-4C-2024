package com.example.twitter

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.Room.ItemDatabase
import com.example.twitter.Room.ItemViewModel
import com.example.twitter.Room.ItemViewModelFactory
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapterRoom: ItemAdapterRoom
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = ItemViewModelFactory.getInstance(this)
        itemViewModel = ViewModelProvider(this, factory)[ItemViewModel::class.java]
        recyclerView = findViewById(R.id.rv_post)
        recyclerView.layoutManager = LinearLayoutManager(this)

        itemViewModel.getAllItem().observe(this) { postData ->
            if (postData != null) {
                itemAdapterRoom = ItemAdapterRoom(postData, itemViewModel)
                recyclerView.adapter = itemAdapterRoom

                itemAdapterRoom.setOnItemClickCallback(object : ItemAdapterRoom.OnItemClickCallback {
                    override fun onMoreClicked(data: ItemDatabase, position: Int) {
                        PopUpFragment(data, position).show(supportFragmentManager, PopUpFragment.TAG)
                    }

                    override fun onShareClicked(data: ItemDatabase) {
                        shareToWhatsApp(data)
                    }
                })
            }
        }
    }

    private fun shareToWhatsApp(item: ItemDatabase) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, item.description)
            type = "text/plain"
            val fileUri = Uri.fromFile(item.image)
            fileUri?.let {
                putExtra(Intent.EXTRA_STREAM, it)
                type = "image/jpeg"
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        }

        val whatsappInstalled = isPackageInstalled("com.whatsapp") || isPackageInstalled("com.whatsapp.w4b")
        if (whatsappInstalled) {

            // Jika WhatsApp terinstal, atur paket intent ke "com.whatsapp" dan mulai activity
            shareIntent.setPackage("com.whatsapp")
            startActivity(shareIntent)
        } else {

            // Jika WhatsApp tidak terinstal, tampilkan pesan toast
            Toast.makeText(this, "WhatsApp tidak terinstal.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun isPackageInstalled(packageName: String): Boolean {
        return try {
            // Mencoba mendapatkan informasi paket
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            // Jika paket tidak ditemukan, kembalikan false
            false
        }
    }

    override fun onRestart() {
        super.onRestart()
        itemViewModel.getAllItem()
    }

    fun toAddPost(view: View) {
        val intent = Intent(this, tambah::class.java)
        startActivity(intent)
    }
}
