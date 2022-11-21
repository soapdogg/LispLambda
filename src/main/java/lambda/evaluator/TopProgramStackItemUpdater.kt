package lambda.evaluator

import lambda.core.datamodels.Stack
import lambda.core.datamodels.ProgramStackItem

internal interface TopProgramStackItemUpdater {
    fun updateTopProgramStackItemToNextChild(
        programStack: Stack<ProgramStackItem>
    )
}

internal class TopProgramStackItemUpdaterImpl (
    private val programStackItemGenerator: ProgramStackItemGenerator
): TopProgramStackItemUpdater {

    override fun updateTopProgramStackItemToNextChild(
        programStack: Stack<ProgramStackItem>
    ) {
        if (programStack.isNotEmpty()) {
            val head = programStack.pop()
            val updatedHead = programStackItemGenerator.generateProgramStackItemFromExisting(
                head
            )
            programStack.push(
                updatedHead
            )
        }
    }
}