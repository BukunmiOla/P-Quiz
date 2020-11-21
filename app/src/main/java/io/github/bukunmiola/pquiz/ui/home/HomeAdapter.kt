package io.github.bukunmiola.pquiz.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.bukunmiola.pquiz.R
import io.github.bukunmiola.pquiz.model.PyQuestionsModel
import io.github.bukunmiola.pquiz.model.Questions
lateinit var mQuestionCategory : MutableList<PyQuestionsModel>
class HomeAdapter () :
    RecyclerView.Adapter<HomeViewHolder>() {

    lateinit var category: PyQuestionsModel
    lateinit var categoryListener: CategorySelectionListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_view_model, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mQuestionCategory.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        category = mQuestionCategory[position]
        holder.bindData(category)
        holder.itemView.setOnClickListener { categoryListener.onSelectCategory(position) }
    }

    fun setQuestionData(generatedQuestions : MutableList<PyQuestionsModel>?, listener: CategorySelectionListener){
        if (generatedQuestions != null) {
            mQuestionCategory = generatedQuestions
        }
        categoryListener = listener
        notifyDataSetChanged()
    }
}