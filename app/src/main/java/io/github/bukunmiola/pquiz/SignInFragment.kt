package io.github.bukunmiola.pquiz

import android.R.attr.password
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


class SignInFragment : Fragment() {
    private var mAuth: FirebaseAuth? = null
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
        mAuth = FirebaseAuth.getInstance();
        view.findViewById<Button>(R.id.buttonLogin).setOnClickListener { signIn() }
        view.findViewById<TextView>(R.id.sign_up).setOnClickListener { goToSignUp() }

    }

    private fun goToSignUp() {
        val action = SignInFragment.actionSignInFragmentToSignUpFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun signIn() {
        val email = view?.findViewById<TextInputEditText>(R.id.mail)?.text.toString()
        val password = view?.findViewById<TextInputEditText>(R.id.password)?.text.toString()

        activity?.let {
            mAuth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(
                    it,
                    OnCompleteListener<AuthResult?> { task ->
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

                        // ...
                    })
        }
        goToHome()


    }

    private fun goToHome() {
        val action = actionSignInFragmentToHomeFragment()
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
}
