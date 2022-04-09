package com.hugohirling.logicalcomponents.gui.knots.appnodes;

import com.hugohirling.logicalcomponents.gui.knots.KnotNode.KnotType;

import javafx.scene.layout.Pane;

/**
 * @author Hugo Hirling
 * @version 09.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of an app input knot
 */
public class AppInputKnotNode extends AppKnotNode {
    
    public AppInputKnotNode(final Pane root, final double y) {
        super(root, y, false, "Input", KnotType.INPUT);
    }
    public AppInputKnotNode(final Pane root, final double y, final String name, final boolean status) {
        super(root, y, status, name, KnotType.INPUT);
    }
}


