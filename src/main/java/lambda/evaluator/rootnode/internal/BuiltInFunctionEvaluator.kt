package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import lambda.function.Function

class BuiltInFunctionEvaluator(
    private val functionMap: Map<String, Function>,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
) {

    fun evaluateBuiltInFunction(
        functionName: String,
        functionStack: Stack<Node>,
        top: ProgramStackItem,
        evalStack: Stack<Node>,
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