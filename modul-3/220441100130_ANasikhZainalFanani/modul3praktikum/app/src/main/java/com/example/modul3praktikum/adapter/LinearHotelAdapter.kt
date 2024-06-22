package com.example.modul3praktikum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3praktikum.R
import com.example.modul3praktikum.data.DataHotel
import com.example.modul3praktikum.data.DataPlace
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class LinearHotelAdapter(private val hotelList: List<DataHotel>): RecyclerView.Adapter<LinearHotelAdapter.HotelLinearViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: DataHotel)
    }
    class HotelLinearViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName: MaterialTextView = itemView.findViewById(R.id.hotel_name)
        val hotelLocation: MaterialTextView = itemView.findViewById(R.id.hotel_location)
        val hotelDesc: MaterialTextView = itemView.findViewById(R.id.hotelsDescriptionMessage)
        val hotelImage: ShapeableImageView = itemView.findViewById(R.id.hotel_picture)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelLinearViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.linear_layout_hotels, parent, false)
        return HotelLinearViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelLinearViewHolder, position: Int) {
        val data = hotelList[position]

        holder.hotelName.text = data.nama
        holder.hotelLocation.text = data.loc
        holder.hotelDesc.text = data.desc.shorten(85)
        holder.hotelImage.setImageResource(data.image)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(hotelList[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int {
    return hotelList.size
    }
    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}