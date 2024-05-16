package com.example.modul3praktikum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3praktikum.R
import com.example.modul3praktikum.data.DataPlace
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class LinearPlaceAdapter(private val placeList: List<DataPlace>) : RecyclerView.Adapter<LinearPlaceAdapter.PlaceLinearViewHolder>() {
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
    class PlaceLinearViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeName: MaterialTextView = itemView.findViewById(R.id.place_name)
        val placeLocation: MaterialTextView = itemView.findViewById(R.id.place_location)
        val placeImage: ShapeableImageView = itemView.findViewById(R.id.place_picture)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceLinearViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.linear_layout_places, parent, false)
        return PlaceLinearViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: PlaceLinearViewHolder, position: Int) {
        val data = placeList[position]

        holder.placeName.text = data.nama
        holder.placeLocation.text = data.loc.shorten(85)
        holder.placeImage.setImageResource(data.image)

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(placeList[holder.adapterPosition]) }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = placeList.size

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}