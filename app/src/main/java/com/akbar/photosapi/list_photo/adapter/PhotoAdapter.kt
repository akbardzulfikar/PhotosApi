package com.akbar.photosapi.list_photo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akbar.photosapi.databinding.ItemPhotoLayoutBinding
import com.akbar.photosapi.list_photo.model.Photo

class PhotoAdapter(private val photo: List<Photo>) :
    RecyclerView.Adapter<PopularPhotoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularPhotoViewHolder {

        val binding = ItemPhotoLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PopularPhotoViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return photo.size
    }

    override fun onBindViewHolder(holder: PopularPhotoViewHolder, position: Int) {
        holder.bind(photo.getOrNull(position))
    }
}