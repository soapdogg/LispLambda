package lambda.evaluator.rootnode.internal

import lambda.core.datamodels.Stack
import lambda.core.datamodels.ProgramStackItem

class TopProgramStackItemUpdater (
    private val programStackItemGenerator: ProgramStackItemGenerator
) {

    fun updateTopProgramStackItemToNextChild(
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