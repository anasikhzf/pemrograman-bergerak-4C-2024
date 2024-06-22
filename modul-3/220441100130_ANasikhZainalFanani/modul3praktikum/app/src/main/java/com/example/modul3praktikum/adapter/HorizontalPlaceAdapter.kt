package com.example.modul3praktikum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3praktikum.R
import com.example.modul3praktikum.data.DataPlace
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class HorizontalPlaceAdapter (private val placeList: List<DataPlace>) : RecyclerView.Adapter<HorizontalPlaceAdapter.PlaceHorizontalViewHolder>() {
    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: DataPlace)
    }
    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class PlaceHorizontalViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeName: MaterialTextView = itemView.findViewById(R.id.place_name)
        val placeLocation: MaterialTextView = itemView.findViewById(R.id.place_location)
        val visitors: TextView = itemView.findViewById(R.id.visitors)
        val placeImage: ShapeableImageView = itemView.findViewById(R.id.place_picture)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceHorizontalViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_place_horizontal, parent, false)
        return PlaceHorizontalViewHolder(view)
    }




    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: PlaceHorizontalViewHolder, position: Int) {
        val data = placeList[position]

        holder.placeName.text = data.nama
        holder.placeLocation.text = data.loc.shorten(85)
        holder.visitors.text = data.visitors
        holder.placeImage.setImageResource(data.image)

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(placeList[holder.adapterPosition]) }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int {
    return if (placeList.size > 3) {
        3
    } else {
        placeList.size
    }}

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }


}

