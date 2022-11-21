package lambda.evaluator

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem

internal interface TopProgramStackItemCreator {
    fun createTopProgramStackItem(
        expressionListNode: ExpressionListNode,
        variableMap: Map<String, Node>,
        programStack: Stack<ProgramStackItem>
    )
}

internal class TopProgramStackItemCreatorImpl(
    private val programStackItemGenerator: ProgramStackItemGenerator
): TopProgramStackItemCreator {

    override fun createTopProgramStackItem(
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