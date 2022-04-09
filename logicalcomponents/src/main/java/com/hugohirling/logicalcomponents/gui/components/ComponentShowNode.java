package com.hugohirling.logicalcomponents.gui.components;

import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Hugo Hirling
 * @version 09.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of component in the listview to add to the main scene.
 */
public class ComponentShowNode extends BorderPane{

    private final Text textField;
    private final Button button;

    private Runnable function;
    
    public ComponentShowNode(final String name, final Runnable function) {
        super();

        this.function = function;

        this.setMaxWidth(180);
        this.setPrefWidth(180);

        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

        this.textField = new Text();
        this.textField.setFont(new Font(17));
        this.button = new Button("Add");

        this.textField.setTextOrigin(VPos.CENTER);
        this.textField.setTextAlignment(TextAlignment.CENTER);
        this.textField.setText(name);

        this.setCenter(this.textField);
        this.setRight(this.button);

        this.handleEvents();
    }

    private void handleEvents() {
        this.button.setOnAction(mouseEvent -> {
            this.function.run();
        });
    }
}
