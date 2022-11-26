package lambda.evaluator;

import lambda.core.datamodels.AtomNode;
import lambda.core.datamodels.ExpressionListNode;
import lambda.core.datamodels.Node;
import lambda.core.datamodels.ProgramStackItem;

import java.util.Map;

interface ProgramStackItemGenerator {

    ProgramStackItem generateProgramStackItemFromScratch(
            ExpressionListNode functionExpressionNode,
            Map<String, Node> variableMap
    );

    ProgramStackItem generateProgramStackItemFromExisting(
            ProgramStackItem existingProgramStackItem
    );

    static ProgramStackItemGenerator newInstance() {
        return new ProgramStackItemGenerator() {
            @Override
            public ProgramStackItem generateProgramStackItemFromScratch(ExpressionListNode functionExpressionNode, Map<String, Node> variableMap) {
                return new ProgramStackItem(
                    functionExpressionNode,
                    0,
                    variableMap,
                    ((AtomNode)functionExpressionNode.getChildren().get(0)).getValue()
                );
            }

            @Override
            public ProgramStackItem generateProgramStackItemFromExisting(ProgramStackItem existingProgramStackItem) {
                return new ProgramStackItem(
                    existingProgramStackItem.getFunctionExpressionNode(),
                    existingProgramStackItem.getCurrentParameterIndex() + 1,
                    existingProgramStackItem.getVariableMap(),
                    existingProgramStackItem.getFunctionName()
                );
            }
        };
    }
}
