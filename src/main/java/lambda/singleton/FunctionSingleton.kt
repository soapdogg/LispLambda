package lambda.singleton

import lambda.core.constants.FunctionNameConstants
import lambda.function.*
import lambda.function.internal.GcdCalculator
import lambda.function.internal.ListValueRetriever
import lambda.function.internal.NumericValueRetriever

enum class FunctionSingleton {
    INSTANCE;

    private val listValueRetriever = ListValueRetriever()
    private val numericValueRetriever = NumericValueRetriever(
        PrinterSingleton.INSTANCE.listNotationPrinter
    )
    private val gcdCalculator = GcdCalculator()

    private val atomFunction = AtomFunction()
    private val carFunction = CarFunction(
        listValueRetriever
    )
    private val cdrFunction = CdrFunction(
        listValueRetriever
    )
    private val consFunction = ConsFunction()
    private val eqFunction = EqFunction()
    private val evenPFunction = EvenPFunction(
        numericValueRetriever
    )
    private val expFunction = ExpFunction(
        numericValueRetriever
    )
    private val exptFunction = ExptFunction(
        numericValueRetriever
    )
    private val gcdFunction = GcdFunction(
        numericValueRetriever,
        gcdCalculator
    )
    private val greaterFunction = GreaterFunction(
        numericValueRetriever
    )
    private val greaterEqFunction = GreaterEqFunction(
        numericValueRetriever
    )
    private val intFunction = IntFunction()
    private val lcmFunction = LcmFunction(
        numericValueRetriever,
        gcdCalculator
    )
    private val lessFunction = LessFunction(
        numericValueRetriever
    )
    private val lessEqFunction = LessEqFunction(
        numericValueRetriever
    )
    private val maxFunction = MaxFunction(
        numericValueRetriever
    )
    private val minFunction = MinFunction(
        numericValueRetriever
    )
    private val minusFunction = MinusFunction(
        numericValueRetriever
    )
    private val minusPFunction = MinusPFunction(
        numericValueRetriever
    )
    private val notEqFunction = NotEqFunction()
    private val nullFunction = NullFunction()
    private val oddPFunction = OddPFunction(
        numericValueRetriever
    )
    private val plusFunction = PlusFunction(
        numericValueRetriever
    )
    private val plusPFunction = PlusPFunction(
        numericValueRetriever
    )
    private val timesFunction = TimesFunction(
        numericValueRetriever
    )
    private val zeroPFunction = ZeroPFunction(
        numericValueRetriever
    )

    val functionMap = mapOf(
        Pair(FunctionNameConstants.ATOM, atomFunction),
        Pair(FunctionNameConstants.CAR, carFunction),
        Pair(FunctionNameConstants.CDR, cdrFunction),
        Pair(FunctionNameConstants.CONS, consFunction),
        Pair(FunctionNameConstants.EQ, eqFunction),
        Pair(FunctionNameConstants.EVEN_P, evenPFunction),
        Pair(FunctionNameConstants.EXP, expFunction),
        Pair(FunctionNameConstants.EXPT, exptFunction),
        Pair(FunctionNameConstants.GCD, gcdFunction),
        Pair(FunctionNameConstants.GREATER, greaterFunction),
        Pair(FunctionNameConstants.GREATER_EQ, greaterEqFunction),
        Pair(FunctionNameConstants.INT, intFunction),
        Pair(FunctionNameConstants.LCM, lcmFunction),
        Pair(FunctionNameConstants.LESS, lessFunction),
        Pair(FunctionNameConstants.LESS_EQ, lessEqFunction),
        Pair(FunctionNameConstants.MAX, maxFunction),
        Pair(FunctionNameConstants.MIN, minFunction),
        Pair(FunctionNameConstants.MINUS, minusFunction),
        Pair(FunctionNameConstants.MINUS_P, minusPFunction),
        Pair(FunctionNameConstants.NOT_EQ, notEqFunction),
        Pair(FunctionNameConstants.NULL, nullFunction),
        Pair(FunctionNameConstants.ODD_P, oddPFunction),
        Pair(FunctionNameConstants.PLUS, plusFunction),
        Pair(FunctionNameConstants.PLUS_P, plusPFunction),
        Pair(FunctionNameConstants.TIMES, timesFunction),
        Pair(FunctionNameConstants.ZERO_P, zeroPFunction)
    )
}