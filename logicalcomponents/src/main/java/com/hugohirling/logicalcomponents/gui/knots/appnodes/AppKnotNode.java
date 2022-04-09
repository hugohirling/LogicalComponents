package com.hugohirling.logicalcomponents.gui.knots.appnodes;

import com.hugohirling.logicalcomponents.gui.knots.InputKnotNode;
import com.hugohirling.logicalcomponents.gui.knots.KnotNode;
import com.hugohirling.logicalcomponents.gui.knots.OutputKnotNode;
import com.hugohirling.logicalcomponents.gui.knots.KnotNode.KnotType;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * @author Hugo Hirling
 * @version 09.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of an app knot
 */
public abstract class AppKnotNode extends BorderPane{

    private final KnotNode knotNode;
    private final TextField textField;

    private final Pane root;

    public AppKnotNode(final Pane root, final double y, final boolean status, final String text, final KnotType knotType) {
        super();
        this.setLayoutY(y);

        this.root = root;

        this.textField = new TextField(text);
        this.textField.setFont(new Font(15));
        this.textField.setMinWidth(100);
        this.textField.setMaxWidth(100);

        if(knotType == KnotType.INPUT) {
            this.setLayoutX(0);
            this.knotNode = new OutputKnotNode(root, 0, 12.5, status, true, 270);

            this.setRight(this.textField);
            this.setLeft(this.knotNode);
        }else {
            this.knotNode = new InputKnotNode(root, 0, 12.5, 90);
            
            this.setRight(this.knotNode);
            this.setLeft(this.textField);

            this.setLayoutX(1000 - this.knotNode.getRadiusX() - 105);

        }
    }

    public void setY(final double y) {
        this.setLayoutY(y);
    }

    public KnotNode getKnotNode() {
        return this.knotNode;
    }
}
