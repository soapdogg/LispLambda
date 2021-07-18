package lambda.generator

import lambda.datamodels.Stack

class StackGenerator {

    fun <T> generateNewStack(clazz: Class<T>): Stack<T> {
        return Stack()
    }
}