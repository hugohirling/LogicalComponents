package com.hugohirling.logicalcomponents.util;

import java.util.List;

import com.hugohirling.logicalcomponents.gui.components.ComponentNode;

import javafx.scene.Node;

/**
 * @author Hugo Hirling
 * @version 30.03.2022
 * @url https://hugohirling.com
 * 
 * Makes ComponentNode "draggable".
 */
public class Draggable {

    private double mouseAnchorX;
    private double mouseAnchorY;

    /**
     * Makes a Node draggable inside 1000x800 canvas.
     * Canvas must be inside and right-aligned of 1200x800 canvas.
     * 
     * @param node Node to make "draggable"
     */
    public void makeDraggable(final Node node) {

        final double nodeWidth = node.getBoundsInLocal().getWidth();
        final double nodeHeight = node.getBoundsInLocal().getHeight();
        
        node.setOnMousePressed(mouseEvent -> {
            this.mouseAnchorX = mouseEvent.getX();
            this.mouseAnchorY = mouseEvent.getY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            final double newX = mouseEvent.getSceneX() - mouseAnchorX-200;
            final double newY = mouseEvent.getSceneY() - mouseAnchorY;

            if(newX > 0 && newX < (1000-nodeWidth)) {
                node.setLayoutX(newX);
            }
            if(newY > 0 && newY < (800-nodeHeight)) {
                node.setLayoutY(newY);   
            }    
        });
    }

    /**
     * Overloading "makeDraggable()" to make a List of nodes "draggable"
     * 
     * @param nodes List of ComponentNode. All nodes will get "draggable".
     */
    public void makeDraggable(final List<ComponentNode> nodes) {
        for(final ComponentNode node : nodes) {
            this.makeDraggable(node);
        }
    }
}
