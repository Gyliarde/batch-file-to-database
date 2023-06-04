package com.involves.migratefiletodatabase.domain

import java.util.*

data class Pessoa(
    val id: Int,
    val nome: String,
    val email: String,
    val dataNascimento: Date,
    val idade: Int
)
