package lambda.evaluator.internal

import lambda.core.datamodels.Stack

class StackGenerator {

    fun <T> generateNewStack(clazz: Class<T>): Stack<T> {
        return Stack()
    }
}