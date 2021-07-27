package lambda.singleton

import lambda.determiner.*

enum class DeterminerSingleton {
    INSTANCE;

    val numericStringDeterminer: NumericStringDeterminer = NumericStringDeterminer()

}