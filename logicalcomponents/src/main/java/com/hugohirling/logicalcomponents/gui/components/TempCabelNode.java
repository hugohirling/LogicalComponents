package com.hugohirling.logicalcomponents.gui.components;

import javafx.scene.shape.Polyline;

public class TempCabelNode extends Polyline {
    
    private final double originX;
    private final double originY;

    public TempCabelNode(final double originX, final double originY, final double x, final double y) {
        this.setStrokeWidth(3);

        this.getPoints().addAll(originX, originY, x, y);

        this.originX = originX;
        this.originY = originY;
    }

    public void setNewTarget(final double x, final double y) {
        this.getPoints().clear();
        this.getPoints().addAll(this.originX, this.originY, x, y);
    }

}
