package com.example.tejuprojectdiagnal.mvvm.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class PagingRepository @Inject constructor() {

    suspend fun getFlowPagedData(): Flow<PagingData<ContentModel.Page.ContentItems.Content>> {
        return Pager(PagingConfig(1)) {
            PagingDataSource()
        }.flow
    }
}