package com.hugohirling.logicalcomponents.gui.components;

import com.hugohirling.logicalcomponents.util.KnotPullListener;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;

/**
 * @author Hugo Hirling
 * @version 01.04.2022
 * @url https://hugohirling.com
 * 
 *      Represents the graphical node of a knot
 */
public abstract class KnotNode extends Arc {

    private boolean status;

    private KnotPullListener listener;

    private TempCabelNode tempCabel;

    private boolean gotDragged;

    protected final int startAngle;

    public KnotNode(final double x, final double y, final int startAngle) {
        this(x, y, false, startAngle);
    }

    public KnotNode(final double x, final double y, final boolean status, final int startAngle) {
        super(x, y, 12.5, 12.5, startAngle, 180);

        this.startAngle = startAngle;

        this.colorize();

        this.gotDragged = false;

        this.setOnMouseClicked(mouseEvent -> {
            this.status = !this.status;
            this.colorize();
        });

        this.setOnMouseDragged(mouseEvent -> {
            if (listener != null) {
                if (!this.gotDragged) {
                    listener.onFirstPull(this.getLayoutX(), this.getLayoutY(), mouseEvent.getX(), mouseEvent.getY());
                } else {
                    listener.onPull(this.getLayoutX(), this.getLayoutY(), mouseEvent.getX(), mouseEvent.getY());
                }
                this.gotDragged = true;
            }
        });

        this.setOnMouseReleased(mouseEvent -> {
            if (listener != null && this.gotDragged) {
                listener.onRelease(this.getLayoutX(), this.getLayoutY(), mouseEvent.getX(), mouseEvent.getY());
                this.gotDragged = false;
            }
        });
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

    public final void setListener(final KnotPullListener listener) {
        this.listener = listener;
    }

    public final void setTempCabel(final TempCabelNode tempCabel) {
        this.tempCabel = tempCabel;
    }

    public final TempCabelNode getTempCabel() {
        return this.tempCabel;
    }
}