package lambda.evaluator;

import lambda.core.datamodels.Node;
import lambda.core.datamodels.ProgramStackItem;
import lambda.core.datamodels.Stack;

interface QuoteFunctionEvaluator {

    void evaluateQuoteFunction(
        ProgramStackItem top,
        Stack<Node> evalStack,
        Stack<ProgramStackItem> programStackItem
    );

    static QuoteFunctionEvaluator newInstance(PostEvaluationStackUpdater postEvaluationStackUpdater) {
        return (top, evalStack, programStack) -> {
            final var quoteExprNode = top.getFunctionExpressionNode();
            final var quoted = quoteExprNode.getChildren().get(1);
            postEvaluationStackUpdater.updateStacksAfterEvaluation(
                quoted,
                top.getVariableMap(),
                evalStack,
                programStack
            );
        };
    }
}
