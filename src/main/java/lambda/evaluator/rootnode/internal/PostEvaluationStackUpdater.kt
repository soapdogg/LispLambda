package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.ProgramStackItem

class PostEvaluationStackUpdater(
    private val topProgramStackItemUpdater: TopProgramStackItemUpdater
) {

    fun updateStacksAfterEvaluation(
        evaluatedNode: NodeV2,
        variableMap: Map<String, NodeV2>,
        evalStack: Stack<NodeV2>,
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