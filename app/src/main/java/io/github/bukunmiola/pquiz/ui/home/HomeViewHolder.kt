package io.github.bukunmiola.pquiz.ui.home

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.bukunmiola.pquiz.R
import io.github.bukunmiola.pquiz.model.PyQuestionsModel

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // TODO: Implement the ViewModel
    private var nameTv: TextView? = null


    fun bindData(question: PyQuestionsModel?) {
        nameTv = itemView.findViewById(R.id.name_tv)
        nameTv?.text = question?.name
        }

}