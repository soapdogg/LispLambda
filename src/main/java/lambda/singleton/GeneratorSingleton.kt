package lambda.singleton

import lambda.generator.UserDefinedFunctionGenerator
import lambda.generator.*
import lambda.tokenizer.internal.TokenGenerator

enum class GeneratorSingleton {
    INSTANCE;

    val nodeGenerator: NodeGenerator = NodeGenerator()
    val programStackItemGenerator: ProgramStackItemGenerator = ProgramStackItemGenerator()
    val userDefinedFunctionGenerator: UserDefinedFunctionGenerator = UserDefinedFunctionGenerator(
        AsserterSingleton.INSTANCE.functionLengthAsserter,
        AsserterSingleton.INSTANCE.userDefinedFunctionNameAsserter,
        AsserterSingleton.INSTANCE.userDefinedFormalParametersAsserter
    )

}