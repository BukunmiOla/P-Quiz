package io.github.bukunmiola.pquiz.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment.findNavController
import io.github.bukunmiola.pquiz.R
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonCancellable.start

class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    companion object {

        fun actionSplashInFragmentToSignInFragment(): NavDirections =
            ActionOnlyNavDirections(R.id.action_splashFragment_to_signInFragment)
    }

//   @InternalCoroutinesApi
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<View>(R.id.text_logo).let { img ->
//           // Setting up a spring animation to animate the view’s translationY property with the final
//           // spring position at 0.
//           SpringAnimation(img, DynamicAnimation.TRANSLATION_Y, 0f)
//            start()
//       }
        Handler().postDelayed({
            val action =
                actionSplashInFragmentToSignInFragment()
            findNavController(this).navigate(action)
        }, 3500)
    }
}
