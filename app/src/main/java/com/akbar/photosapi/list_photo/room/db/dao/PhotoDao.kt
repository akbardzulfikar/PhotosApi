package com.akbar.photosapi.list_photo.room.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.akbar.photosapi.list_photo.model.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(photo: Photo)

    @Delete
    suspend fun delete(photo: Photo)

    @Query("SELECT * FROM photo WHERE id = :photoId")
    suspend fun getPhoto(photoId: Int?): Photo?

    @Query("SELECT * FROM photo ORDER BY id DESC")
    fun getAll(): DataSource.Factory<Int, Photo>

    @Query("SELECT * FROM photo WHERE title LIKE '%' || :title || '%'")
    fun searchTitle(title: String?): List<Photo>?
}