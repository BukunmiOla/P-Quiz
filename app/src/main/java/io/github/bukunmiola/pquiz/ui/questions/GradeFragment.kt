package io.github.bukunmiola.pquiz.ui.questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import io.github.bukunmiola.pquiz.R
import io.github.bukunmiola.pquiz.databinding.FragmentGradeBinding

class GradeFragment : Fragment() {
    lateinit var gradeBinding: FragmentGradeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        gradeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_grade, container, false)
        return  gradeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = GradeFragmentArgs.fromBundle(requireArguments())
        val score = args.score
        val noOfQuestions = args.noOfQuestions
        val showGrade ="You scored $score/${noOfQuestions}"
        gradeBinding.scoreTv.text =showGrade

        val grade : Double =  score.toDouble()/noOfQuestions.toDouble()
        decideGrade(grade)

        gradeBinding.homeBtn.setOnClickListener { goToHome() }

    }

    private fun goToHome() {
        view?.findNavController()?.navigate(GradeFragmentDirections.actionGradeFragmentToHomeFragment(""))
    }

    private fun decideGrade(grade: Double) {
        val imageResource :Int
        val note :String
        when {
            grade < 0.5 -> {
                imageResource = R.drawable.ic_encourage
                note = "That was below average, you can do better"
                showNoteAndMedal(imageResource, note)
            }
            grade > 0.5 && grade < 1 -> {
                imageResource = R.drawable.ic_rate
                note = "Wow! that was more than average, you are doing well"
                showNoteAndMedal(imageResource, note)
            }
            grade == 1.0 -> {
                imageResource = R.drawable.ic_gold_medal
                note = "Bravo! You got all questions correctly"
                showNoteAndMedal(imageResource, note)
            }
        }
    }

    private fun showNoteAndMedal(imageResource: Int, note: String) {
        gradeBinding.medalImg.setImageResource(imageResource)
        gradeBinding.noteTv.text = note
    }
}