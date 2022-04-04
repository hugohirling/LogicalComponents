package com.hugohirling.logicalcomponents.gui.components.specific;

import com.hugohirling.logicalcomponents.gui.components.ComponentNode;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

/**
 * @author Hugo Hirling
 * @version 04.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of an NOT-Component
 */
public class NOTComponentNode extends ComponentNode {

    public NOTComponentNode(final Pane root, final Point2D location) {
        super(root, "NOT", location, 1, 1);
    }

    @Override
    public void calculateOutput(final int outputIndex) {
        if(outputIndex != 0) {
            throw new IllegalArgumentException("Output index must be 0");
        }

        this.outputNodes.get(0).setStatus(!this.inputNodes.get(0).getStatus());
    }
}
