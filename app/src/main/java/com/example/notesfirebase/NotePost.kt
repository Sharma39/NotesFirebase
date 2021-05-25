package com.example.notesfirebase

data class NotePost(val userId: String, val postId: String, val caption: String){
    constructor(): this("", "", "")
}