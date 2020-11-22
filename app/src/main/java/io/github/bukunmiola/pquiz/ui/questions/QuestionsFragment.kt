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
import androidx.navigation.findNavController
import io.github.bukunmiola.pquiz.R
import io.github.bukunmiola.pquiz.databinding.QuestionsFragmentBinding
import io.github.bukunmiola.pquiz.model.Questions
import io.github.bukunmiola.pquiz.ui.home.mQuestionCategory

class QuestionsFragment : Fragment() {

    lateinit var questionBinding : QuestionsFragmentBinding
    private lateinit var radioOptions : Array<RadioButton>

    private var counter :Int = 1
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
        questions = mQuestionCategory[args.categoryIndex].questions

        val radioOptionA = questionBinding.radioOptionA
        val radioOptionB = questionBinding.radioOptionB
        val radioOptionC = questionBinding.radioOptionC
        val radioOptionD = questionBinding.radioOptionD
        radioOptions= arrayOf(radioOptionA,radioOptionB,radioOptionC,radioOptionD)


        showQuestions(counter, radioOptions)
        questionBinding.nextBtn.setOnClickListener { nextQuestion() }
        radioOptionA.setOnClickListener { onOptionsClicked(radioOptionA)}
        radioOptionB.setOnClickListener { onOptionsClicked(radioOptionB) }
        radioOptionC.setOnClickListener { onOptionsClicked(radioOptionC) }
        radioOptionD.setOnClickListener { onOptionsClicked(radioOptionD) }

        questionBinding.submissionBtn.setOnClickListener { submit() }

    }

    private fun submit() {
        view?.findNavController()?.navigate(QuestionsFragmentDirections.actionQuestionFragmentToGradeFragment(score,counter))
    }

    private fun nextQuestion() {
        updateView()
        if (counter< questions?.size!!) {
            counter+=1
            showQuestions(counter, this.radioOptions)
            if (counter == questions?.size!! ) {
             questionBinding.nextBtn.visibility = View.GONE
            questionBinding.submissionBtn.visibility = View.VISIBLE}
        }
    }

    private fun showQuestions(count: Int, radioOptions: Array<RadioButton>) {
        question = questions?.get(count-1)

        val questionCount ="Question $count/${questions?.size}"
        questionBinding.questionCounterTv.text = questionCount
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
            radioOption.setBackgroundResource(R.drawable.radio_btn_green)
            radioOption.setTextColor(Color.GREEN)
            radioOption.setButtonDrawable(R.drawable.ic_check_circle)
            radioOption.buttonTintList =  ColorStateList.valueOf(Color.GREEN)
            score++
        } else {
            radioOption.setBackgroundResource(R.drawable.radio_btn_red)
            radioOption.setTextColor(Color.RED)
            radioOption.setButtonDrawable(R.drawable.ic_check_circle)
            radioOption.buttonTintList =  ColorStateList.valueOf(Color.RED)

        }
        for (option in radioOptions){
            option.isClickable = false
            questionBinding.textExplanation.visibility = View.VISIBLE
        }
    }

    private fun updateView() {
        for (radioOption in radioOptions){
            radioOption.setBackgroundResource(R.drawable.radio_btn_bg)
            radioOption.setTextColor(Color.BLACK)
            radioOption.setButtonDrawable(R.drawable.ic_radio_button_unchecked)
            radioOption.buttonTintList =  ColorStateList.valueOf(Color.BLACK)
            radioOption.isClickable = true
            radioOption.isChecked = false
            questionBinding.textExplanation.visibility = View.GONE
        }
    }
}