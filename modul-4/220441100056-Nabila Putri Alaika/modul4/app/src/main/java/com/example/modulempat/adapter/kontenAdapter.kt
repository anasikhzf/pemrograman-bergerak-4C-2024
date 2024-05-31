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

// Kelas adapter untuk RecyclerView dengan tampilan LinearLayoutManager
class kontenAdapter(private val kontenList: List<kontenDatabase>) : RecyclerView.Adapter<kontenAdapter.kontenViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: kontenDatabase)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class kontenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val deskripsi: TextView = itemView.findViewById(R.id.deskripsi_konten)
        val gambar: ShapeableImageView = itemView.findViewById(R.id.gambar_konten)
        var like: TextView = itemView.findViewById(R.id.likefield)
        val btnLike: ImageButton = itemView.findViewById(R.id.like_button)
        var isClik = false
        var isCommented = false
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): kontenViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.konten, parent, false)
        return kontenViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: kontenViewHolder, position: Int) {
        val data = kontenList[position]

        //mengatur deskripsi konten
        holder.deskripsi.text = data.deskripsi
        holder.like.text = data.like.toString()

        val uri = Uri.fromFile(data.image)
        holder.gambar.setImageURI(uri)

        holder.btnLike.setOnClickListener {
            if (holder.isClik == false){
                holder.like.text = "1"
                holder.isClik = true
                return@setOnClickListener
            }else{
                holder.like.text = "0"
                holder.isClik = false
                return@setOnClickListener
            }
        }


    }
    override fun getItemCount(): Int = kontenList.size
}