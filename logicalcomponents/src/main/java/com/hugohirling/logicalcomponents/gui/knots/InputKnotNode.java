package com.hugohirling.logicalcomponents.gui.knots;

import javafx.scene.layout.Pane;

/**
 * @author Hugo Hirling
 * @version 01.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of a input knot
 */
public class InputKnotNode extends KnotNode {

    public InputKnotNode(final Pane root, final double x, final double y, final boolean status, final boolean changeable) {
        super(root, x, y, status, 270, KnotType.INPUT, changeable, 1);
    }
    
    public InputKnotNode(final Pane root, final double x, final double y) {
        super(root, x, y, 270, KnotType.INPUT, 1);
    }

    public InputKnotNode(final Pane root, final double x, final double y, final int startAngle) {
        super(root, x, y, startAngle, KnotType.INPUT, 1);
    }
}
