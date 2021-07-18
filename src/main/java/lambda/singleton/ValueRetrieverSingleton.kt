package lambda.singleton

import lambda.valueretriver.ListValueRetriever
import lambda.valueretriver.NumericValueRetriever

enum class ValueRetrieverSingleton {
    INSTANCE;

    val listValueRetriever: ListValueRetriever = ListValueRetriever(
        PrinterSingleton.INSTANCE.dotNotationPrinter
    )
    val numericValueRetriever: NumericValueRetriever = NumericValueRetriever(
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        PrinterSingleton.INSTANCE.listNotationPrinter
    )
}