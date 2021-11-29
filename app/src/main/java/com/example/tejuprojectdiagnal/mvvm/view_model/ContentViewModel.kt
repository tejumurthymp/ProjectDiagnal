package com.example.tejuprojectdiagnal.mvvm.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel
import com.example.tejuprojectdiagnal.mvvm.pagination.PagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val pagingRepository: PagingRepository
): ViewModel() {

    suspend fun getPagedContentList(): Flow<PagingData<ContentModel.Page.ContentItems.Content>> {
        return pagingRepository.getFlowPagedData().cachedIn(viewModelScope)
    }
}