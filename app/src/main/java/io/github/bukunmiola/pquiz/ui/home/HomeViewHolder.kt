package io.github.bukunmiola.pquiz.ui.home

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.bukunmiola.pquiz.R
import io.github.bukunmiola.pquiz.model.PyQuestionsModel
import kotlin.random.Random

class HomeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    // TODO: Implement the ViewModel
    private var instructionTv: TextView? = null
    private var expressionTv: TextView? = null
    private var explanationTv: TextView? = null

    private var radioGroup : RadioGroup? = null
    private var optionA : RadioButton? = null
    private var optionB : RadioButton? = null
    private var optionC : RadioButton? = null
    private var optionD : RadioButton? = null


    fun bindData(question: PyQuestionsModel?) {
        instructionTv?.text = question?.instruction
        expressionTv?.text = question?.expression
        explanationTv?.text = question?.explanation
        val pickOption : Random?= null
        if (pickOption != null) {
            optionA?.text = question?.incorrect?.get(pickOption.nextInt(3))
            optionB?.text = question?.incorrect?.get(pickOption.nextInt(3))
            optionC?.text = question?.incorrect?.get(pickOption.nextInt(3))

        }

        optionD?.text = question?.correct
    }
//    fun onOptionsClicked(view: View) {
//        if (view is RadioButton) {
//            // Is the button now checked?
//            val checked = view.isChecked
//
//            // Check which radio button was clicked
//            when (view.getId()) {
//                R.id.radio_pirates ->
//                    if (checked) {
//                        // Pirates are the best
//                    }
//                R.id.radio_ninjas ->
//                    if (checked) {
//                        // Ninjas rule
//                    }
//            }
//        }
//    }


}