package com.transactions.homework.ui.parcel

import android.os.Parcelable
import com.transactions.homework.data.transaction.TransactionUIModel
import com.transactions.homework.ui.util.buildTestParameters
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ParcelTest(
    private val initialObject: Parcelable,
    private val classLoader: ClassLoader
) {
    @Test
    fun test() {
        // When
        val processedObject = initialObject.parcelAndUnParcel(classLoader)

        // Then
        assertEquals(initialObject, processedObject)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "initialObject: {0}. classLoader: {1}")
        fun getParameters() = buildTestParameters(1) {
            val transactionUiModel1 = TransactionUIModel(
                id = "id1",
                name = "Test Name",
                accountNumber = "TestAccountNumber",
                amount = 123.45,
                description = "Test Description",
                date = "15:01 25.06.2024"
            )

            val transactionUiModel2 = TransactionUIModel(
                id = "id2",
                name = "Test Name2",
                accountNumber = "TestAccountNumber2",
                amount = 1234.56,
                description = "Test Description2",
                date = "15:02 25.06.2024"
            )

            appendWithClassLoader(transactionUiModel1)
            appendWithClassLoader(transactionUiModel2)
        }
    }
}
