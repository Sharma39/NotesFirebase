package com.example.notesfirebase

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.signup_fragment_layout.*

class SignupFragment: Fragment() {

    lateinit var cancelIntent: Intent
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.signup_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        signup_button.setOnClickListener {
            val email = su_email_edittext.text.toString().trim()
            val password = su_password_edittext.text.toString().trim()

           //Call to SigninActivity's signupUser method          //data class
            (requireActivity() as SignInActivity).signupUser(SignUpUser(email, password))
        }
        

        
    }
}