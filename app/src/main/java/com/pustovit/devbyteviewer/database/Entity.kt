package com.pustovit.devbyteviewer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pustovit.devbyteviewer.domain.Video


@Entity(tableName = "videos_table")
data class DatabaseVideo(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val title: String,
    val description: String,
    val updated: String,
    val thumbnail: String
)

fun List<DatabaseVideo>.asDomainModel(): List<Video> {
    return map {
        Video(
            title = it.title,
            description = it.description,
            url = it.url,
            thumbnail = it.thumbnail,
            updated = it.updated
        )
    }
}