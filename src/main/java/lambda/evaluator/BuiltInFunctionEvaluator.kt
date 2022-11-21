package lambda.evaluator

import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import lambda.Function

internal interface BuiltInFunctionEvaluator {
    fun evaluateBuiltInFunction(
        functionName: String,
        functionStack: Stack<Node>,
        top: ProgramStackItem,
        evalStack: Stack<Node>,
        programStack: Stack<ProgramStackItem>
    )
}

internal class BuiltInFunctionEvaluatorImpl(
    private val functionMap: Map<String, Function>,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
): BuiltInFunctionEvaluator {

    override fun evaluateBuiltInFunction(
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