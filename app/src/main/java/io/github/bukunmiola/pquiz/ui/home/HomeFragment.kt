package io.github.bukunmiola.pquiz.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.bukunmiola.pquiz.R
import io.github.bukunmiola.pquiz.model.PyQuestionsModel
import io.github.bukunmiola.pquiz.network.GetDataService
import io.github.bukunmiola.pquiz.network.QuizClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var customRv: RecyclerView
    private lateinit var adapter : HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customRv = view.findViewById(R.id.categories_rv)
        customRv.layoutManager = LinearLayoutManager(activity)
        showQuestions()
    }
    private fun showQuestions(  ){

        val service: GetDataService = QuizClientInstance.getClientInstance().create(GetDataService::class.java)
        service.getAllPosts().enqueue(object : Callback<MutableList<PyQuestionsModel>> {
            override fun onResponse(
                call: Call<MutableList<PyQuestionsModel>>,
                response: Response<MutableList<PyQuestionsModel>>
            ) {
                generateQuestions(response.body())
            }

            override fun onFailure(
                call: Call<MutableList<PyQuestionsModel>>,
                t: Throwable
            ) {
                Toast.makeText(
                    activity,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun generateQuestions(questions: MutableList<PyQuestionsModel>?) {
        val listener :CategorySelectionListener = object : CategorySelectionListener {
            override fun onSelectCategory(category: Int) {
                view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToQuestionFragment(category))
            }
        }
        adapter = HomeAdapter()
        adapter.setQuestionData(questions,listener)
        customRv.adapter = adapter
    }

}