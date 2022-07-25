package com.pipe.listapplication.data.request

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pipe.listapplication.data.mappers.toPostEntity
import com.pipe.listapplication.domain.entity.PostEntity
import com.pipe.listapplication.extensions.APP_TAG
import com.pipe.listapplication.extensions.SharedPrefs
import javax.inject.Inject

class CandidateListPagingSource @Inject constructor(
    private val redditApi: CandidateApi,
    private val sharedPrefs: SharedPrefs
) : PagingSource<Int, PostEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostEntity> {
        Log.i(APP_TAG, sharedPrefs.getAfter().toString())
        try {
            val position = params.key ?: 1
            val response = redditApi.getData(sharedPrefs.getAfter())
            sharedPrefs.setAfter(response.result.current_page +1)
            return LoadResult.Page(
                data = response.result.data.toPostEntity(),
                prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1
            )
        } catch (throwable: Throwable) {
            val errorThrowable = Throwable(
                "Something Went Wrong Please Try again later.",
                Throwable("Processing Error")
            )
            return LoadResult.Error(errorThrowable)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PostEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}