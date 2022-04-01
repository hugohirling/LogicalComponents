package com.hugohirling.logicalcomponents.gui.components;

import java.util.Optional;

import com.hugohirling.logicalcomponents.util.KnotPullListener;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

/**
 * @author Hugo Hirling
 * @version 01.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of a knot
 */
public abstract class KnotNode extends Arc {

    private boolean status;

    protected final int startAngle;

    private final boolean changeable;

    private final Pane root;

    public KnotNode(final Pane root, final double x, final double y, final int startAngle) {
        this(root, x, y, false, startAngle, false);
    }

    public KnotNode(final Pane root, final double x, final double y, final boolean status, final int startAngle, final boolean changeable) {
        super(x, y, 12.5, 12.5, startAngle, 180);

        this.root = root;
        this.changeable = changeable;

        this.startAngle = startAngle;

        this.colorize();

        this.coordinateEvents(); 
    }

    private void coordinateEvents() {
        if(this.changeable) {
            this.setOnMouseClicked(mouseEvent -> {
                this.status = !this.status;
                this.colorize();
            });
        }
    }

    /**
     * Colorizes the InputNode dependet on its status
     */
    private final void colorize() {
        if (this.status) {
            this.setFill(Color.RED);
        } else {
            this.setFill(Color.BLACK);
        }
    }
}