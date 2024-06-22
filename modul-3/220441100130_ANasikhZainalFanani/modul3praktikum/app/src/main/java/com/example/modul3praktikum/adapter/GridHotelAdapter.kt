package com.example.modul3praktikum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3praktikum.R
import com.example.modul3praktikum.data.DataHotel
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class GridHotelAdapter (private val hotelList: List<DataHotel>) : RecyclerView.Adapter<GridHotelAdapter.GridHotelViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: DataHotel)
    }
    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class GridHotelViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName: MaterialTextView = itemView.findViewById(R.id.hotel_name)
        val hotelRate: MaterialTextView = itemView.findViewById(R.id.hotel_rating)
        val hotelPerson: MaterialTextView = itemView.findViewById(R.id.hotel_person)
        val hotelImage: ShapeableImageView = itemView.findViewById(R.id.hotel_picture)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridHotelViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_hotels_grid, parent, false)
        return GridHotelViewHolder(view)
    }




    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: GridHotelViewHolder, position: Int) {
        val data = hotelList[position]

        holder.hotelName.text = data.nama
        holder.hotelPerson.text = data.jml
        holder.hotelRate.text = data.rate
        holder.hotelImage.setImageResource(data.image)

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

