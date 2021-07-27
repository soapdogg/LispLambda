package lambda.evaluator.rootnode

import lambda.core.datamodels.Stack
import lambda.core.datamodels.ProgramStackItem
import lambda.core.exceptions.NotAListException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CondFunctionEvaluatorTest {

    private val topProgramStackItemUpdater = Mockito.mock(TopProgramStackItemUpdater::class.java)
    private val condChildStackItemBuilder = Mockito.mock(CondChildStackItemBuilder::class.java)

    private val condProgramStackItemEvaluator = CondFunctionEvaluator(
        topProgramStackItemUpdater,
        condChildStackItemBuilder
    )

    @Test
    fun currentParameterIndexIsZeroTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val programStack = Stack<ProgramStackItem>()

        val currentParameterIndex = 0
        Mockito.`when`(top.currentParameterIndex).thenReturn(currentParameterIndex)

        condProgramStackItemEvaluator.evaluateCondProgramStackItem(
            top,
            programStack
        )

        Mockito.verify(topProgramStackItemUpdater).updateTopProgramStackItemToNextChild(programStack)

        Mockito.verify(condChildStackItemBuilder).buildCondChildStackItems(
            top,
            programStack
        )
    }

    @Test
    fun noCondConditionsEvaluatedToTrueTest() {
        val top = Mockito.mock(ProgramStackItem::class.java)
        val programStack = Stack<ProgramStackItem>()

        val currentParameterIndex = 1
        Mockito.`when`(top.currentParameterIndex).thenReturn(currentParameterIndex)

        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            condProgramStackItemEvaluator.evaluateCondProgramStackItem(
                top,
                programStack
            )
        }

        Mockito.verifyNoInteractions(topProgramStackItemUpdater)
        Mockito.verifyNoInteractions(condChildStackItemBuilder)
    }
}