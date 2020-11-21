package io.github.bukunmiola.pquiz.model

import com.google.gson.annotations.SerializedName

data class PyQuestionsModel(

	@field:SerializedName("questions")
	val questions: List<Questions?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)