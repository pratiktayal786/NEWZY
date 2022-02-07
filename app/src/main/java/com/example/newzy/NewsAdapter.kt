package com.example.newzy

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context, val items: List<Articles>): RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_items,parent, false)
        val viewHolder = NewsViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val articles = items[position]
        holder.titleTV.text = articles.title
        Glide.with(context).load(articles.urlToImage).into(holder.NewsImageIV)
        val url: String = articles.url
        holder.itemView.setOnClickListener{
            val builder = CustomTabsIntent.Builder();
            val customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(url));


        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val titleTV = itemView.findViewById<TextView>(R.id.TVTitle)
    val NewsImageIV = itemView.findViewById<ImageView>(R.id.IVNewsImage)
}
