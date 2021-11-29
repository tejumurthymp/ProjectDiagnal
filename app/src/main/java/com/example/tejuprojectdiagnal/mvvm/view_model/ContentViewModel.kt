package com.example.tejuprojectdiagnal.mvvm.view_model

import androidx.lifecycle.*
import com.example.tejuprojectdiagnal.mvvm.models.ContentModel
import com.example.tejuprojectdiagnal.mvvm.repository.ContentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val contentRepository: ContentRepository
): ViewModel() {

    var page = 0
    var isOrientationChanged = false
    var contentModelLiveData: MutableLiveData<ContentModel> = MutableLiveData<ContentModel>()

    fun initPagedContentList(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        val repoContentModel = contentRepository.getJsonData(page)
        if(contentModelLiveData.value == null){
            repoContentModel?.let { contentModelLiveData.postValue(it) }
        }else {
            repoContentModel?.page?.`content-items`?.content?.let {
                contentModelLiveData.value?.page?.`content-items`?.content?.addAll(it)
                contentModelLiveData.postValue(contentModelLiveData.value)
            }
        }
    }

    fun getPagedContentList() = contentModelLiveData
    fun clearData() {
        contentModelLiveData.value?.page?.`content-items`?.content?.clear()
    }
}