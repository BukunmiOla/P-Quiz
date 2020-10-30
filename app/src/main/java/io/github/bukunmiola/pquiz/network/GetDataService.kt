package io.github.bukunmiola.pquiz.network

import io.github.bukunmiola.pquiz.model.PyQuestionsModel
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {

    @GET("/P-Quiz/pyQuestions.json")
    fun getAllPosts(): Call<MutableList<PyQuestionsModel?>?>?

}