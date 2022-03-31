package com.hugohirling.logicalcomponents.gui.components;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.text.Font;


/**
 * @author Hugo Hirling
 * @version 31.03.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of a general input knot
 */
public class GeneralInputNode extends BorderPane{

    private boolean status;
    
    private final Arc arc;
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

        this.arc = new Arc(0, 0, 12.5, 12.5, 270, 180);
        this.colorize();

        this.setLeft(this.arc);
        this.setRight(this.textField);

        this.arc.setOnMousePressed(mouseEvent -> {
            this.status = !this.status;
            this.colorize();
        });

    }

    private void colorize() {
        if(this.status) {
            this.arc.setFill(Color.RED);
        }else{
            this.arc.setFill(Color.BLACK);
        }
    }

    public void setY(final double y) {
        this.setLayoutY(y);
    }
}


