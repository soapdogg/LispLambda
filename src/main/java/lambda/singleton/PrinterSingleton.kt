package lambda.singleton

import lambda.printer.*

enum class PrinterSingleton {
    INSTANCE;

    val listNotationPrinter: ListNotationPrinter = ListNotationPrinter()
}