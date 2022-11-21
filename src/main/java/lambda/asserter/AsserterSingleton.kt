package lambda.asserter

import lambda.FunctionLengthAsserter

enum class AsserterSingleton {
    INSTANCE;

    fun getFunctionLengthAsserter(): FunctionLengthAsserter {
        val functionLengthDeterminer = FunctionLengthDeterminerImpl()
        return FunctionLengthAsserterImpl(
            functionLengthDeterminer
        )
    }
}