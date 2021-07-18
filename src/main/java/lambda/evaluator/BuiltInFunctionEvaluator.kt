package lambda.evaluator

import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.datamodels.ProgramStackItem
import lambda.function.Function

class BuiltInFunctionEvaluator(
    private val functionMap: Map<String, Function>,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
) {

    fun evaluateBuiltInFunction(
        functionName: String,
        functionStack: Stack<NodeV2>,
        top: ProgramStackItem,
        evalStack: Stack<NodeV2>,
        programStack: Stack<ProgramStackItem>
    ) {
        val function = functionMap.getValue(functionName)
        val evaluatedFunctionResult = function.evaluate(functionStack)
        postEvaluationStackUpdater.updateStacksAfterEvaluation(
            evaluatedFunctionResult,
            top.variableMap,
            evalStack,
            programStack
        )
    }
}