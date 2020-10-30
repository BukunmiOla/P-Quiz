package io.github.bukunmiola.pquiz.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.github.bukunmiola.pquiz.R


class SignUpFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null

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

        mAuth = FirebaseAuth.getInstance();

        view.findViewById<Button>(R.id.button_signUp).setOnClickListener { signUp() }
        view.findViewById<TextView>(R.id.login).setOnClickListener { goToSignIn() }

    }

    private fun goToSignIn() {
        val action =
            actionSignUpFragmentToSignInFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToHome() {
        val action =
            actionSignUpFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

    }
    private  fun signUp(){
        val email = view?.findViewById<TextInputEditText>(R.id.mail)?.text.toString()
        val password = view?.findViewById<TextInputEditText>(R.id.password)?.text.toString()
        activity?.let {
            mAuth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(
                    it,
                    OnCompleteListener<AuthResult?> { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user: FirebaseUser? = mAuth!!.getCurrentUser()
                            updateUI(user)
                            goToHome()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                activity, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                            updateUI(null)
                        }

                        // ...
                    })
        }
    }
}