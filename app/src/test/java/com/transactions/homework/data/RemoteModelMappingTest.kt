package com.transactions.homework.data

import com.transactions.homework.data.transaction.TransactionRemote
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.data.transaction.mapper.TransactionToUIModelMapper
import com.transactions.homework.util.buildTestParameters
import com.transactions.homework.util.datetime.TransactionDateTimeFormatter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class RemoteModelMappingTest(private val remoteData: TransactionRemote, private val expectedResult: TransactionUIModel) {

    private lateinit var mapper: TransactionToUIModelMapper

    @Before
    fun setUp() {
        mapper = TransactionToUIModelMapper(TransactionDateTimeFormatter())
    }

    @Test
    fun testParsing() {
        // When
        val actualResult = mapper.mapRemoteToExternal(remoteData)
        // Then
        assertEquals(expectedResult, actualResult)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "remoteData: {0}. expectedResult: {1}")
        fun getParameters() = buildTestParameters(2) {
            append(
                TransactionRemote(
                    id = "1",
                    name = "Gail Fay",
                    accountNumber = "VG37211Q1695809173005334",
                    type = "debit",
                    amount = "672.28",
                    description = "invoice transaction at Lang, Bartell and Zemlak using card ending with ***(...9015) for PKR 894.94 in account ***74548458",
                    date = "2025-04-11T23:47:54.064Z"
                ),
                TransactionUIModel(
                    id = "1",
                    name = "Gail Fay",
                    accountNumber = "VG37211Q1695809173005334",
                    amount = -672.28,
                    description = "invoice transaction at Lang, Bartell and Zemlak using card ending with ***(...9015) for PKR 894.94 in account ***74548458",
                    date = "02:47 12.04.2025"
                )
            )
        }
    }
}
