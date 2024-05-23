package com.example.modulempat.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modulempat.R
import com.example.modulempat.room.kontenDatabase
import com.google.android.material.imageview.ShapeableImageView

class kontenAdapter(private val kontenList: List<kontenDatabase>) : RecyclerView.Adapter<kontenAdapter.kontenViewHolder>() {

    // Metode setOnItemClickCallback digunakan untuk mengatur callback
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: kontenDatabase)
    }

    // Kelas yang menyimpan referensi ke elemen-elemen tampilan dalam item konten
    class kontenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val komen: TextView = itemView.findViewById(R.id.komenfield)
        val deskripsi: TextView = itemView.findViewById(R.id.deskripsi_konten)
        val gambar: ShapeableImageView = itemView.findViewById(R.id.gambar_konten)
        var like: TextView = itemView.findViewById(R.id.likefield)
        val btnLike: ImageButton = itemView.findViewById(R.id.like_button)
        val btnComment: ImageButton = itemView.findViewById(R.id.comment_button)
        var isLiked = false
        var isCommented = false
    }

    // Metode ini mengatur tata letak item konten dan mengembalikan instance kontenViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kontenViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.konten, parent, false)
        return kontenViewHolder(view)
    }

    // Digunakan untuk mengikat data ke tampilan item dalam RecyclerView
    override fun onBindViewHolder(holder: kontenViewHolder, position: Int) {
        val data = kontenList[position]

        holder.deskripsi.text = data.deskripsi // Mengatur deskripsi konten
        holder.like.text = data.like.toString()
        holder.komen.text = data.komen.toString()

        val uri = Uri.fromFile(data.image)
        holder.gambar.setImageURI(uri)

        // Menangani klik pada btnLike
        holder.btnLike.setOnClickListener {
            if (!holder.isLiked) { // Jika isLiked false mengubah teks jumlah like menjadi 1
                holder.like.text = (data.like + 1).toString()
                holder.isLiked = true
            } else { // Jika isLiked true mengubah teks jumlah like menjadi 0
                holder.like.text = data.like.toString()
                holder.isLiked = false
            }
        }

        // Menangani klik pada btnComment
        holder.btnComment.setOnClickListener {
            if (!holder.isCommented) { // Jika isCommented false mengubah teks jumlah komen menjadi 1
                holder.komen.text = (data.komen + 1).toString()
                holder.isCommented = true
            } else { // Jika isCommented true mengubah teks jumlah komen menjadi 0
                holder.komen.text = data.komen.toString()
                holder.isCommented = false
            }
        }

        // Set item click listener
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(data)
        }
    }

    // Mengembalikan jumlah item dalam kontenList.
    override fun getItemCount(): Int = kontenList.size
}
