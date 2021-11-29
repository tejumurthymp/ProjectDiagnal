package com.example.tejuprojectdiagnal.mvvm.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel
import com.example.tejuprojectdiagnal.mvvm.pagination.PagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val pagingRepository: PagingRepository
): ViewModel() {

    suspend fun getPagedContentList(context: Context): Flow<PagingData<ContentModel.Page.ContentItems.Content>> {
        return pagingRepository.getFlowPagedData()
    }
}