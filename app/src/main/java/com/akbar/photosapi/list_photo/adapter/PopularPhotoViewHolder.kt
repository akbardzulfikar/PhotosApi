package com.akbar.photosapi.list_photo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.akbar.photosapi.databinding.ItemPhotoLayoutBinding
import com.akbar.photosapi.list_photo.model.Photo
import com.bumptech.glide.Glide

class PopularPhotoViewHolder(
    private val binding: ItemPhotoLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo?) {
        Glide.with(binding.root)
            .load(photo?.thumbnailUrl)
            .into(binding.ivBackdrop)

        binding.tvTitle.text = photo?.title ?: ""

    }
}