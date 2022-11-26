package lambda.printer;

import lambda.core.constants.ReservedValuesConstants;
import lambda.core.constants.TokenValueConstants;
import lambda.core.datamodels.AtomNode;
import lambda.core.datamodels.ExpressionListNode;
import lambda.core.datamodels.Node;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

interface ListNotationPrinter extends lambda.ListNotationPrinter {

    static ListNotationPrinter newInstance() {
        return new ListNotationPrinter() {
            @NotNull
            @Override
            public String printInListNotation(@NotNull List<? extends Node> nodes) {
                return nodes.stream().map(this::printInListNotation).collect(Collectors.joining(Character.toString(ReservedValuesConstants.NEW_LINE), ReservedValuesConstants.EMPTY, Character.toString(ReservedValuesConstants.NEW_LINE)));
            }

            @NotNull
            @Override
            public String printInListNotation(@NotNull Node node) {
                if (node instanceof ExpressionListNode) {
                    final var expressionNode = (ExpressionListNode) node;
                    final var sb = new StringBuilder();
                    sb.append(TokenValueConstants.OPEN_PARENTHESES);
                    var i = 0;
                    while(i < expressionNode.getChildren().size() - 1) {
                        sb.append(printInListNotation(expressionNode.getChildren().get(i)));
                        sb.append(ReservedValuesConstants.SPACE);
                        ++i;
                    }
                    var result = sb.toString().trim();
                    final var lastChild = expressionNode.getChildren().get(expressionNode.getChildren().size() - 1);
                    if (lastChild instanceof AtomNode && ((AtomNode) lastChild).getValue().equals(ReservedValuesConstants.NIL)) {
                        result += TokenValueConstants.CLOSE_PARENTHESES;
                    } else {
                        result += ReservedValuesConstants.LIST_DELIMITER;
                        result += printInListNotation(lastChild);
                        result += TokenValueConstants.CLOSE_PARENTHESES;
                    }
                    return result;
                }
                return ((AtomNode) node).getValue();
            }
        };
    }
}
