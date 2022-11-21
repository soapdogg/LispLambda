package lambda.evaluator

import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Stack
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TopProgramStackItemCreatorTest {

    private val programStackItemGenerator = Mockito.mock(ProgramStackItemGenerator::class.java)

    private val topProgramStackItemCreator = TopProgramStackItemCreatorImpl(programStackItemGenerator)

    @Test
    fun createTopProgramStackItemTest() {
        val expressionListNode = Mockito.mock(ExpressionListNode::class.java)
        val variableMap = emptyMap<String, Node>()
        val programStack = Stack<ProgramStackItem>()

        val top = Mockito.mock(ProgramStackItem::class.java)
        Mockito.`when`(
            programStackItemGenerator.generateProgramStackItemFromScratch(
                expressionListNode,
                variableMap
            )
        ).thenReturn(top)

        topProgramStackItemCreator.createTopProgramStackItem(
            expressionListNode,
            variableMap,
            programStack
        )

        Assertions.assertEquals(1, programStack.size())
        Assertions.assertEquals(top, programStack.peek())
    }
}