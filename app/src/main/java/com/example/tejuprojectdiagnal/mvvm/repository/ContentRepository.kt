package com.example.tejuprojectdiagnal.mvvm.repository

import com.example.tejuprojectdiagnal.R
import com.example.tejuprojectdiagnal.mvvm.helper_class.JsonHelperClass
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ContentRepository @Inject constructor(
    private val jsonHelperClass: JsonHelperClass
){

    companion object {
        private const val TAG = "ContentRepository"
    }

    suspend fun getJsonData(page: Int): ContentModel? {
        val rawFileId = when(page){
            0 -> R.raw.content_listing_page_1
            1 -> R.raw.content_listing_page_2
            2 -> R.raw.content_listing_page_3
            else -> null
        }

        return rawFileId?.let { jsonHelperClass.convertStringToObject(it) }
    }
}