package com.example.gamesapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gamesapp.models.GameModel
import com.example.gamesapp.repositories.GamesRepository

/***
 * Project: Games App
 * Package: com.example.gamesapp.data
 * Created by Kevin Daniel Flores Nataren
 * File created at 05/May/2025 at 19:30
 * All rights reserved 2025.
 **/
class GamesDataSource(private val repo: GamesRepository): PagingSource<Int, GameModel>() {
    override fun getRefreshKey(state: PagingState<Int, GameModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repo.getGamesPaginated(nextPageNumber, 3) ?: emptyList()
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if(response.isNotEmpty()) nextPageNumber + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}