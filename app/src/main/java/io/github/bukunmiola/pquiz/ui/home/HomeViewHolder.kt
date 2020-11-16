package io.github.bukunmiola.pquiz.ui.home

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.bukunmiola.pquiz.R
import io.github.bukunmiola.pquiz.model.PyQuestionsModel
import kotlin.random.Random

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        instructionTv = itemView.findViewById(R.id.text_instruction)
        expressionTv = itemView.findViewById(R.id.text_expression)
        explanationTv = itemView.findViewById(R.id.text_explanation)
        instructionTv?.text = question?.instruction
        expressionTv?.text = question?.expression
        explanationTv?.text = question?.explanation
        optionA = itemView.findViewById(R.id.radio_option_a)
        optionB = itemView.findViewById(R.id.radio_option_b)
        optionC = itemView.findViewById(R.id.radio_option_c)
        optionD = itemView.findViewById(R.id.radio_option_d)

        val pickOption = java.util.Random()
        optionA?.text = question?.incorrect?.get(pickOption.nextInt(3))
        optionB?.text = question?.incorrect?.get(pickOption.nextInt(3))
        optionC?.text = question?.incorrect?.get(pickOption.nextInt(3))

        optionD?.text = question?.correct
    }

    fun onOptionsClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_option_a ->
                    if (checked) {

                    }
                R.id.radio_option_b ->
                    if (checked) {

                    }

                R.id.radio_option_c ->
                    if (checked) {

                    }
                R.id.radio_option_d ->
                    if (checked) {

                    }
            }
        }
    }


}