package lambda.evaluator

import lambda.core.datamodels.Stack

internal interface StackGenerator {
    fun <T> generateNewStack(clazz: Class<T>): Stack<T>
}

internal class StackGeneratorImpl: StackGenerator {

    override fun <T> generateNewStack(clazz: Class<T>): Stack<T> {
        return Stack()
    }
}