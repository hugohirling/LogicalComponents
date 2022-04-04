package com.hugohirling.logicalcomponents.gui;

import com.hugohirling.logicalcomponents.gui.components.ComponentNode;
import com.hugohirling.logicalcomponents.gui.knots.GeneralInputNode;
import com.hugohirling.logicalcomponents.gui.knots.KnotNode;
import com.hugohirling.logicalcomponents.gui.knots.KnotNode.KnotType;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

/**
 * @author Hugo Hirling
 * @version 04.04.2022
 * @url https://hugohirling.com
 * 
 * Represent the graphical node of a cabel
 */
public class CabelNode extends Polyline implements InvalidationListener{

    private final KnotNode inputNode;
    private final KnotNode outputNode;
    
    public CabelNode(final KnotNode inputNode, final KnotNode outputNode) {
        super();
        this.setStrokeWidth(3);

        if((inputNode.getKnotType() != KnotType.OUTPUT) ||
                (outputNode.getKnotType() != KnotType.INPUT)) {
            throw new IllegalArgumentException("The inputKnot must be a output knot. The outputKnot must be a input knot.");
        }

        this.inputNode = inputNode;
        this.outputNode = outputNode;

        this.inputNode.getParent().boundsInParentProperty().addListener(this);
        this.outputNode.getParent().boundsInParentProperty().addListener(this);

        this.colorize();
        this.setOutputStatus();

        this.inputNode.setOnKnotChangeListener(() -> {
            this.colorize();
            this.setOutputStatus();
        });

        this.setPoints();
    }

    private void setOutputStatus() {
        this.outputNode.setStatus(
                this.inputNode.getStatus()
        );
    }

    private void colorize() {
        if(inputNode.getStatus()) {
            this.setStroke(Color.RED);
        }else {
            this.setStroke(Color.BLACK);
        }
    }

    private void setPoints() {
        final Point2D inputPoint = this.inputNode.getLocation();
        final Point2D outputPoint = this.outputNode.getLocation();

        final Bounds boundsInSceneInputNode = this.inputNode.getParent().getBoundsInParent();
        final Bounds boundsInSceneOutputNode = this.outputNode.getParent().getBoundsInParent();
        final double inputX = boundsInSceneInputNode.getMinX();
        final double inputY = boundsInSceneInputNode.getMinY();
        final double outputX = boundsInSceneOutputNode.getMinX();
        final double outputY = boundsInSceneOutputNode.getMinY();

        double offsetY = 0;
        if(this.inputNode.getParent() instanceof GeneralInputNode) {
            offsetY = 12.5;
        }

        this.getPoints().clear();
        this.getPoints().addAll(inputPoint.getX() + inputX, inputPoint.getY() + inputY + offsetY,
                outputPoint.getX() + outputX, outputPoint.getY() + outputY);
    }

    @Override
    public void invalidated(final Observable observable) {
        this.setPoints();
    }
}
