package com.pipe.listapplication.data.request

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pipe.listapplication.domain.entity.PostEntity
import com.pipe.listapplication.domain.repository.GetListRepository
import com.pipe.listapplication.extensions.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListRepositoryImpl @Inject constructor(
    private val redditListPagingSource: CandidateListPagingSource
) : GetListRepository {

    override fun invokeResponse(): Flow<PagingData<PostEntity>> {

        return Pager(
            config = PagingConfig( pageSize = PAGE_SIZE, enablePlaceholders = false ),
            pagingSourceFactory = { redditListPagingSource }).flow
    }
}

