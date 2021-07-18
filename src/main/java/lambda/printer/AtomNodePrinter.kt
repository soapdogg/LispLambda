package lambda.printer

import lambda.datamodels.AtomNode

class AtomNodePrinter {
    fun printAtomNode(
        atomNode: AtomNode
    ): String {
        return atomNode.value
    }
}