package lambda.evaluator

import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.datamodels.ProgramStackItem
import lambda.datamodels.UserDefinedFunction
import kotlin.collections.HashMap

class UserDefinedFunctionEvaluator(
    private val stackUpdateDeterminer: StackUpdateDeterminer
) {

    fun evaluateUserDefinedFunction(
        userDefinedFunction: UserDefinedFunction,
        variableMap: Map<String, NodeV2>,
        functionStack: Stack<NodeV2>,
        evalStack: Stack<NodeV2>,
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