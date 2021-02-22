package com.mahidhar.superapp.model

import com.mahidhar.superapp.enum.TransactionType

data class Transaction(
    val type:Int,
    val transactor: String,
    val time: String,
    val amount: Int,
)
