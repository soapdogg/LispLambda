package lambda.core.datamodels

import lambda.core.constants.ReservedValuesConstants

data class AtomNode(val value: String) : Node {
    constructor(value: Boolean): this(if (value) ReservedValuesConstants.T else ReservedValuesConstants.NIL)
    constructor(value: Int): this(value.toString())
}