package io.github.bukunmiola.pquiz.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.bukunmiola.pquiz.R
import io.github.bukunmiola.pquiz.model.PyQuestionsModel

class QuestionsAdapter (questionList : MutableList<PyQuestionsModel?>) :
    RecyclerView.Adapter<HomeViewHolder>() {

    val mQuestionList : MutableList<PyQuestionsModel?> = questionList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.questions_view_model, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mQuestionList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val question: PyQuestionsModel? = mQuestionList.get(position)
        holder.bindData(question)
    }
}