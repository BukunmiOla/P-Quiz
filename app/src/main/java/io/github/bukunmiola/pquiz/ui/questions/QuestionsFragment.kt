package io.github.bukunmiola.pquiz.ui.questions

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.bukunmiola.pquiz.R
import io.github.bukunmiola.pquiz.databinding.QuestionsFragmentBinding
import io.github.bukunmiola.pquiz.model.Questions
import io.github.bukunmiola.pquiz.ui.home.mQuestionCategory

class QuestionsFragment : Fragment() {

    lateinit var questionBinding : QuestionsFragmentBinding
    private lateinit var radioOptions : Array<RadioButton>

    private var counter :Int = 0
    private var score :Int = 0
    var questions :List<Questions?>? = null
    var question : Questions? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        questionBinding = DataBindingUtil.inflate(inflater,R.layout.questions_fragment, container, false)
        return  questionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = QuestionsFragmentArgs.fromBundle(requireArguments())
        questions = mQuestionCategory[args.questions].questions

        val radioOptionA = questionBinding.radioOptionA
        val radioOptionB = questionBinding.radioOptionB
        val radioOptionC = questionBinding.radioOptionC
        val radioOptionD = questionBinding.radioOptionD
        radioOptions= arrayOf(radioOptionA,radioOptionB,radioOptionC,radioOptionD)


        showQuestions(counter, radioOptions)
        questionBinding.nextBtn.setOnClickListener { nextQuestion(radioOptions) }
        radioOptionA.setOnClickListener { onOptionsClicked(radioOptionA)}
        radioOptionB.setOnClickListener { onOptionsClicked(radioOptionB) }
        radioOptionC.setOnClickListener { onOptionsClicked(radioOptionC) }
        radioOptionD.setOnClickListener { onOptionsClicked(radioOptionD) }

    }

    private fun nextQuestion(radioOptions: Array<RadioButton>) {
        updateView()
        if (counter< questions?.size!!-1) {
            counter+=1
            showQuestions(counter, this.radioOptions)
            if (counter == questions?.size!!-1 ) {
             questionBinding.nextBtn.visibility = View.GONE}
        }
    }

    private fun showQuestions(count: Int, radioOptions: Array<RadioButton>) {
        question = questions?.get(count)

        questionBinding.textInstruction.text = question?.instruction
        questionBinding.textExpression.text = question?.expression
        questionBinding.textExplanation.text = question?.explanation

        for ((optionCount, radioOption) in radioOptions.withIndex()){
            radioOption.text =question?.options?.get(optionCount)
        }

    }

    private fun onOptionsClicked(radioOption: RadioButton) {
        // Check which radio button was clicked
        val selected : String = radioOption.text.toString()
        if (question?.correct == selected) {
            radioOption.setBackgroundColor(Color.GREEN)
            score++
        } else radioOption.setBackgroundColor(Color.RED)
        for (option in radioOptions){
            option.isClickable = false
            questionBinding.textExplanation.visibility = View.VISIBLE
        }
    }

    private fun updateView() {
        for (radioOption in radioOptions){
            radioOption.setBackgroundColor(Color.WHITE)
            radioOption.isClickable = true
            radioOption.isChecked = false
            questionBinding.textExplanation.visibility = View.GONE
        }
    }
}