package io.github.bukunmiola.pquiz.model

import com.google.gson.annotations.SerializedName

data class Questions(

    @field:SerializedName("correct")
    val correct: String? = null,

    @field:SerializedName("expression")
    val expression: String? = null,

    @field:SerializedName("options")
    val options: List<String?>? = null,

    @field:SerializedName("instruction")
    val instruction: String? = null,

    @field:SerializedName("Explanation")
    val explanation: String? = null

)