package com.transactions.homework.util.datetime

import javax.inject.Inject

class TransactionDateTimeFormatter @Inject constructor() : DateFormatter(timePattern = "HH:mm dd.MM.yyyy")
