package com.hugohirling.logicalcomponents.gui.components.specific;

import com.hugohirling.logicalcomponents.gui.components.ComponentNode;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

/**
 * @author Hugo Hirling
 * @version 04.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of an OR-Component
 */
public class ORComponentNode extends ComponentNode {

    public ORComponentNode(final Pane root, final Point2D location) {
        super(root, "OR", location, 2, 1);
    }

    @Override
    public void calculateOutput(final int outputIndex) {
        if(outputIndex != 0) {
            throw new IllegalArgumentException("Output index must be 0");
        }

        if(this.inputNodes.get(0).getStatus() || this.inputNodes.get(1).getStatus()) {
            this.outputNodes.get(0).setStatus(true);
        }else {
            this.outputNodes.get(0).setStatus(false);
        }
    }
}
