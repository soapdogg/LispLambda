package lambda.evaluator

import lambda.asserter.AtomRootNodeAsserter
import lambda.datamodels.*
import lambda.generator.StackGenerator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class ProgramEvaluatorTest {
    private val userDefinedFunctions: Map<String, UserDefinedFunction> = emptyMap()

    private val atomRootNodeAsserter = Mockito.mock(AtomRootNodeAsserter::class.java)
    private val nodeEvaluatorIterative = Mockito.mock(RootNodeEvaluator::class.java)
    private val stackGenerator = Mockito.mock(StackGenerator::class.java)

    private val programEvaluator = ProgramEvaluator(
        atomRootNodeAsserter,
        nodeEvaluatorIterative,
        stackGenerator
    )

    @Test
    fun rootNodeIsAnAtomNodeTest() {
        val atomNode = Mockito.mock(AtomNode::class.java)
        val rootNodes = listOf(atomNode)

        val actual = programEvaluator.evaluatePostOrder(
            rootNodes,
            userDefinedFunctions
        )
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(atomNode, actual[0])
        Mockito.verify(atomRootNodeAsserter).assertAtomRootNode(atomNode)
    }

    @Test
    fun rootNodeIsExpressionNodeTest() {
        val expressionNode = Mockito.mock(ExpressionListNode::class.java)
        val rootNodes = listOf(expressionNode)
        val evaluatedNode = Mockito.mock(NodeV2::class.java)
        val programStack = Stack<ProgramStackItem>()
        Mockito.`when`(stackGenerator.generateNewStack(ProgramStackItem::class.java)).thenReturn(programStack)
        val evaluationStack = Stack<NodeV2>()
        Mockito.`when`(stackGenerator.generateNewStack(NodeV2::class.java)).thenReturn(evaluationStack)
        Mockito.`when`(
            nodeEvaluatorIterative.evaluate(
                expressionNode,
                userDefinedFunctions,
                programStack,
                evaluationStack
            )
        ).thenReturn(evaluatedNode)
        val actual = programEvaluator.evaluatePostOrder(
            rootNodes,
            userDefinedFunctions
        )
        Assertions.assertEquals(1, actual.size)
        Assertions.assertEquals(evaluatedNode, actual[0])
        Mockito.verifyNoInteractions(atomRootNodeAsserter)
    }
}