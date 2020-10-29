package io.github.bukunmiola.pquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment


class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    companion object {
        fun actionSignInFragmentToHomeFragment(): NavDirections =
            ActionOnlyNavDirections(R.id.action_signInFragment_to_homeFragment)

        fun actionSignInFragmentToSignUpFragment(): NavDirections =
            ActionOnlyNavDirections(R.id.action_signInFragment_to_signUpFragment)
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonLogin).setOnClickListener { goToHome() }
        view.findViewById<TextView>(R.id.sign_up).setOnClickListener { goToSignUp() }

    }

    private fun goToSignUp() {
        val action = SignInFragment.actionSignInFragmentToSignUpFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToHome() {
        val action = SignInFragment.actionSignInFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}
