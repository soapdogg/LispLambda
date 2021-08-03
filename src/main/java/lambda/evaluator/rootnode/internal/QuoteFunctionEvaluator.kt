package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import lambda.evaluator.rootnode.internal.PostEvaluationStackUpdater

class QuoteFunctionEvaluator(
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
) {

    fun evaluateQuoteFunction(
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