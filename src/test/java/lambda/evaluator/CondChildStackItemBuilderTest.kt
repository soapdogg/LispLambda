package lambda.evaluator

import lambda.core.constants.FunctionNameConstants
import lambda.core.datamodels.*
import lambda.core.datamodels.Stack
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class CondChildStackItemBuilderTest {

    private val nodeGenerator = Mockito.mock(NodeGenerator::class.java)
    private val topProgramStackItemCreator = Mockito.mock(TopProgramStackItemCreator::class.java)

    private val condChildStackItemBuilder = CondChildStackItemBuilderImpl(
        nodeGenerator,
        topProgramStackItemCreator
    )

    @Test
    fun buildCondChildStackItemsTest() {
        val condProgramStackItem = Mockito.mock(ProgramStackItem::class.java)
        val programStack = Stack<ProgramStackItem>()

        val variableMap = emptyMap<String, Node>()
        Mockito.`when`(condProgramStackItem.variableMap).thenReturn(variableMap)

        val functionExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(condProgramStackItem.functionExpressionNode).thenReturn(functionExpressionListNode)

        val child0 = Mockito.mock(Node::class.java)
        val child1 = Mockito.mock(ExpressionListNode::class.java)
        val child2 = Mockito.mock(Node::class.java)
        val condChildren = listOf(
            child0,
            child1,
            child2
        )

        Mockito.`when`(functionExpressionListNode.children).thenReturn(condChildren)

        val condChildAtomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(nodeGenerator.generateAtomNode(FunctionNameConstants.COND_CHILD)).thenReturn(condChildAtomNode)

        val grandchild = Mockito.mock(Node::class.java)
        val child1Children = listOf(grandchild)
        Mockito.`when`(child1.children).thenReturn(child1Children)
        val condChildsChildren = listOf(condChildAtomNode, grandchild)
        val condChildExpressionListNode = Mockito.mock(ExpressionListNode::class.java)
        Mockito.`when`(nodeGenerator.generateExpressionListNode(condChildsChildren)).thenReturn(condChildExpressionListNode)

        condChildStackItemBuilder.buildCondChildStackItems(
            condProgramStackItem,
            programStack
        )

        Mockito.verify(topProgramStackItemCreator).createTopProgramStackItem(
            condChildExpressionListNode,
            variableMap,
            programStack
        )
    }
}