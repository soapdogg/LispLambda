package lambda.printer

enum class PrinterSingleton {
    INSTANCE;

    fun getListNotationPrinter(): lambda.ListNotationPrinter {
        return ListNotationPrinter.newInstance()
    }
}