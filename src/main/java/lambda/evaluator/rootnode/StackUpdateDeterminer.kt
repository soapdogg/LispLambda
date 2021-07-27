package lambda.evaluator.rootnode

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.ProgramStackItem

class StackUpdateDeterminer (
    private val topProgramStackItemCreator: TopProgramStackItemCreator,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
){

    fun determineHowToUpdateStacks(
        node: NodeV2,
        variableMap: Map<String, NodeV2>,
        evalStack: Stack<NodeV2>,
        programStack: Stack<ProgramStackItem>
    ) {
        if (node is ExpressionListNode) {
            if (node.children.size > 1) {
                topProgramStackItemCreator.createTopProgramStackItem(
                    node,
                    variableMap,
                    programStack
                )
            } else {
                postEvaluationStackUpdater.updateStacksAfterEvaluation(
                    node.children[0],
                    variableMap,
                    evalStack,
                    programStack
                )
            }
        }
        else {
            postEvaluationStackUpdater.updateStacksAfterEvaluation(
                node,
                variableMap,
                evalStack,
                programStack
            )
        }
    }
}