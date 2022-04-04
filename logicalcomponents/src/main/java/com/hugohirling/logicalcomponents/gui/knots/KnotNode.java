package com.hugohirling.logicalcomponents.gui.knots;

import com.hugohirling.logicalcomponents.gui.CabelNode;
import com.hugohirling.logicalcomponents.util.KnotChangeListener;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Polyline;

/**
 * @author Hugo Hirling
 * @version 01.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of a knot
 */
public abstract class KnotNode extends Arc {

    public enum KnotType {
        INPUT, OUTPUT
    }

    private KnotType knotType;
    private boolean status;
    protected final int startAngle;
    private final boolean changeable;

    private final Pane root;

    private KnotChangeListener listener;

    private final Polyline tempCabelNode;

    public KnotNode(final Pane root, final double x, final double y, final int startAngle, final KnotType knotType) {
        this(root, x, y, false, startAngle, knotType, false);
    }

    public KnotNode(final Pane root, final double x, final double y, final boolean status, final int startAngle, final KnotType knotType, final boolean changeable) {
        super(x, y, 12.5, 12.5, startAngle, 180);

        this.root = root;
        this.changeable = changeable;
        this.knotType = knotType;
        this.startAngle = startAngle;
        this.tempCabelNode = new Polyline();

        this.colorize();

        this.coordinateEvents(); 
    }

    private void coordinateEvents() {
        if(this.changeable) {
            this.setOnMouseClicked(mouseEvent -> {
                this.status = !this.status;
                this.colorize();
                if(listener != null) {
                    listener.onStatusChanged();
                }
            });
        }

        this.setOnDragDetected(event -> {
            this.startFullDrag();
        });

        this.setOnMouseDragged(event -> {
            final Point2D center = this.localToScene(this.getCenterX(), this.getCenterY());

            if(this.root.getChildren().contains(this.tempCabelNode)) {
                this.tempCabelNode.getPoints().clear();
                this.tempCabelNode.getPoints().addAll(center.getX()-200, center.getY(), event.getSceneX()-200, event.getSceneY());
            }else {
                this.tempCabelNode.getPoints().addAll(center.getX()-200, center.getY(), event.getSceneX()-200, event.getSceneY());
                this.root.getChildren().add(this.tempCabelNode);
            }
        });

        this.setOnMouseDragReleased(event -> {
            if (event.getGestureSource() instanceof KnotNode) {
                final KnotNode sourceKnotNode = (KnotNode) event.getGestureSource();

                if (this.knotType != sourceKnotNode.getKnotType()) {
                    if (this.knotType == KnotType.INPUT) {
                        this.root.getChildren().add(new CabelNode(sourceKnotNode, this));
                    } else {
                        this.root.getChildren().add(new CabelNode(this, sourceKnotNode));
                    }
                }
            }
        });

        this.setOnMouseReleased(event -> {    
            this.root.getChildren().remove(this.tempCabelNode);
        });
    }

    /**
     * Colorizes the InputNode dependet on its status
     */
    private final void colorize() {
        if (this.status) {
            this.setFill(Color.RED);
        } else {
            this.setFill(Color.BLACK);
        }
    }

    public boolean getStatus() {
        return this.status;
    }
    public void setStatus(final boolean status) {
        if(this.status != status) {
            this.status = status;
            this.colorize();
            if(this.listener != null) {
                this.listener.onStatusChanged();
            }
        }
    }

    public Point2D getLocation() {
        return new Point2D(this.getCenterX(), this.getCenterY());
    }

    public void setOnKnotChangeListener(final KnotChangeListener listener) {
        this.listener = listener;
    }

    public KnotType getKnotType() {
        return this.knotType;
    }

    public Polyline getTempCabelNode() {
        return this.tempCabelNode;
    }
}