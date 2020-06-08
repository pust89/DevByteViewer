package com.pustovit.devbyteviewer.repository

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.pustovit.devbyteviewer.database.VideoDatabase
import com.pustovit.devbyteviewer.database.asDomainModel
import com.pustovit.devbyteviewer.domain.Video
import com.pustovit.devbyteviewer.network.Network
import com.pustovit.devbyteviewer.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository(private val database: VideoDatabase) {

    val videos: LiveData<List<Video>> =
        Transformations.map(database.getDatabaseVideoDAO().getVideos(),
            Function { it.asDomainModel() })


    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playList = Network.devbytesService.getPlayListAsync().await()
            database.getDatabaseVideoDAO().insertAll(*playList.asDatabaseModel())

        }
    }
}