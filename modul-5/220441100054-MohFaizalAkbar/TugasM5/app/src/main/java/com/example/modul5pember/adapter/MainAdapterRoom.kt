package com.example.modul5pember.adapter

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modul5pember.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.example.modul5pember.room.PostDatabase

class MainAdapterRoom(private var playerList: List<PostDatabase>) :
    RecyclerView.Adapter<MainAdapterRoom.PlayerViewHolder>() {
    private var isClicked = false
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onMoreClicked(data: PostDatabase, position: Int)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postDeskripsi: MaterialTextView = itemView.findViewById(R.id.deskripsi)
        val postImage: ShapeableImageView = itemView.findViewById(R.id.image)
        val heartCount: TextView = itemView.findViewById(R.id.heart_count)
        val btnHeart: LinearLayout = itemView.findViewById(R.id.btn_heart)
        val heartImage: ImageView = itemView.findViewById(R.id.img_heart)
        val btnMore: ImageView = itemView.findViewById(R.id.btn_more)
    }
    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)
        return PlayerViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val data = playerList[position]

        holder.postDeskripsi.text = data.deskripsi

        // Mengatur image
        val uri = Uri.fromFile(data.image)
        holder.postImage.setImageURI(uri)

        // Fungsi untuk mengatur like / love agar bertambah jumlahnya dan mengbah warna love menjadi merah
        holder.btnHeart.setOnClickListener {
            // tombol love di klik
            isClicked = !isClicked

            // mengubah ikon love menjadi merah
            val drawableRes = if (isClicked) R.drawable.heart_fill_red else R.drawable.heart_outlined_black
            holder.heartImage.setImageResource(drawableRes)

            // mengubah jumlah like dari 45 menjadi 46
            holder.heartCount.text = if (isClicked) "46" else "45"
            holder.heartCount.setTextColor(if (isClicked) Color.RED else Color.BLACK)
        }

        // Mengatur aksi ketika button more / button titik tiga diklik
        holder.btnMore.setOnClickListener { onItemClickCallback.onMoreClicked(playerList[holder.absoluteAdapterPosition], holder.absoluteAdapterPosition) }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = playerList.size
}