package com.example.clean_architecture.domain.model

data class Note(
    val id : Int,
    val title : String,
    val description : String,
    val createdAt : Long

)