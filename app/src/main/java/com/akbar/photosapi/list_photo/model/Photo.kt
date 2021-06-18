package com.akbar.photosapi.list_photo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photo")
data class Photo(
    @ColumnInfo(name = "albumId")
    @SerializedName("albumId") var albumId: Int?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id") var id: Int?,
    @ColumnInfo(name = "title")
    @SerializedName("title") var title: String?,
    @ColumnInfo(name = "url")
    @SerializedName("url") var url: String?,
    @ColumnInfo(name = "thumbnailUrl")
    @SerializedName("thumbnailUrl") var thumbnailUrl: String?
)