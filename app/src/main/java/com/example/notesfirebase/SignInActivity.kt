package com.example.notesfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class SignInActivity : AppCompatActivity() {

//    private val notesReference = FirebaseDatabase.getInstance().reference.child("Notes")
//    private val userReference = FirebaseDatabase.getInstance().reference.child("Users")

    private val signupFragment = SignupFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //activity_main.xml is linked with this activity
        setContentView(R.layout.activity_main)


        signin_button.setOnClickListener {
            val email = email_edittext.text.toString().trim()
            val password = password_edittext.text.toString().trim()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if(task.isSuccessful){
                        if(FirebaseAuth.getInstance().currentUser?.isEmailVerified == true){
                            openHomePage()
                        } else {
                            showVerificationDialog(email)
                        }
                    } else {
                        showError(task)
                    }
                }

        }

        signup_textview.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                ).addToBackStack(signupFragment.tag)
                .replace(R.id.signup_frame, signupFragment)
                .commit()
        }
    }

    fun signupUser(signUpUser: SignUpUser) {

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(signUpUser.email, signUpUser.password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    //User created
                    if (FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                        openHomePage()
                    } else {
                        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                        showVerificationDialog(signUpUser.email)

                    }

                } else {
                    Log.d("TAG_X", task.exception.toString())
                    showError(task)
                }

            }


    }

    private fun showError(task: Task<AuthResult>) {
        Snackbar.make(
            root,
            "Error: ${task.exception?.localizedMessage}",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun showVerificationDialog(signUpUser: String) {
        AlertDialog.Builder(
            ContextThemeWrapper(
                this,
                R.style.Theme_AppCompat_Dialog
            )
        )
            .setTitle(getString(R.string.confirmation_title))
            .setMessage("Please check your email: ${signUpUser}. A confirmation email has been sent.")
            .setPositiveButton(getString(R.string.okay)) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun openHomePage() {
        startActivity(Intent(this, HomePageActivity::class.java).also {
            it.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }
}