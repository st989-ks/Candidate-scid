package com.pipe.listapplication.domain

import androidx.paging.PagingData
import com.pipe.listapplication.domain.entity.PostEntity
import com.pipe.listapplication.domain.repository.GetListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val getListRepository: GetListRepository
) {
    fun invokeRequest()
            : Flow<PagingData<PostEntity>> {
        return getListRepository.invokeResponse()
    }
}