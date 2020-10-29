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


class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    companion object {
        fun actionSignUpFragmentToHomeFragment(): NavDirections =
            ActionOnlyNavDirections(R.id.action_signUpFragment_to_homeFragment)

        fun actionSignUpFragmentToSignInFragment(): NavDirections =
            ActionOnlyNavDirections(R.id.action_signUpFragment_to_signInFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_signUp).setOnClickListener { goToHome() }
        view.findViewById<TextView>(R.id.login).setOnClickListener { goToSignIn() }

    }

    private fun goToSignIn() {
        val action = SignUpFragment.actionSignUpFragmentToSignInFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToHome() {
        val action = SignUpFragment.actionSignUpFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}