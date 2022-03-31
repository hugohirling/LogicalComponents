package com.hugohirling.logicalcomponents.gui.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

public class GeneralInputNode extends Arc{

    private boolean status;
    

    public GeneralInputNode(final double y) {
        this(y, false);
    }
    public GeneralInputNode(final double y, final boolean status) {
        super(0, y, 12.5, 12.5, 270, 180);

        this.colorize();

        this.setOnMousePressed(mouseEvent -> {
            this.status = !this.status;
            this.colorize();
        });
    }

    private void colorize() {
        if(this.status) {
            this.setFill(Color.RED);
        }else{
            this.setFill(Color.BLACK);
        }
    }

    public void setY(final double y) {
        this.setCenterY(y);
    }
}


