package com.c23ps291.heiwan.data.model

data class User(
    val id: String,
    val name: String?,
    val email: String?,
    val phoneNumber: String?,
    val bookmark: List<Animal>
)
