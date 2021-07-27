package lambda.evaluator

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.ProgramStackItem
import lambda.core.datamodels.UserDefinedFunction
import lambda.function.Function

class FinishedProgramStackItemEvaluator(
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater,
    private val functionMap: Map<String, Function>,
    private val builtInFunctionEvaluator: BuiltInFunctionEvaluator,
    private val userDefinedFunctionEvaluator: UserDefinedFunctionEvaluator
) {
    fun evaluateFinishedProgramStackItem(
        top: ProgramStackItem,
        userDefinedFunctions: Map<String, UserDefinedFunction>,
        evalStack: Stack<NodeV2>,
        programStack: Stack<ProgramStackItem>
    ) {
        val functionStack = Stack<NodeV2>()
        for (i in 0 until top.functionExpressionNode.children.size - 1) {
            functionStack.push(evalStack.pop())
        }

        val functionNameNode = functionStack.pop()
        val functionName = top.functionName
        when {
            functionName == ReservedValuesConstants.NIL -> {
                postEvaluationStackUpdater.updateStacksAfterEvaluation(
                    functionNameNode,
                    top.variableMap,
                    evalStack,
                    programStack
                )
            }
            functionMap.containsKey(functionName) -> {
                builtInFunctionEvaluator.evaluateBuiltInFunction(
                    functionName,
                    functionStack,
                    top,
                    evalStack,
                    programStack
                )
            }
            userDefinedFunctions.containsKey(functionName) -> {
                userDefinedFunctionEvaluator.evaluateUserDefinedFunction(
                    userDefinedFunctions.getValue(functionName),
                    top.variableMap,
                    functionStack,
                    evalStack,
                    programStack
                )
            }
            else -> {
                throw Exception("Error! Invalid CAR value: $functionName\n")
            }
        }
    }
}