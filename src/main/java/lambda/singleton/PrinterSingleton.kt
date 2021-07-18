package lambda.singleton

import lambda.printer.*

enum class PrinterSingleton {
    INSTANCE;

    val atomNodePrinter: AtomNodePrinter = AtomNodePrinter()
    val dotNotationExpressionNodePrinter: DotNotationExpressionNodePrinter
    val dotNotationPrinter: DotNotationPrinter
    val listNotationPrinter: ListNotationPrinter

    init {
        dotNotationExpressionNodePrinter = DotNotationExpressionNodePrinter(
            atomNodePrinter
        )
        dotNotationPrinter = DotNotationPrinter(
            atomNodePrinter,
            dotNotationExpressionNodePrinter
        )
        listNotationPrinter = ListNotationPrinter(
            atomNodePrinter
        )
    }
}