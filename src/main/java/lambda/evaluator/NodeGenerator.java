package lambda.evaluator;

import lambda.core.datamodels.AtomNode;
import lambda.core.datamodels.ExpressionListNode;
import lambda.core.datamodels.Node;

import java.util.List;

interface NodeGenerator {

    AtomNode generateAtomNode(String value);

    ExpressionListNode generateExpressionListNode(List<Node> children);

    static NodeGenerator newInstance() {
        return new NodeGenerator() {
            @Override
            public AtomNode generateAtomNode(String value) {
                return new AtomNode(value);
            }

            @Override
            public ExpressionListNode generateExpressionListNode(List<Node> children) {
                return new ExpressionListNode(children);
            }
        };
    }
}
