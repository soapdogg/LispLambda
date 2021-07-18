package lambda.function

import lambda.constants.FunctionNameConstants
import lambda.datamodels.Stack
import lambda.datamodels.NodeV2
import lambda.generator.NodeGenerator
import lambda.valueretriver.NumericValueRetriever

class TimesFunction(
   private val numericValueRetriever: NumericValueRetriever,
   private val nodeGenerator: NodeGenerator
): Function {

    override fun evaluate(
        params: Stack<NodeV2>
    ): NodeV2 {
        val first = params.pop()
        val second = params.pop()
        val firstNumeric = numericValueRetriever.retrieveNumericValue(
            first,
            FunctionNameConstants.TIMES,
            1
        )
        val secondNumeric = numericValueRetriever.retrieveNumericValue(
            second,
            FunctionNameConstants.TIMES,
            2
        )

        val result = firstNumeric * secondNumeric

        return nodeGenerator.generateAtomNode(result)
    }
}