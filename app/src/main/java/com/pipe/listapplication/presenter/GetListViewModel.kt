package com.pipe.listapplication.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pipe.listapplication.domain.GetListUseCase
import com.pipe.listapplication.domain.entity.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase,
) : ViewModel() {

    fun getFileDataStream(): Flow<PagingData<PostEntity>> =
        getListUseCase.invokeRequest().cachedIn(viewModelScope)
}