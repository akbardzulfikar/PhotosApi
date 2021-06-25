package com.akbar.photosapi.list_photo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.akbar.photosapi.R
import com.akbar.photosapi.databinding.ItemPhotoLayoutBinding
import com.akbar.photosapi.list_photo.model.PhotoResponse
import com.squareup.picasso.Picasso

class PopularPhotoViewHolder(
    private val binding: ItemPhotoLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: PhotoResponse?) {
        Picasso.get().load(photo?.thumbnailUrl).placeholder(R.drawable.ic_launcher_background).into(binding.ivBackdrop)
//        Glide.with(binding.root)
//            .load(photo?.thumbnailUrl)
//            .override(100, 100)
//            .into(binding.ivBackdrop)

        binding.tvTitle.text = photo?.title ?: ""

    }
}