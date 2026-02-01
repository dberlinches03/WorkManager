package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.Date

class BackupWorker(appContext: Context, params: WorkerParameters) :
    Worker(appContext, params) {

    override fun doWork(): Result {
        return try {
            val backupData = "Backup generado el: ${Date()}\nDatos: ejemplo de copia de seguridad"
            val fileName = "backup_${Date().time}.txt"

            applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
                output.write(backupData.toByteArray())
            }
            Log.d("BACKUP", "Copia de seguridad creada: $fileName")
            Result.success()
        } catch (e: Exception) {
            Log.e("BACKUP", "Error al crear la copia: ${e.message}")
            Result.failure()
        }
    }
}