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
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.github.bukunmiola.pquiz.R
import java.util.regex.Pattern


class SignUpFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null
    private lateinit var emailEt  : TextInputEditText
    private lateinit var nameEt  : TextInputEditText
    private lateinit var  dateOfBirthEt : TextInputEditText
    private lateinit var passwordEt  : TextInputEditText
    private lateinit var confirmPasswordEt  : TextInputEditText


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

        mAuth = FirebaseAuth.getInstance()

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
        emailEt = view?.findViewById(R.id.mail)!!
        nameEt = view?.findViewById(R.id.fullName)!!
        dateOfBirthEt = view?.findViewById(R.id.date_of_birth)!!
        passwordEt = view?.findViewById(R.id.password)!!
        confirmPasswordEt = view?.findViewById(R.id.confirm_password)!!

        val email = emailEt.text.toString()
        val password = passwordEt.text.toString()

        if (areTextsValid()) {
            activity?.let {
                mAuth?.createUserWithEmailAndPassword(email, password)
                    ?.addOnCompleteListener(
                        it
                    ) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user: FirebaseUser? = mAuth!!.currentUser
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
                    }
            }
        }}
    private fun areTextsValid(): Boolean {
        val allEditTexts = arrayOf(
            emailEt,
            nameEt,
            confirmPasswordEt,
            passwordEt
        )
        for (textInputEditText in allEditTexts) {
            if (textInputEditText.text?.isEmpty()!!) {
                textInputEditText.error = "Field cannot be empty"
                return false
            }
        }

        if (isNameInvalid(nameEt)) {
            nameEt.error = "Invalid input!"
            return false
        } else if (!isEmailValid(emailEt)) {
            emailEt.error = "Invalid email"
            return false
        }else if (passwordEt.text.toString().length < 8) {
            passwordEt.error = "password must be at least 8 characters"
            return false
        } else if (passwordEt.text.toString() != confirmPasswordEt.text.toString()) {
            confirmPasswordEt.error = "password do not match"
            return false
        } else if (isDateInvalid(dateOfBirthEt)) {
            dateOfBirthEt.error = "Invalid input!"
            return false
        }


        return true
    }

    private fun isEmailValid(emailEt: TextInputEditText): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailEt.text.toString()).matches()
    }
    private fun isNameInvalid(nameEt: TextInputEditText): Boolean {
        return !Pattern.compile("[a-z A-z]{0,256}").matcher(nameEt.text.toString()).matches()
    }


    private fun isDateInvalid(dateOfBirthEt: TextInputEditText): Boolean {
        return !Pattern.compile("[/0-9]{0,10}").matcher(dateOfBirthEt.text.toString()).matches()
    }

}