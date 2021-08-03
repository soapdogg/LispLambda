package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem

class PostEvaluationStackUpdater(
    private val topProgramStackItemUpdater: TopProgramStackItemUpdater
) {

    fun updateStacksAfterEvaluation(
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