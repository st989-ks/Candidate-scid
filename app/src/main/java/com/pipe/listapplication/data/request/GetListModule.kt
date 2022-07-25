package com.pipe.listapplication.data.request

import androidx.paging.ExperimentalPagingApi
import com.pipe.listapplication.domain.repository.GetListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class GetListModule {

    @Provides
    fun provideListApi(
        retrofit: Retrofit,
    ): CandidateApi {
        return retrofit.create(CandidateApi::class.java)
    }

    @ExperimentalPagingApi
    @Provides
    fun bindListDataSource(
        redditListPagingSource: CandidateListPagingSource
    ): GetListRepository =
        GetListRepositoryImpl(redditListPagingSource)
}