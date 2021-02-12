package com.example.githubrepo.network

import com.example.githubrepo.data.Readme
import com.example.githubrepo.data.ReposList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitAPI {

    @GET("search/repositories")
    suspend fun getDataFromApi(@Query("q") query : String, @Query("sort") stars: String, @Query("page") page : Int, @Query("per_page") pages: Int): ReposList

    @GET("repos/{owner}/{repo}/readme")
    suspend fun getReadMeFromApi(@Path("owner") login: String, @Path("repo") name: String) : Readme

}