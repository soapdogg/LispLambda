package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import lambda.core.datamodels.UserDefinedFunction
import kotlin.collections.HashMap

class UserDefinedFunctionEvaluator(
    private val stackUpdateDeterminer: StackUpdateDeterminer
) {

    fun evaluateUserDefinedFunction(
        userDefinedFunction: UserDefinedFunction,
        variableMap: Map<String, Node>,
        functionStack: Stack<Node>,
        evalStack: Stack<Node>,
        programStack: Stack<ProgramStackItem>
    ) {
        val mapCopy = HashMap(variableMap)
        userDefinedFunction.formalParameters.forEach {
            val param = functionStack.pop()
            mapCopy[it] = param
        }
        stackUpdateDeterminer.determineHowToUpdateStacks(
            userDefinedFunction.body,
            mapCopy,
            evalStack,
            programStack
        )
    }
}