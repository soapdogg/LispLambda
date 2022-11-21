package lambda.evaluator

import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem

internal interface QuoteFunctionEvaluator {
    fun evaluateQuoteFunction(
        top: ProgramStackItem,
        evalStack: Stack<Node>,
        programStack: Stack<ProgramStackItem>
    )
}

internal class QuoteFunctionEvaluatorImpl(
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
): QuoteFunctionEvaluator {

    override fun evaluateQuoteFunction(
        top: ProgramStackItem,
        evalStack: Stack<Node>,
        programStack: Stack<ProgramStackItem>
    ) {
        val quoteExprNode = top.functionExpressionNode
        val quoted = quoteExprNode.children[1]
        return postEvaluationStackUpdater.updateStacksAfterEvaluation(
            quoted,
            top.variableMap,
            evalStack,
            programStack
        )
    }
}