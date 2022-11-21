package lambda.evaluator

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.ProgramStackItem

internal interface CondChildStackItemBuilder {
    fun buildCondChildStackItems(
        condProgramStackItem: ProgramStackItem,
        programStack: Stack<ProgramStackItem>
    )
}

internal class CondChildStackItemBuilderImpl(
    private val nodeGenerator: NodeGenerator,
    private val topProgramStackItemCreator: TopProgramStackItemCreator
): CondChildStackItemBuilder {

    override fun buildCondChildStackItems(
        condProgramStackItem: ProgramStackItem,
        programStack: Stack<ProgramStackItem>
    ) {
        val variableMap = condProgramStackItem.variableMap
        val condChildren = condProgramStackItem.functionExpressionNode.children
        val condChildAtomNode = nodeGenerator.generateAtomNode(FunctionNameConstants.COND_CHILD)
        for (i in condChildren.size - 2 downTo 1) {
            val condChildsChildren = listOf(condChildAtomNode) +  (condChildren[i] as ExpressionListNode).children
            val condChildExpressionListNode = nodeGenerator.generateExpressionListNode(condChildsChildren)
            topProgramStackItemCreator.createTopProgramStackItem(
                condChildExpressionListNode,
                variableMap,
                programStack
            )
        }
    }
}