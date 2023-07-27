package com.bedirhandroid.simpleecommerceapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bedirhandroid.simpleecommerceapp.network.ApiService
import com.bedirhandroid.simpleecommerceapp.network.models.dataresponses.product.ProductResponseDataItem
import java.io.IOException
import javax.inject.Inject

class PagingListAdapter @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, ProductResponseDataItem>() {
    override fun getRefreshKey(state: PagingState<Int, ProductResponseDataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResponseDataItem> {
        val position = params.key ?: 0
        return try {
            val response = apiService.getUsers(position)
            LoadResult.Page(
                data = response,
                prevKey = if (position == 0) null else position,
                //Burada response.size == 20 yapmamın nedeni
                // product End-Point'i pagination desteklemiyor.
                //Ama max response size'ı 20. Max size'a ulaşınca bir daha EP'e gitmesin diye
                // böyle bir logic ekledim.
                nextKey = if (response.isEmpty() || response.size == 20) null else position + 5
            )
        } catch (exc: IOException) {
            return LoadResult.Error(exc)
        } catch (exc: IOException) {
            return LoadResult.Error(exc)
        }
    }


}