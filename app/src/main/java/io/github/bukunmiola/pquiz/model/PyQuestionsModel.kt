package io.github.bukunmiola.pquiz.model

import com.google.gson.annotations.SerializedName

data class PyQuestionsModel(

	@field:SerializedName("correct")
	val correct: String? = null,

	@field:SerializedName("expression")
	val expression: String? = null,

	@field:SerializedName("incorrect")
	val incorrect: List<String?>? = null,

	@field:SerializedName("instruction")
	val instruction: String? = null,

	@field:SerializedName("Explanation")
	val explanation: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)