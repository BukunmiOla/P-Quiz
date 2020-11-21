package io.github.bukunmiola.pquiz.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.github.bukunmiola.pquiz.R


class SignInFragment : Fragment() {
    private var mAuth: FirebaseAuth? = null

    private lateinit var emailEt : TextInputEditText
    private lateinit var passwordEt : TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        view.findViewById<Button>(R.id.buttonLogin).setOnClickListener { signIn() }
        view.findViewById<TextView>(R.id.sign_up).setOnClickListener { goToSignUp() }

    }

    private fun goToSignUp() {
        view?.findNavController()?.navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }

    private fun signIn() {

        emailEt = view?.findViewById(R.id.email)!!
        passwordEt = view?.findViewById(R.id.password)!!

        val email = emailEt.text.toString()
        val password = passwordEt.text.toString()

        if (areTextsValid()) {


        activity?.let {
            mAuth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(
                    it
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = mAuth!!.currentUser
                        goToHome()
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            activity, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }

                }
        }}

    }
    private fun areTextsValid(): Boolean {
        val allEditTexts = arrayOf(emailEt, passwordEt)
        for (textInputEditText in allEditTexts) {
            if (textInputEditText.text?.isEmpty()!!) {
                textInputEditText.error = "Field cannot be empty"
                return false
            }
        }
        if (!isEmailValid(emailEt)) {
            emailEt.error = "Invalid email"
            return false
        }
        return true
    }

    private fun isEmailValid(emailEt: TextInputEditText): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailEt.text.toString()).matches()
    }

    private fun goToHome() {
        view?.findNavController()?.navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment("yo"))
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

    }
}
