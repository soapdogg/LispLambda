package lambda.evaluator

import lambda.core.datamodels.AtomNode
import lambda.core.datamodels.ExpressionListNode
import lambda.core.datamodels.Node
import lambda.core.datamodels.ProgramStackItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ProgramStackItemGeneratorTest {

    private val functionExpressionNode = Mockito.mock(ExpressionListNode::class.java)
    private val child0 = Mockito.mock(AtomNode::class.java)
    private val functionName = "functionName"
    private val currentParameterIndex = 0
    private val variableMap = emptyMap<String, Node>()

    private val programStackItemGenerator = ProgramStackItemGeneratorImpl()

    @Test
    fun generateProgramStackItemFromScratchTest() {
        Mockito.`when`(functionExpressionNode.children).thenReturn(listOf(child0))
        Mockito.`when`(child0.value).thenReturn(functionName)

        val actual = programStackItemGenerator.generateProgramStackItemFromScratch(
            functionExpressionNode,
            variableMap
        )

        Assertions.assertEquals(functionExpressionNode, actual.functionExpressionNode)
        Assertions.assertEquals(0, actual.currentParameterIndex)
        Assertions.assertEquals(variableMap, actual.variableMap)
        Assertions.assertEquals(functionName, actual.functionName)
    }

    @Test
    fun generateProgramStackItemFromExistingTest() {
        val existingProgramStackItem = Mockito.mock(ProgramStackItem::class.java)

        Mockito.`when`(existingProgramStackItem.functionExpressionNode).thenReturn(functionExpressionNode)
        Mockito.`when`(existingProgramStackItem.currentParameterIndex).thenReturn(currentParameterIndex)
        Mockito.`when`(existingProgramStackItem.variableMap).thenReturn(variableMap)
        Mockito.`when`(existingProgramStackItem.functionName).thenReturn(functionName)

        val actual = programStackItemGenerator.generateProgramStackItemFromExisting(
            existingProgramStackItem
        )

        Assertions.assertEquals(functionExpressionNode, actual.functionExpressionNode)
        Assertions.assertEquals(currentParameterIndex + 1, actual.currentParameterIndex)
        Assertions.assertEquals(variableMap, actual.variableMap)
        Assertions.assertEquals(functionName, actual.functionName)
    }
}