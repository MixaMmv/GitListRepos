package com.example.gitlistrepos.data.api

import com.example.gitlistrepos.data.api.model.CommitsModel
import com.example.gitlistrepos.data.api.model.ReposModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ReposApi {
    @GET("repositories")
    @Headers("accept: application/vnd.github.v3+json")
    suspend fun getRepos(
        @Query("since") since: Int = 0
    ): List<ReposModel>

    @GET("repos/{owner}/{name}/commits")
    suspend fun getCommits(
        @Path("owner") owner: String = "mojombo",
        @Path("name") name: String = "grit",
        @Query("per_page") count: Int = 1
    ): List<CommitsModel>

}