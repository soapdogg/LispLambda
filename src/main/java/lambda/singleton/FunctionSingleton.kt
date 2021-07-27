package lambda.singleton

import lambda.core.constants.FunctionNameConstants
import lambda.function.*
import lambda.function.internal.ListValueRetriever
import lambda.function.internal.NumericValueRetriever

enum class FunctionSingleton {
    INSTANCE;

    private val listValueRetriever: ListValueRetriever = ListValueRetriever()
    private val numericValueRetriever: NumericValueRetriever = NumericValueRetriever(
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        PrinterSingleton.INSTANCE.listNotationPrinter
    )

    val atomFunction: AtomFunction = AtomFunction(
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val carFunction: CarFunction = CarFunction(
        listValueRetriever
    )
    val cdrFunction: CdrFunction = CdrFunction(
        listValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val consFunction: ConsFunction = ConsFunction(
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val eqFunction: EqFunction = EqFunction(
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val greaterFunction: GreaterFunction = GreaterFunction(
        numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val intFunction: IntFunction = IntFunction(
        DeterminerSingleton.INSTANCE.numericStringDeterminer,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val lessFunction: LessFunction = LessFunction(
        numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val minusFunction: MinusFunction = MinusFunction(
        numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val nullFunction: NullFunction = NullFunction(
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val plusFunction: PlusFunction = PlusFunction(
        numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )
    val timesFunction: TimesFunction = TimesFunction(
        numericValueRetriever,
        GeneratorSingleton.INSTANCE.nodeGenerator
    )

    val functionMap = mapOf(
        Pair(FunctionNameConstants.ATOM, atomFunction),
        Pair(FunctionNameConstants.CAR, carFunction),
        Pair(FunctionNameConstants.CDR, cdrFunction),
        Pair(FunctionNameConstants.CONS, consFunction),
        Pair(FunctionNameConstants.EQ, eqFunction),
        Pair(FunctionNameConstants.GREATER, greaterFunction),
        Pair(FunctionNameConstants.INT, intFunction),
        Pair(FunctionNameConstants.LESS, lessFunction),
        Pair(FunctionNameConstants.MINUS, minusFunction),
        Pair(FunctionNameConstants.NULL, nullFunction),
        Pair(FunctionNameConstants.PLUS, plusFunction),
        Pair(FunctionNameConstants.TIMES, timesFunction)
    )
}