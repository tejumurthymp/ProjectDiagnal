package com.example.tejuprojectdiagnal.mvvm.helper_class

import android.content.Context
import androidx.annotation.RawRes
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel
import com.google.gson.Gson
import java.io.InputStream
import android.R
import android.util.Log
import org.json.JSONObject


class JsonHelperClass(private val context: Context) {

    companion object{
        private const val TAG = "JsonHelperClass"
    }

    init {
        Log.e(TAG, "called: " )
    }

    private fun convertJsonToString(@RawRes fileId: Int): String {
        val iStream: InputStream = context.resources.openRawResource(fileId)
        val size = iStream.available()
        val buffer = ByteArray(size)
        iStream.read(buffer)
        iStream.close()
        return String(buffer, Charsets.UTF_8)
    }

    fun convertStringToObject( @RawRes fileId: Int): ContentModel {
        Log.e("Data", "convertStringToObject: called" )
        val data = convertJsonToString(fileId)
        Log.e("Data", "convertStringToObject: $data" )
        val jsonObject = JSONObject(data)
        return Gson().fromJson(jsonObject.toString(), ContentModel::class.java)
    }

}