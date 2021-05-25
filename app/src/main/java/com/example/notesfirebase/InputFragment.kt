package com.example.notesfirebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.input_fragment_layout.*

class InputFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.input_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_post_imageview.setOnClickListener {
            val fragment = UploadFragment()

            val transaction = activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(fragment.tag)
                ?.replace(R.id.main_frame, fragment)
                ?.commit()
        }

    }
}