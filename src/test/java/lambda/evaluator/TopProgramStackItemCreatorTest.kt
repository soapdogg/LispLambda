package lambda.evaluator

import lambda.datamodels.ExpressionListNode
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.datamodels.ProgramStackItem
import lambda.generator.ProgramStackItemGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class TopProgramStackItemCreatorTest {

    private val programStackItemGenerator = Mockito.mock(ProgramStackItemGenerator::class.java)

    private val topProgramStackItemCreator = TopProgramStackItemCreator(programStackItemGenerator)

    @Test
    fun createTopProgramStackItemTest() {
        val expressionListNode = Mockito.mock(ExpressionListNode::class.java)
        val variableMap = emptyMap<String, NodeV2>()
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