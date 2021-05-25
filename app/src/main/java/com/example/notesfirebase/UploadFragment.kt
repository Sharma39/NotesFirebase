package com.example.notesfirebase

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaCas
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.feed_item_layout.*
import kotlinx.android.synthetic.main.upload_fragment_layout.*
import java.io.ByteArrayOutputStream

class UploadFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.upload_fragment_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        upload_button.setOnClickListener {

            val ref = FirebaseDatabase.getInstance().reference.child("NotePosts")
            val key = ref.push().key ?: ""
            val post = NotePost(
                FirebaseAuth.getInstance().currentUser.toString(),
                key,
                feed_caption.text.toString().trim()
            )
            ref.child(key).setValue(post)
            Toast.makeText(requireContext(), "Success!!", Toast.LENGTH_SHORT).show()

        }

    }
}