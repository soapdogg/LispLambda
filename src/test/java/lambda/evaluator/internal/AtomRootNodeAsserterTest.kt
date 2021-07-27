package lambda.evaluator.internal

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.exceptions.NotAtomicException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AtomRootNodeAsserterTest {
    private val atomNode: AtomNode = Mockito.mock(AtomNode::class.java)
    private val atomRootNodeAsserter: AtomRootNodeAsserter = AtomRootNodeAsserter()


    @Test
    fun valueIsNumericTest() {
        val value = "34"
        Mockito.`when`(atomNode.value).thenReturn(value)
        Assertions.assertDoesNotThrow { atomRootNodeAsserter.assertAtomRootNode(atomNode) }
    }

    @Test
    fun valueIsTTest() {
        val value = ReservedValuesConstants.T
        Mockito.`when`(atomNode.value).thenReturn(value)
        Assertions.assertDoesNotThrow { atomRootNodeAsserter.assertAtomRootNode(atomNode) }
    }

    @Test
    fun valueIsNilTest() {
        val value = ReservedValuesConstants.NIL
        Mockito.`when`(atomNode.value).thenReturn(value)
        Assertions.assertDoesNotThrow { atomRootNodeAsserter.assertAtomRootNode(atomNode) }
    }

    @Test
    fun invalidValueTest() {
        val value = "value"
        Mockito.`when`(atomNode.value).thenReturn(value)
        Assertions.assertThrows(
            NotAtomicException::class.java
        ) { atomRootNodeAsserter.assertAtomRootNode(atomNode) }
    }
}