package lambda.printer

import lambda.ListNotationPrinter

enum class PrinterSingleton {
    INSTANCE;

    fun getListNotationPrinter(): ListNotationPrinter {
        return ListNotationPrinterImpl()
    }
}