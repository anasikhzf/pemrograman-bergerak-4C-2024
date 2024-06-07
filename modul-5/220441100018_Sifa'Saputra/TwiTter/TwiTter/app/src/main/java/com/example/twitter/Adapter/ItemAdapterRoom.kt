package com.example.twitter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.twitter.Room.ItemDatabase
import com.example.twitter.Room.ItemViewModel
import com.google.android.material.imageview.ShapeableImageView

class ItemAdapterRoom(private var itemList: List<ItemDatabase>, private val itemViewModel: ItemViewModel) :
    RecyclerView.Adapter<ItemAdapterRoom.ItemViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private var stateFav = false

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onMoreClicked(data: ItemDatabase, position: Int)
        fun onShareClicked(data: ItemDatabase) // Added onShareClicked
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postDesc: TextView = itemView.findViewById(R.id.item_description)
        val postImg: ShapeableImageView = itemView.findViewById(R.id.item_image)
        val postLike: TextView = itemView.findViewById(R.id.like)
        val iconLike: ImageView = itemView.findViewById(R.id.icon_like)

        //btn
        val btnLike: LinearLayout = itemView.findViewById(R.id.btn_like)
        val btnMore: ImageView = itemView.findViewById(R.id.btn_more)
        val shareIcon: ImageView = itemView.findViewById(R.id.btn_share) // Added shareIcon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.content, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = itemList[position]

        holder.postDesc.text = data.description.shorten(500)
        holder.postLike.text = data.like.toString()

        Glide.with(holder.postImg.context).load(Uri.fromFile(data.image)).into(holder.postImg)

        holder.btnLike.setOnClickListener {
            stateFav = !stateFav
            holder.iconLike.setImageResource(if (stateFav) R.drawable.on else R.drawable.heart)
            if (stateFav) {
                data.like += 1
            } else {
                data.like -= 1
            }
            holder.postLike.text = data.like.toString()
        }

        holder.btnMore.setOnClickListener {
            onItemClickCallback.onMoreClicked(itemList[holder.absoluteAdapterPosition], holder.absoluteAdapterPosition)
        }

        holder.shareIcon.setOnClickListener {
            onItemClickCallback.onShareClicked(itemList[holder.absoluteAdapterPosition])
        }
    }

    override fun getItemCount(): Int = itemList.size

    fun updateItems(items: List<ItemDatabase>) {
        itemList = items
        notifyDataSetChanged()
    }

    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}
