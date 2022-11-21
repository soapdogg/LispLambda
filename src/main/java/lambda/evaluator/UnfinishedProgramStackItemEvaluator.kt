package lambda.evaluator

import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem

internal interface UnfinishedProgramStackItemEvaluator {
    fun evaluateUnfinishedProgramStackItem(
        top: ProgramStackItem,
        evalStack: Stack<Node>,
        programStack: Stack<ProgramStackItem>
    )
}

internal class UnfinishedProgramStackItemEvaluatorImpl(
    private val stackUpdateDeterminer: StackUpdateDeterminer
): UnfinishedProgramStackItemEvaluator {

    override fun evaluateUnfinishedProgramStackItem(
        top: ProgramStackItem,
        evalStack: Stack<Node>,
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