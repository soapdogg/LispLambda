package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem

class TopProgramStackItemCreator(
    private val programStackItemGenerator: ProgramStackItemGenerator
) {

    fun createTopProgramStackItem(
        expressionListNode: ExpressionListNode,
        variableMap: Map<String, Node>,
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