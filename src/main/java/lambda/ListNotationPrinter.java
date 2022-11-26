package lambda;

import lambda.core.datamodels.Node;
import lambda.printer.PrinterSingleton;

import java.util.List;

public interface ListNotationPrinter {

    String printInListNotation(List<? extends Node> nodes);

    String printInListNotation(Node node);

    static ListNotationPrinter newInstance() {
       return PrinterSingleton.INSTANCE.getListNotationPrinter();
    }
}
