package com.transactions.homework.data.transaction

import com.google.gson.annotations.SerializedName

class TransactionRemote(
    @SerializedName("id")
    val id: String?,
    @SerializedName("counterPartyName")
    val name: String?,
    @SerializedName("counterPartyAccount")
    val accountNumber: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("date")
    val date: String?
)
