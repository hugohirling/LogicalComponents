package com.hugohirling.logicalcomponents.gui.components;

import com.hugohirling.logicalcomponents.components.util.ComponentTrace;

import javafx.beans.InvalidationListener;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

/**
 * @author Hugo Hirling
 * @version 01.04.2022
 * @url https://hugohirling.com
 * 
 * Represent the graphical node of /components/util/ComponentTrace
 */
public class CabelNode extends Polyline implements InvalidationListener {

    private final ComponentNode inputNode;
    private final ComponentNode outputNode;
    private final ComponentTrace trace;
    private final int inputIndex;
    private final int outputIndex;

    public CabelNode(final ComponentNode inputNode, final int inputIndex, final ComponentNode outputNode, final int outputIndex) {
        this.setStrokeWidth(3);
        
        this.inputNode = inputNode;
        this.outputNode = outputNode;
        this.trace = new ComponentTrace(this.inputNode.getComponent().getOutputs().get(inputIndex),
            this.outputNode.getComponent().getInputs().get(outputIndex));
        this.inputIndex = inputIndex;
        this.outputIndex = outputIndex;

        this.inputNode.boundsInParentProperty().addListener(this);
        this.outputNode.boundsInParentProperty().addListener(this);

        this.colorize();
    }

    private void colorize() {
        if(this.trace.getInputKnot().getStatus()) {
           this.setStroke(Color.RED);
        } else {
            this.setStroke(Color.BLACK);
        }
    }

    @Override
    public void invalidated(final javafx.beans.Observable observable) {
        final Point2D inputPoint = inputNode.getOutputCords().get(this.inputIndex);
        final Point2D outputPoint = this.outputNode.getInputCords().get(this.outputIndex);

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
