package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem

class StackUpdateDeterminer (
    private val topProgramStackItemCreator: TopProgramStackItemCreator,
    private val postEvaluationStackUpdater: PostEvaluationStackUpdater
){

    fun determineHowToUpdateStacks(
        node: Node,
        variableMap: Map<String, Node>,
        evalStack: Stack<Node>,
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