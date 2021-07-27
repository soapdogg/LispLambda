package lambda.singleton

import lambda.core.constants.FunctionNameConstants
import lambda.function.*
import lambda.function.internal.ListValueRetriever
import lambda.function.internal.NodeGenerator
import lambda.function.internal.NumericValueRetriever

enum class FunctionSingleton {
    INSTANCE;

    private val listValueRetriever: ListValueRetriever = ListValueRetriever()
    private val numericValueRetriever: NumericValueRetriever = NumericValueRetriever(
        PrinterSingleton.INSTANCE.listNotationPrinter
    )

    private val nodeGenerator = NodeGenerator()

    private val atomFunction: AtomFunction = AtomFunction(
        nodeGenerator
    )
    private val carFunction: CarFunction = CarFunction(
        listValueRetriever
    )
    private val cdrFunction: CdrFunction = CdrFunction(
        listValueRetriever,
        nodeGenerator
    )
    private val consFunction: ConsFunction = ConsFunction(
        nodeGenerator
    )
    private val eqFunction: EqFunction = EqFunction(
        nodeGenerator
    )
    private val greaterFunction: GreaterFunction = GreaterFunction(
        numericValueRetriever,
        nodeGenerator
    )
    private val intFunction: IntFunction = IntFunction(
        nodeGenerator
    )
    private val lessFunction: LessFunction = LessFunction(
        numericValueRetriever,
        nodeGenerator
    )
    private val minusFunction: MinusFunction = MinusFunction(
        numericValueRetriever,
        nodeGenerator
    )
    private val nullFunction: NullFunction = NullFunction(
        nodeGenerator
    )
    private val plusFunction: PlusFunction = PlusFunction(
        numericValueRetriever,
        nodeGenerator
    )
    private val timesFunction: TimesFunction = TimesFunction(
        numericValueRetriever,
        nodeGenerator
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