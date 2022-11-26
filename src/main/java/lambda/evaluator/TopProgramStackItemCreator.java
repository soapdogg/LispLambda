package lambda.evaluator;

import lambda.core.datamodels.ExpressionListNode;
import lambda.core.datamodels.Node;
import lambda.core.datamodels.ProgramStackItem;
import lambda.core.datamodels.Stack;

import java.util.Map;

interface TopProgramStackItemCreator {

    void createTopProgramStackItem(
        ExpressionListNode expressionListNode,
        Map<String, Node> variableMap,
        Stack<ProgramStackItem> programStack
    );

    static TopProgramStackItemCreator newInstance(
        ProgramStackItemGenerator programStackItemGenerator
    ) {
        return (expressionListNode, variableMap, programStack) -> {
            final var top = programStackItemGenerator.generateProgramStackItemFromScratch(expressionListNode, variableMap);
            programStack.push(top);
        };
    }
}
