package com.pipe.listapplication.domain.repository

import androidx.paging.PagingData
import com.pipe.listapplication.domain.entity.PostEntity
import kotlinx.coroutines.flow.Flow

interface GetListRepository {
    fun invokeResponse()
            : Flow<PagingData<PostEntity>>
}