package lambda.evaluator.rootnode

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.ProgramStackItem

class ProgramStackItemGenerator {

    fun generateProgramStackItemFromScratch(
        functionExpressionNode: ExpressionListNode,
        variableMap: Map<String, NodeV2>
    ): ProgramStackItem {
        return ProgramStackItem(
            functionExpressionNode,
            0,
            variableMap,
            (functionExpressionNode.children[0] as AtomNode).value
        )
    }

    fun generateProgramStackItemFromExisting(
        existingProgramStackItem: ProgramStackItem
    ): ProgramStackItem {
        return ProgramStackItem(
            existingProgramStackItem.functionExpressionNode,
            existingProgramStackItem.currentParameterIndex + 1,
            existingProgramStackItem.variableMap,
            existingProgramStackItem.functionName
        )
    }
}