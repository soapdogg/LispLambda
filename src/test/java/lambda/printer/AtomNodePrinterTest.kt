package lambda.printer

import lambda.datamodels.AtomNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AtomNodePrinterTest {

    private val atomNodePrinter: AtomNodePrinter = AtomNodePrinter()

    @Test
    fun atomNodePrinterTest() {
        val value = "value"
        val atomNode = Mockito.mock(AtomNode::class.java)
        Mockito.`when`(atomNode.value).thenReturn(value)
        val actual = atomNodePrinter.printAtomNode(atomNode)
        Assertions.assertEquals(value, actual)
    }
}