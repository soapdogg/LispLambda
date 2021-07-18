package lambda.evaluator

import lambda.constants.FunctionNameConstants
import lambda.datamodels.ExpressionListNode
import lambda.datamodels.Stack
import lambda.datamodels.ProgramStackItem
import lambda.generator.NodeGenerator

class CondChildStackItemBuilder(
    private val nodeGenerator: NodeGenerator,
    private val topProgramStackItemCreator: TopProgramStackItemCreator
) {

    fun buildCondChildStackItems(
        condProgramStackItem: ProgramStackItem,
        programStack: Stack<ProgramStackItem>
    ) {
        val variableMap = condProgramStackItem.variableMap
        val condChildren = condProgramStackItem.functionExpressionNode.children
        val condChildAtomNode = nodeGenerator.generateAtomNode(FunctionNameConstants.CONDCHILD)
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