package com.exemple.hotels.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.R
import com.exemple.hotels.data.HotelsData
import com.google.android.material.imageview.ShapeableImageView

// Kelas adapter untuk RecyclerView dengan tampilan GridLayoutManager
class HotelAdapterGrid(private val hotelList: List<HotelsData>) : RecyclerView.Adapter<HotelAdapterGrid.HotelsViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: HotelsData)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class HotelsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName: TextView = itemView.findViewById(R.id.hotel_name)
        val hotelJumlah: TextView = itemView.findViewById(R.id.hotel_jumlah)
        val hotelRating: TextView = itemView.findViewById(R.id.hotel_rating)
        val hotelImage: ShapeableImageView = itemView.findViewById(R.id.image_hotel)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_hotels_grid, parent, false)
        return HotelsViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        val data = hotelList[position]

        holder.hotelName.text = data.nama
        holder.hotelJumlah.text = data.jumlah_pengunjung.toString()
        holder.hotelRating.text = data.rating.toString()
        holder.hotelImage.setImageResource(data.gambar)

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(hotelList[holder.adapterPosition]) }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = hotelList.size

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}