package com.example.propertymanagementapp.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Environment.DIRECTORY_PICTURES
import android.provider.MediaStore
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object FileUtils {
    fun uri2File(uri: Uri, context: Context): File? {
       val bitmap: Bitmap= BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
        File(context.getExternalFilesDir(null)?.path).let {
            if (!it.exists()) {
                it.mkdirs()
            }
        }
        val file: File = File(context.filesDir, "${randomFileName()}.jpg")
        if (file.exists()) {
            file.delete()
        }
        val out: FileOutputStream
        return try {
            out = FileOutputStream(file)
            // make the format be JPEG.
            // PNG format cannot be in the gallery
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out)) {
                out.flush()
                out.close()
            }
            file
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun randomFileName(): String {
        var randoms = ""
        for (i in 1..5) {
            randoms += (0..10).random()
        }
        return SimpleDateFormat("yyyyMMddHHmmss").format(Date(System.currentTimeMillis())) + randoms
    }


}