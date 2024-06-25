package com.transactions.homework.ui.util

fun buildTestParameters(
    paramsSetSize: Int,
    block: TestParameters.() -> Unit
): Collection<Array<*>> =
    TestParametersImpl(paramsSetSize, mutableListOf()).apply {
        block(this)
    }

interface TestParameters {
    fun append(vararg params: Any?)

    fun appendWithClassLoader(vararg params: Any)

    fun appendParametersSet(vararg params: Any?) {
        append(*params)
    }
}

private class TestParametersImpl(
    private val paramsSetSize: Int,
    private val parameters: MutableList<Array<*>>
) : TestParameters, List<Array<*>> by parameters {
    override fun append(vararg params: Any?) {
        require(params.size == paramsSetSize) {
            "Amount of parameters should always be the same: $paramsSetSize. Was: ${params.size}"
        }

        parameters.add(params)
    }

    override fun appendWithClassLoader(vararg params: Any) {
        require(params.size == paramsSetSize) {
            "Amount of parameters should always be the same: $paramsSetSize. Was: ${params.size}"
        }

        params.forEach {
            parameters.add(arrayOf(it, it::class.java.classLoader))
        }
    }
}
