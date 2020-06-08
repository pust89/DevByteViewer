package com.pustovit.devbyteviewer.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.pustovit.devbyteviewer.database.VideoDatabase
import com.pustovit.devbyteviewer.repository.VideosRepository
import retrofit2.HttpException


class RefreshDataWork(appContext: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(appContext, workerParameters) {
    companion object {
        const val WORK_NAME = "DevByteApplicationRefreshDataWork"

    }

    override suspend fun doWork(): Result {

        val database = VideoDatabase.getInstance(applicationContext)
        val videosRepository = VideosRepository(database)

        try {
            videosRepository.refreshVideos()
            return Result.success()
        } catch (e: HttpException) {
            return Result.failure()
        }
    }
}