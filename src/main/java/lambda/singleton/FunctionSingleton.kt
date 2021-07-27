package lambda.singleton

import lambda.core.constants.FunctionNameConstants
import lambda.function.*
import lambda.function.internal.ListValueRetriever
import lambda.function.internal.NumericValueRetriever

enum class FunctionSingleton {
    INSTANCE;

    private val listValueRetriever: ListValueRetriever = ListValueRetriever()
    private val numericValueRetriever: NumericValueRetriever = NumericValueRetriever(
        PrinterSingleton.INSTANCE.listNotationPrinter
    )

    private val atomFunction: AtomFunction = AtomFunction()
    private val carFunction: CarFunction = CarFunction(
        listValueRetriever
    )
    private val cdrFunction: CdrFunction = CdrFunction(
        listValueRetriever
    )
    private val consFunction: ConsFunction = ConsFunction()
    private val eqFunction: EqFunction = EqFunction()
    private val greaterFunction: GreaterFunction = GreaterFunction(
        numericValueRetriever
    )
    private val intFunction: IntFunction = IntFunction()
    private val lessFunction: LessFunction = LessFunction(
        numericValueRetriever
    )
    private val minusFunction: MinusFunction = MinusFunction(
        numericValueRetriever
    )
    private val nullFunction: NullFunction = NullFunction()
    private val plusFunction: PlusFunction = PlusFunction(
        numericValueRetriever
    )
    private val timesFunction: TimesFunction = TimesFunction(
        numericValueRetriever
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