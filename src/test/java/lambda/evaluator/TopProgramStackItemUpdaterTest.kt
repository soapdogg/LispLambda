package lambda.evaluator

import lambda.core.datamodels.Stack
import lambda.core.datamodels.ProgramStackItem
import lambda.generator.ProgramStackItemGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TopProgramStackItemUpdaterTest {

    private val programStackItemGenerator = Mockito.mock(ProgramStackItemGenerator::class.java)

    private val topProgramStackItemUpdater = TopProgramStackItemUpdater(
        programStackItemGenerator
    )

    @Test
    fun updateTopProgramStackItemToNextChildTest() {
        val programStackItem = Mockito.mock(ProgramStackItem::class.java)

        val programStack = Stack<ProgramStackItem>()
        programStack.push(programStackItem)

        val updatedHead = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            programStackItemGenerator.generateProgramStackItemFromExisting(
                programStackItem
            )
        ).thenReturn(updatedHead)

        topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
            programStack
        )

        Assertions.assertEquals(1, programStack.size())
        Assertions.assertEquals(updatedHead, programStack.peek())
    }

    @Test
    fun updateTopProgramStackItemToNextChildEmptyStackTest() {
        val programStack = Stack<ProgramStackItem>()
        topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(
            programStack
        )

        Assertions.assertTrue(programStack.isEmpty())
        Mockito.verifyNoInteractions(programStackItemGenerator)
    }
}