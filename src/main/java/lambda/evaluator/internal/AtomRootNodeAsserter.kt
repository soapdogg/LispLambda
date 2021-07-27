package lambda.evaluator.internal

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.determiner.NumericStringDeterminer
import lambda.core.exceptions.NotAtomicException

class AtomRootNodeAsserter(
    private val numericStringDeterminer: NumericStringDeterminer
) {

    fun assertAtomRootNode(
        atomNode: AtomNode
    ) {
        val isNotNumeric = !numericStringDeterminer.isStringNumeric(atomNode.value)
        val isNotT = atomNode.value != ReservedValuesConstants.T
        val isNotNil = atomNode.value != ReservedValuesConstants.NIL
        if (isNotNumeric && isNotT && isNotNil) {
            throw NotAtomicException("""Error! ${atomNode.value} is not a valid atomic value!${'\n'}""")
        }
    }
}