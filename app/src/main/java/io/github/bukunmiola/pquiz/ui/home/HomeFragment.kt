package io.github.bukunmiola.pquiz.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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
    var customRv: RecyclerView? = null
    var progressDialog: ProgressDialog? = null

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewHolder: HomeViewHolder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customRv = view.findViewById(R.id.questions_rv)
        showQuestions()
    }

    private fun showQuestions(  ){
        progressDialog?.setMessage("Loading....")
        progressDialog?.show()


        val service: GetDataService? = QuizClientInstance.getClientInstance()?.create(GetDataService::class.java)
        service?.getAllPosts()?.enqueue(object : Callback<MutableList<PyQuestionsModel?>?> {
            override fun onResponse(
                call: Call<MutableList<PyQuestionsModel?>?>?,
                response: Response<MutableList<PyQuestionsModel?>?>?
            ) {
                progressDialog?.dismiss()
                generateQuestions(response?.body())
            }

            override fun onFailure(
                call: Call<MutableList<PyQuestionsModel?>?>?,
                t: Throwable
            ) {
                progressDialog?.dismiss()
                Toast.makeText(
                    activity,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun generateQuestions(questions: MutableList<PyQuestionsModel?>?) {
        val adapter = questions?.let { QuestionsAdapter(it) }
        customRv!!.layoutManager = LinearLayoutManager(activity)
        customRv!!.adapter = adapter
    }


}