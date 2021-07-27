package lambda.evaluator.rootnode

import lambda.core.datamodels.Stack
import lambda.core.datamodels.ProgramStackItem
import lambda.core.exceptions.NotAListException

class CondFunctionEvaluator(
    private val topProgramStackItemUpdater: TopProgramStackItemUpdater,
    private val condChildStackItemBuilder: CondChildStackItemBuilder
) {

    fun evaluateCondProgramStackItem(
        top: ProgramStackItem,
        programStack: Stack<ProgramStackItem>
    ) {
        programStack.push(top)
        when (top.currentParameterIndex) {
            0 -> {
                topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
                    programStack
                )
                condChildStackItemBuilder.buildCondChildStackItems(
                    top,
                    programStack
                )
            }
            else -> {
                throw NotAListException("Error! None of the conditions in the cond function evaluated to true.\n")
            }
        }
    }
}