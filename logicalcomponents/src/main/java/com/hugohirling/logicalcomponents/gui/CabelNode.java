package com.hugohirling.logicalcomponents.gui;

import com.hugohirling.logicalcomponents.gui.components.ComponentNode;

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

    private final ComponentNode inputNode;
    private final ComponentNode outputNode;

    private final int inputIndex;
    private final int outputIndex;
    
    public CabelNode(final ComponentNode inputNode, final int inputIndex, final ComponentNode outputNode, final int outputIndex) {
        super();
        this.setStrokeWidth(3);

        this.inputNode = inputNode;
        this.outputNode = outputNode;

        this.inputIndex = inputIndex;
        this.outputIndex = outputIndex;

        this.inputNode.boundsInParentProperty().addListener(this);
        this.outputNode.boundsInParentProperty().addListener(this);

        this.colorize();
        this.setOutputStatus();

        this.inputNode.getOutputNodes().get(inputIndex).setOnKnotChangeListener(() -> {
            this.colorize();
            this.setOutputStatus();
        });
    }

    private void setOutputStatus() {
        this.outputNode.getInputNodes().get(this.outputIndex).setStatus(
                this.inputNode.getOutputNodes().get(this.inputIndex).getStatus()
        );
    }

    private void colorize() {
        if(inputNode.getOutputNodes().get(this.inputIndex).getStatus()) {
            this.setStroke(Color.RED);
        }else {
            this.setStroke(Color.BLACK);
        }
    }

    @Override
    public void invalidated(final Observable observable) {
        final Point2D inputPoint = inputNode.getOutputNodes().get(this.inputIndex).getLocation();
        final Point2D outputPoint = this.outputNode.getInputNodes().get(this.outputIndex).getLocation();

        final Bounds boundsInSceneInputNode = this.inputNode.getBoundsInParent();
        final Bounds boundsInSceneOutputNode = this.outputNode.getBoundsInParent();
        final double inputX = boundsInSceneInputNode.getMinX();
        final double inputY = boundsInSceneInputNode.getMinY();
        final double outputX = boundsInSceneOutputNode.getMinX();
        final double outputY = boundsInSceneOutputNode.getMinY();

        this.getPoints().clear();
        this.getPoints().addAll(inputPoint.getX() + inputX, inputPoint.getY() + inputY,
                outputPoint.getX() + outputX, outputPoint.getY() + outputY);
    }
}
