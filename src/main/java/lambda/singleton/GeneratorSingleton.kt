package lambda.singleton

import lambda.generator.*

enum class GeneratorSingleton {
    INSTANCE;

    val nodeGenerator: NodeGenerator = NodeGenerator()

}