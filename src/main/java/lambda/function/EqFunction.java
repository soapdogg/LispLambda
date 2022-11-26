package lambda.function;

import lambda.Function;
import lambda.core.datamodels.AtomNode;

interface EqFunction extends Function {

    static EqFunction newInstance() {
        return params -> {
            final var first = params.pop();
            while(params.isNotEmpty()) {
                final var node = params.pop();
                if (!node.equals(first)) {
                    return new AtomNode(false);
                }
            }
            return new AtomNode(true);
        };
    }
}
