package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.ProgramStackItem

class TopProgramStackItemCreator(
    private val programStackItemGenerator: ProgramStackItemGenerator
) {

    fun createTopProgramStackItem(
        expressionListNode: ExpressionListNode,
        variableMap: Map<String, NodeV2>,
        programStack: Stack<ProgramStackItem>
    ) {
        val top = programStackItemGenerator.generateProgramStackItemFromScratch(
            expressionListNode,
            variableMap
        )
        programStack.push(
            top
        )
    }
}