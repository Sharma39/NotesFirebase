package com.example.notesfirebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.info_fragment_layout.*
import kotlinx.android.synthetic.main.input_fragment_layout.*

class InfoFragment: Fragment() {

    private val postAdapter = PostAdapter()

//    private val postReference = FirebaseDatabase.getInstance().reference.child("NotePosts")
      private val postReference = Firebase.database.reference.child("NotePosts")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.info_fragment_layout, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feed_recyclerview.adapter = postAdapter
//        val snapHelper: SnapHelper = LinearSnapHelper()
//        snapHelper.attachToRecyclerView(feed_recyclerview)

        val postList = mutableListOf<NotePost>()
        postReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (convSnapshot in snapshot.children) {
                    val conv = convSnapshot.getValue(NotePost::class.java)
                    postList.add(conv!!)
                }
//                snapshot.children.forEach {
//                    it.getValue(NotePost::class.java)?.let { note ->
//                        postList.add(note)
//                    }
//                }
                 postAdapter.posts = postList
            }

            override fun onCancelled(error: DatabaseError) {
                //we never make mistakes..
            }
        })
    }
}