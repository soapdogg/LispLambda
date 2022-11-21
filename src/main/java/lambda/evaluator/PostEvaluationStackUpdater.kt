package lambda.evaluator

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem

internal interface PostEvaluationStackUpdater {
    fun updateStacksAfterEvaluation(
        evaluatedNode: Node,
        variableMap: Map<String, Node>,
        evalStack: Stack<Node>,
        programStack: Stack<ProgramStackItem>
    )
}

internal class PostEvaluationStackUpdaterImpl(
    private val topProgramStackItemUpdater: TopProgramStackItemUpdater
): PostEvaluationStackUpdater {

    override fun updateStacksAfterEvaluation(
        evaluatedNode: Node,
        variableMap: Map<String, Node>,
        evalStack: Stack<Node>,
        programStack: Stack<ProgramStackItem>
    ) {
        val nodeToPush = if (evaluatedNode is AtomNode) {
            variableMap.getOrDefault(evaluatedNode.value, evaluatedNode)
        } else {
            evaluatedNode
        }
        evalStack.push(nodeToPush)
        topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
            programStack
        )
    }
}