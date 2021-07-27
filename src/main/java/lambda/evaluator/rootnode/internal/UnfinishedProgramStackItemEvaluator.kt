package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.Stack
import lambda.core.datamodels.NodeV2
import lambda.core.datamodels.ProgramStackItem
import lambda.evaluator.rootnode.internal.StackUpdateDeterminer

class UnfinishedProgramStackItemEvaluator(
    private val stackUpdateDeterminer: StackUpdateDeterminer
) {

    fun evaluateUnfinishedProgramStackItem(
        top: ProgramStackItem,
        evalStack: Stack<NodeV2>,
        programStack: Stack<ProgramStackItem>
    ) {
        val nthChild = top.functionExpressionNode.children[top.currentParameterIndex]
        programStack.push(top)
        stackUpdateDeterminer.determineHowToUpdateStacks(
            nthChild,
            top.variableMap,
            evalStack,
            programStack
        )
    }
}