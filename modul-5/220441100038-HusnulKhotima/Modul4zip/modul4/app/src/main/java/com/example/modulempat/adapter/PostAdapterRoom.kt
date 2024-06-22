package com.example.modulempat.adapter

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.modulempat.R
import com.example.modulempat.room.PostDatabase
import com.example.modulempat.room.PostViewModel
import com.google.android.material.imageview.ShapeableImageView

class PostAdapterRoom(private var postList: List<PostDatabase>, private val postViewModel: PostViewModel) :
    RecyclerView.Adapter<PostAdapterRoom.PostViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    private var isliked = false
    private var iscomment = false


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onMoreClicked(data: PostDatabase, position: Int)
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val postTitle: TextView = itemView.findViewById(R.id.post_title)
        val postDesc: TextView = itemView.findViewById(R.id.deskripsi_konten)
        val postImg: ShapeableImageView = itemView.findViewById(R.id.gambar_konten)
        val postLike: TextView = itemView.findViewById(R.id.likefield)
        val postDate: TextView = itemView.findViewById(R.id.textView5)
        val iconLike: ImageView = itemView.findViewById(R.id.like_button)
        val postkomen: TextView = itemView.findViewById(R.id.komenfield)
        val iconkomen: ImageView = itemView.findViewById(R.id.comment_button)

        //btn
        val btnLike: LinearLayout = itemView.findViewById(R.id.like_button)
        val btnMore: ImageView = itemView.findViewById(R.id.btn_menu)
        val btnShare: ImageView = itemView.findViewById(R.id.btnshare)
        val btnkomen: ImageView = itemView.findViewById(R.id.comment_button)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.konten, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val data = postList[position]

//        holder.postTitle.text = data.name
        holder.postDesc.text = data.description.shorten(500)
        holder.postLike.text = data.like.toString()
        holder.postkomen.text = data.komen.toString()

        val uri = Uri.fromFile(data.image)
        holder.postImg.setImageURI(uri)

        holder.btnLike.setOnClickListener {
            isliked = !isliked
//            holder.iconLike.setImageResource(if (stateFav) R.drawable.likeactive else R.drawable.like)
            if (isliked){
                data.like += 1
            }else{
                data.like -= 1
            }
            holder.postLike.text = data.like.toString()

            holder.btnkomen.setOnClickListener {
                iscomment = !iscomment
//            holder.iconLike.setImageResource(if (stateFav) R.drawable.likeactive else R.drawable.like)
                if (iscomment) {
                    data.komen += 1
                } else {
                    data.komen -= 1
                }
                holder.postkomen.text = data.komen.toString()

                // Mengatur aksi ketika button share diklik
                holder.btnShare.setOnClickListener {
                    val context = holder.itemView.context
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "Lihat postingan ini: ${data.description}")
                        `package` = "com.whatsapp"
                    }

                    // Memeriksa apakah aplikasi WhatsApp terinstall
                    if (shareIntent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(shareIntent)
                    } else {
                        Toast.makeText(
                            context,
                            "WhatsApp tidak terpasang di perangkat Anda.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }
        // Mengatur aksi ketika button more diklik
        holder.btnMore.setOnClickListener {
            onItemClickCallback.onMoreClicked(postList[holder.absoluteAdapterPosition], holder.absoluteAdapterPosition)
        }
    }

    override fun getItemCount(): Int = postList.size

    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }

}