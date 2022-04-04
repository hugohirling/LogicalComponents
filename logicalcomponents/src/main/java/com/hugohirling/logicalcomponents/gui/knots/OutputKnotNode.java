package com.hugohirling.logicalcomponents.gui.knots;

import com.hugohirling.logicalcomponents.gui.knots.KnotNode.KnotType;

import javafx.scene.layout.Pane;

/**
 * @author Hugo Hirling
 * @version 01.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of a output knot
 */
public class OutputKnotNode extends KnotNode {
    
    public OutputKnotNode(final Pane root, final double x, final double y, final boolean status) {
        super(root, x, y, status, 90, KnotType.OUTPUT, false);
    }
    public OutputKnotNode(final Pane root, final double x, final double y) {
        super(root, x, y, 90, KnotType.OUTPUT);
    }
}
