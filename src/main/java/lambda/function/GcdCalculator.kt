package lambda.function


internal interface GcdCalculator {
    fun calculateGCD(i: Int, j: Int): Int
}

internal class GcdCalculatorImpl: GcdCalculator {

    override fun calculateGCD(i: Int, j: Int): Int {
        var x = i
        var y = j
        while (x != 0 && y != 0) {
            if (x > y)
                x %= y
            else
                y %= x
        }
        if (x == 0) {
            x = y

        }

        return x
    }
}