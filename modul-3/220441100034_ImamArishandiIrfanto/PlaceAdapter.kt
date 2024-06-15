package com.exemple.hotels.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.R
import com.example.hotels.data.PlacesData
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

// Kelas adapter untuk RecyclerView dengan tampilan LinearLayoutManager
class PlaceAdapter(private val placesList: List<PlacesData>) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: PlacesData)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class PlaceViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeName: MaterialTextView = itemView.findViewById(R.id.place_name)
        val placeLocation: MaterialTextView = itemView.findViewById(R.id.place_location)
        val placeImage: ShapeableImageView = itemView.findViewById(R.id.place_picture)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_places, parent, false)
        return PlaceViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val data = placesList[position]

        holder.placeName.text = data.name
        holder.placeLocation.text = data.lokasi.shorten(85)
        holder.placeImage.setImageResource(data.image)

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(placesList[holder.adapterPosition]) }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = placesList.size

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}