package com.hugohirling.logicalcomponents.gui.components;

import com.hugohirling.logicalcomponents.util.KnotPullListener;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
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

    public GeneralInputNode(final double y) {
        this(y, false, "Input");
    }
    public GeneralInputNode(final double y, final boolean status, final String textField) {
        super();
        this.setLayoutX(0);
        this.setLayoutY(y);

        this.textField = new TextField(textField);
        this.textField.setFont(new Font(15));
        this.textField.setPrefWidth(Control.USE_COMPUTED_SIZE);
        this.textField.prefColumnCountProperty().bind(this.textField.textProperty().length());

        this.inputKnotNode = new InputKnotNode(0, 0, status);

        this.setLeft(this.inputKnotNode);
        this.setRight(this.textField);
    }

    public void setY(final double y) {
        this.setLayoutY(y);
    }
}


