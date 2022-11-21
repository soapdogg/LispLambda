package lambda.userdefined

import lambda.core.exceptions.InvalidUserDefinedNameException

internal interface UserDefinedFormalParametersAsserter {
    fun assertFormalParameters(formalParameters: List<String>)
}

internal class UserDefinedFormalParametersAsserterImpl(
    private val invalidNamesSet: Set<String>
): UserDefinedFormalParametersAsserter {

    override fun assertFormalParameters(
        formalParameters: List<String>
    ) {
        val formalParametersSet: MutableSet<String> = HashSet(formalParameters)
        if (formalParameters.size != formalParametersSet.size) {
            throw InvalidUserDefinedNameException("Error! Duplicate formal parameter name!\n")
        }
        formalParametersSet.retainAll(invalidNamesSet)
        if (formalParametersSet.isNotEmpty()) {
            throw InvalidUserDefinedNameException("Error! Invalid formal parameter name!\n")
        }
    }
}