package com.example.postanger.Adapter

import android.content.ClipData.Item
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postanger.R
import com.example.postanger.Room.ItemDatabase
import com.google.android.material.imageview.ShapeableImageView

class ItemAdapterRoom(private var itemList: List<ItemDatabase>) :
    RecyclerView.Adapter<ItemAdapterRoom.ItemViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ItemDatabase)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val postTitle: TextView = itemView.findViewById(R.id.item_title)
        val postDesc: TextView = itemView.findViewById(R.id.item_description)
        val postImg: ShapeableImageView = itemView.findViewById(R.id.item_image)
        val postLike: TextView = itemView.findViewById(R.id.like)
        val postDate: TextView = itemView.findViewById(R.id.time)

        //btn
        val btnLike: LinearLayout = itemView.findViewById(R.id.btn_like)
        val btnMore: ImageView = itemView.findViewById(R.id.btn_more)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = itemList[position]

//        holder.postTitle.text = data.name
        holder.postDesc.text = data.description.shorten(500)
        holder.postLike.text = data.like.toString()

        val uri = Uri.fromFile(data.image)
        holder.postImg.setImageURI(uri)

        holder.btnLike.setOnClickListener {
            data.like += 1
            holder.postLike.text = data.like.toString()
        }

        holder.btnMore.setOnClickListener { onItemClickCallback.onItemClicked(itemList[holder.absoluteAdapterPosition]) }
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
