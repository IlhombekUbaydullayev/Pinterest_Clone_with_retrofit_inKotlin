package com.example.pinterest_clone_with_retrofit_inkotlin.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pinterest_clone_with_retrofit_inkotlin.R
import com.example.pinterest_clone_with_retrofit_inkotlin.activity.model.MyCollection

class SearchTitleAdapter(val context: Context, var items: ArrayList<MyCollection>, val search: (String) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_title_search, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoViewHolder)
            holder.bind(position)
    }

    override fun getItemCount() = items.size

    inner class PhotoViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val iv_photo: ImageView = view.findViewById(R.id.iv_search)
        val tv_search_title: TextView = view.findViewById(R.id.tv_search_title)
        val fm_container: FrameLayout = view.findViewById(R.id.fm_search_container)

        fun bind(position: Int){
            val topic = items[position]

            Glide
                .with(view)
                .load(topic.url)
                .error(R.drawable.ic_launcher_background)
                .into(iv_photo)
//
//            Glide
//                .with(view)
//                .load(topic.coverPhoto!!.urls!!.small)
//                .error(R.drawable.ic_launcher_background)
//                .into(iv_photo)

            tv_search_title.text = topic.title

            fm_container.setOnClickListener {
                search.invoke(topic.title)
            }
        }
    }
}