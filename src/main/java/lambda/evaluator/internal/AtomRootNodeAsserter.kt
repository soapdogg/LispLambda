package lambda.evaluator.internal

import lambda.core.constants.ReservedValuesConstants
import lambda.core.datamodels.AtomNode
import lambda.core.exceptions.NotAtomicException

class AtomRootNodeAsserter {

    fun assertAtomRootNode(
        atomNode: AtomNode
    ) {
        val isNotNumeric = !atomNode.value.matches(Regex(ReservedValuesConstants.NUMERIC_PATTERN))
        val isNotT = atomNode.value != ReservedValuesConstants.T
        val isNotNil = atomNode.value != ReservedValuesConstants.NIL
        if (isNotNumeric && isNotT && isNotNil) {
            throw NotAtomicException("""Error! ${atomNode.value} is not a valid atomic value!${'\n'}""")
        }
    }
}