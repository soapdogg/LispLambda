package lambda.function;

import lambda.Function;
import lambda.core.datamodels.AtomNode;

interface AtomFunction extends Function {

    static AtomFunction newInstance() {
        return params -> {
            final var first = params.pop();
            return new AtomNode(first instanceof AtomNode);
        };
    }
}
