package com.example.modulenam

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.modulenam.retrofit.Player

class AdapterEnam(private var players: List<Player>) :
    RecyclerView.Adapter<AdapterEnam.ViewHolder>() {

        // Deklarasi variabel untuk callback ketika item diklik
        private lateinit var onItemClickCallback: OnItemClickCallback

        // Fungsi untuk mengatur callback ketika item diklik
        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
            this.onItemClickCallback = onItemClickCallback
        }

        // Interface untuk callback ketika item diklik
        interface OnItemClickCallback {
            fun onItemClicked(data: Player)
        }

        // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val rating: TextView = itemView.findViewById(R.id.rate)
            val nomor: TextView = itemView.findViewById(R.id.no)
            val nama: TextView = itemView.findViewById(R.id.nama)
            val posisi: TextView = itemView.findViewById(R.id.posisi)
            val ga: TextView = itemView.findViewById(R.id.ga)
            val logo: ImageView = itemView.findViewById(R.id.logo)
            val foto: ImageView = itemView.findViewById(R.id.foto)
            val bg: ConstraintLayout = itemView.findViewById(R.id.back)
        }

        // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val view: View =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.konten, parent, false)
            return ViewHolder(view)
        }

        // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = players[position]

            holder.rating.text = data.rating.toString()
            holder.nomor.text = "#" + data.number.toString()
            holder.nama.text = data.name
            holder.posisi.text = data.position
            holder.ga.text = "G/A : " + data.ga


            // Mengatur image
            Glide.with(holder.logo)
                .load(data.club_logo)
                .into(holder.logo)

            Glide.with(holder.foto)
                .load(data.player_image)
                .into(holder.foto)


            Glide.with(holder.bg.context)
                .load(data.background_card) // replace with your image URL
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in
                    Drawable>?) {
                        holder.bg.background = resource
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                        // handle cleanup here
                    }
                })


                    // Mengatur aksi ketika item diklik
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(players[holder.absoluteAdapterPosition]) }
        }

        // Fungsi untuk mendapatkan jumlah item
        override fun getItemCount(): Int = players.size

        // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
        private fun String.shorten(maxLength: Int): String {
            return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
        }
}