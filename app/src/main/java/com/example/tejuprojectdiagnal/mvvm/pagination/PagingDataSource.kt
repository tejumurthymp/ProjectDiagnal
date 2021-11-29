package com.example.tejuprojectdiagnal.mvvm.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tejuprojectdiagnal.R
import com.example.tejuprojectdiagnal.mvvm.helper_class.JsonHelperClass
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel
import javax.inject.Inject

class PagingDataSource: PagingSource<Int, ContentModel.Page.ContentItems.Content>() {

    companion object {
        private const val TAG = "PagingDataSource"
    }

    @Inject
    lateinit var jsonHelperClass: JsonHelperClass

    override fun getRefreshKey(state: PagingState<Int, ContentModel.Page.ContentItems.Content>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            Log.e(TAG, "getRefreshKey: called" )
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContentModel.Page.ContentItems.Content> {
        return try {
            Log.e(TAG, "load: called" )
            val nextPage = params.key ?: 0
            val data = jsonHelperClass.convertStringToObject(R.raw.content_listing_page_1).page?.`content-items`?.content as List<ContentModel.Page.ContentItems.Content>
            Log.e(TAG, "load: data: $data" )

            val response: List<ContentModel.Page.ContentItems.Content> = data /*(
                    if(nextPage == 0) {
                        jsonHelperClass.convertStringToObject(R.raw.content_listing_page_1).page?.`content-items`?.content
                    } else if(nextPage == 1){
                        jsonHelperClass.convertStringToObject(R.raw.content_listing_page_2).page?.`content-items`?.content
                    } else{
                        jsonHelperClass.convertStringToObject(R.raw.content_listing_page_3).page?.`content-items`?.content
                    })*/


            Log.e(TAG, "load: $response" )

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = if (response.isEmpty()) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}