package com.artemiod.challengeandroidssr.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.artemiod.challengeandroidssr.R
import com.artemiod.challengeandroidssr.domain.model.CatItem
import com.squareup.picasso.Picasso

class CatAdapter: RecyclerView.Adapter<CatAdapter.ViewHolder>() {

    lateinit var onItemClickListener: (img: String) -> Unit
    private var images: List<CatItem> = emptyList()

    fun setImages(listImages: List<CatItem>) {
        images = listImages
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.imageItemList)

        fun bind(imageCat: CatItem) {
            Picasso.get().load(imageCat.img).into(imageView)

            view.setOnClickListener {
                onItemClickListener(imageCat.img)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount()= images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }
}