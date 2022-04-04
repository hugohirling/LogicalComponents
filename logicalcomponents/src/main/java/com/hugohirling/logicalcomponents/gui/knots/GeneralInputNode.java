package com.hugohirling.logicalcomponents.gui.knots;


import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


/**
 * @author Hugo Hirling
 * @version 01.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of a general input knot
 */
public class GeneralInputNode extends BorderPane{
    
    private final InputKnotNode inputKnotNode;
    private final TextField textField;

    private final Pane root;

    public GeneralInputNode(final Pane root, final double y) {
        this(root, y, false, "Input");
    }
    public GeneralInputNode(final Pane root, final double y, final boolean status, final String textField) {
        super();
        this.setLayoutX(0);
        this.setLayoutY(y);

        this.root = root;

        this.textField = new TextField(textField);
        this.textField.setFont(new Font(15));
        this.textField.setPrefWidth(Control.USE_COMPUTED_SIZE);
        this.textField.prefColumnCountProperty().bind(this.textField.textProperty().length());

        this.inputKnotNode = new InputKnotNode(this.root, 0, 0, status, true);

        this.setLeft(this.inputKnotNode);
        this.setRight(this.textField);
    }

    public void setY(final double y) {
        this.setLayoutY(y);
    }

    public InputKnotNode getInputKnotNode() {
        return this.inputKnotNode;
    }
}


