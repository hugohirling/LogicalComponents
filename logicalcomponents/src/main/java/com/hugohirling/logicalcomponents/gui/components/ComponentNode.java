package com.hugohirling.logicalcomponents.gui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hugohirling.logicalcomponents.components.Component;
import com.hugohirling.logicalcomponents.components.util.KnotChangeListener;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Hugo Hirling
 * @version 31.03.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of /components/Component
 */
public class ComponentNode extends Pane{

    private final Rectangle rectangle;
    private final List<Arc> inputArcs;
    private final List<Arc> outputArcs;
    private final Text label;

    private final Component component;

    private final List<Point2D> inputCords;
    private final List<Point2D> outputCords;

    private final double compHeight;
    private final double compWidth;

    private double mouseAnchorX;
    private double mouseAnchorY;
    
    public ComponentNode(final Component component) {
        super();
        this.component = component;
        this.component.setNode(this);

        //Define Text
        this.label = new Text(this.component.getName());
        this.label.setFont(new Font(20));
        this.label.setTextAlignment(TextAlignment.CENTER);
        this.compWidth = 75 + this.label.getBoundsInLocal().getWidth();

        this.compHeight = this.calcRectHeight();

        //Define Rectangle
        this.rectangle = new Rectangle(0, 0, this.compWidth, this.compHeight);
        this.rectangle.setArcHeight(30);
        this.rectangle.setArcWidth(30);
        this.rectangle.setFill(Color.AQUA);

        this.getChildren().add(this.rectangle);

        //Define Inputs/Outputs
        this.inputCords = new ArrayList<>();
        this.outputCords = new ArrayList<>();
        this.fillCordLists();
        

        //Define Arcs
        this.inputArcs = this.inputCords.stream().map((point) -> 
            new Arc(
                point.getX(), point.getY(), 12.5, 12.5, 270, 180)
        ).collect(Collectors.toList());
        this.outputArcs = this.outputCords.stream().map((point) ->
            new Arc(
                point.getX(), point.getY(), 12.5, 12.5, 90, 180)
        ).collect(Collectors.toList());

        this.getChildren().addAll(this.inputArcs);
        this.getChildren().addAll(this.outputArcs);

        //Arrange Text
        this.label.setX(this.compWidth/2 - this.label.getBoundsInLocal().getWidth()/2);
        this.label.setY(this.compHeight/2 + this.label.getBoundsInLocal().getHeight()/4);

        this.getChildren().add(this.label);

        this.colorize();

        this.setOnMousePressed(event -> {
            this.mouseAnchorX = event.getX();
            this.mouseAnchorY = event.getY();
        });

        this.setOnMouseDragged(event -> {
            final double newX = event.getSceneX() - this.mouseAnchorX - 200;
            final double newY = event.getSceneY() - this.mouseAnchorY;

            if (newX > 0 && newX < (1000 - this.compWidth)) {
                this.setLayoutX(newX);
            }
            if (newY > 0 && newY < (800 - this.compHeight)) {
                this.setLayoutY(newY);
            }
        });
    
        this.component.getInputs().forEach(inputKnot -> {
            inputKnot.setListener(new KnotChangeListener() {
                @Override
                public void onStatusChanged() {
                    colorize();
                }
            });
        });
    }

    /**
     * Arc gets a red color if input/output-status of component is true
     */
    private void colorize() {
        for(int i=0; i<this.component.getInputs().size(); i++) {
            final Arc input = this.inputArcs.get(i);
            if(this.component.getInputs().get(i).getStatus()) {
                input.setFill(Color.RED);
            }else{
                input.setFill(Color.BLACK);
            }
        }

        for (int i = 0; i < this.component.getOutputs().size(); i++) {
            final Arc output = this.outputArcs.get(i);
            if (this.component.getOutputs().get(i).getStatus()) {
                output.setFill(Color.RED);
            } else {
                output.setFill(Color.BLACK);
            }
        }
    }

    /**
     * Calculates the coordinates of input/output arcs
     */
    private void fillCordLists() {
        if(this.component.getInputs().size() > this.component.getOutputs().size()) {
            for (int i = 0; i < this.component.getInputs().size(); i++) {
                final double y = 37.5 + i * 45;
                inputCords.add(new Point2D(0, y));
            }

            for(int i=0; i< this.component.getOutputs().size(); i++) {
                final double availableSpace = this.compHeight-50;
                final double spaceBetween = availableSpace- this.component.getOutputs().size()*25;
                final double spaceLeft = spaceBetween- (this.component.getOutputs().size()-1)*20;
                final double spacer = spaceLeft / (this.component.getOutputs().size()+1);
                final double y = 37.5 + spacer + i*(45+spacer);
                outputCords.add(new Point2D(this.compWidth, y));
            }
        }else{
            for (int i = 0; i < this.component.getOutputs().size(); i++) {
                final double y = 37.5 + i * 45;
                outputCords.add(new Point2D(this.compWidth, y));
            }

            for (int i = 0; i < this.component.getInputs().size(); i++) {
                final double availableSpace = this.compHeight - 50;
                final double spaceBetween = availableSpace - this.component.getOutputs().size() * 25;
                final double spaceLeft = spaceBetween - (this.component.getOutputs().size() - 1) * 20;
                final double spacer = spaceLeft / (this.component.getOutputs().size() + 1);
                final double y = 37.5 + spacer + i * (45 + spacer);
                inputCords.add(new Point2D(0, y));
            }
        }


    }

    /**
     * Calculates the height of the component. Height gets calculated by the input/output side dependent of their size.
     * @return height of component
     */
    private double calcRectHeight() {
        double height = 50;
        if(this.component.getInputs().size() > this.component.getOutputs().size()) {
            height += (45* this.component.getInputs().size())-20;
        }else{
            height += (45 * this.component.getOutputs().size()) - 20;
        }
        return height;
    }

    public Component getComponent() {
        return this.component;
    }

    public List<Point2D> getInputCords() {
        return this.inputCords;
    }
    
    public List<Point2D> getOutputCords() {
        return this.outputCords;
    }
}
