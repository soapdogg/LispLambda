package lambda.function

import lambda.Function
import lambda.ListNotationPrinter
import lambda.core.constants.FunctionNameConstants

enum class FunctionSingleton {
    INSTANCE;

    fun getFunctionMap(listNotationPrinter: ListNotationPrinter): Map<String, Function> {
        val listValueRetriever = ListValueRetrieverImpl()
        val numericValueRetriever = NumericValueRetrieverImpl(listNotationPrinter)
        val gcdCalculator = GcdCalculatorImpl()
        val atomFunction = AtomFunction.newInstance()
        val carFunction = CarFunction(listValueRetriever)
        val cdrFunction = CdrFunction(listValueRetriever)
        val consFunction = ConsFunction()
        val eqFunction = EqFunction()
        val evenPFunction = EvenPFunction(numericValueRetriever)
        val expFunction = ExpFunction(numericValueRetriever)
        val exptFunction = ExptFunction(numericValueRetriever)
        val gcdFunction = GcdFunction(numericValueRetriever, gcdCalculator)
        val greaterFunction = GreaterFunction(numericValueRetriever)
        val greaterEqFunction = GreaterEqFunction(numericValueRetriever)
        val intFunction = IntFunction()
        val lcmFunction = LcmFunction(numericValueRetriever, gcdCalculator)
        val lessFunction = LessFunction(numericValueRetriever)
        val lessEqFunction = LessEqFunction(numericValueRetriever)
        val maxFunction = MaxFunction(numericValueRetriever)
        val minFunction = MinFunction(numericValueRetriever)
        val minusFunction = MinusFunction(numericValueRetriever)
        val minusPFunction = MinusPFunction(numericValueRetriever)
        val notEqFunction = NotEqFunction()
        val nullFunction = NullFunction()
        val oddPFunction = OddPFunction(numericValueRetriever)
        val onePlusFunction = OnePlusFunction(numericValueRetriever)
        val oneMinusFunction = OneMinusFunction(numericValueRetriever)
        val plusFunction = PlusFunction(numericValueRetriever)
        val plusPFunction = PlusPFunction(numericValueRetriever)
        val timesFunction = TimesFunction(numericValueRetriever)
        val zeroPFunction = ZeroPFunction(numericValueRetriever)

        return mapOf(
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
            Pair(FunctionNameConstants.ONE_MINUS, oneMinusFunction),
            Pair(FunctionNameConstants.ONE_PLUS, onePlusFunction),
            Pair(FunctionNameConstants.PLUS, plusFunction),
            Pair(FunctionNameConstants.PLUS_P, plusPFunction),
            Pair(FunctionNameConstants.TIMES, timesFunction),
            Pair(FunctionNameConstants.ZERO_P, zeroPFunction)
        )
    }
}