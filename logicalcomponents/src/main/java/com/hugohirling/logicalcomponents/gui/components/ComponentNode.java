package com.hugohirling.logicalcomponents.gui.components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.hugohirling.logicalcomponents.gui.knots.InputKnotNode;
import com.hugohirling.logicalcomponents.gui.knots.OutputKnotNode;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Hugo Hirling
 * @version 04.04.2022
 * @url https://hugohirling.com
 * 
 * Represents the graphical node of component
 */
public abstract class ComponentNode extends Pane {

    private final Pane root;

    private final Text label;
    private final Rectangle rectangle;

    private final String componentName;
    private final double compWidth;
    private final double compHeight;

    protected final List<InputKnotNode> inputNodes;
    protected final List<OutputKnotNode> outputNodes;

    private double mouseAnchorX;
    private double mouseAnchorY;
    
    public ComponentNode(final Pane root, final String componentName, final Point2D location, final int inputCount, final int outputCount) {
        super();
        this.setLayoutX(location.getX());
        this.setLayoutY(location.getY());

        if(inputCount < 1 || outputCount < 1) {
            throw new IllegalArgumentException("Component must have at least one input and one output");
        }
        if(componentName == null || componentName.isEmpty()) {
            throw new IllegalArgumentException("Component must have a name");
        }
        if(root == null) {
            throw new IllegalArgumentException("Component must have a root-pane");
        }

        this.root = root;
        this.componentName = componentName;

        //Define Text
        this.label = this.createText();

        this.compWidth = 75 + this.label.getBoundsInLocal().getWidth();
        this.compHeight = this.calcRectHeight(inputCount, outputCount);

        //Arrange Text
        this.label.setX(this.compWidth/2 - this.label.getBoundsInLocal().getWidth()/2);
        this.label.setY(this.compHeight/2 - this.label.getBoundsInLocal().getHeight()/2);

        //Define Rectangle
        this.rectangle = this.createRectangle();

        // Generate In-/Output Notes
        this.inputNodes = new ArrayList<>();
        this.outputNodes = new ArrayList<>();
        this.createKnotNodes(inputCount, outputCount);

        this.getChildren().add(this.rectangle);
        this.getChildren().add(this.label);
        this.getChildren().addAll(this.inputNodes);
        this.getChildren().addAll(this.outputNodes);

        //EventHandler
        this.eventListener();
        IntStream.range(0, this.outputNodes.size()).forEach(i -> this.calculateOutput(i));
        this.inputNodes.forEach(input -> input.setOnKnotChangeListener(() -> {
            IntStream.range(0, this.outputNodes.size()).forEach(i -> this.calculateOutput(i));
        }));
    }

    public abstract void calculateOutput(final int outputIndex);

    /**
     * Returns the rectangle of the component
     * 
     * @param location of origin(left-top)
     * @return Rectangle
     */
    private Rectangle createRectangle() {
        final Rectangle rectangle = new Rectangle(0, 0, this.compWidth, this.compHeight);
        rectangle.setArcHeight(30);
        rectangle.setArcWidth(30);
        rectangle.setFill(Color.AQUA);
        return rectangle;
    }

    /**
     * Returns the label/text of the component
     * 
     * @return Text
     */
    private Text createText() {
        final Text label = new Text(this.componentName);
        label.setFont(new Font(20));
        label.setTextAlignment(TextAlignment.CENTER);
        return label;
    }
   
    /**
     * Calculates the height of the component. Height gets calculated by the
     * input/output side dependent of their size.
     * 
     * @return height of component
     */
    private double calcRectHeight(final int inputCount, final int outputCount) {
        double height = 50;
        if (inputCount > outputCount) {
            height += (45* inputCount) - 20;
        }else {
            height += (45 * outputCount) - 20;
        }
        return height;
    }

    /**
     * Generates the Input and Output Knots
     */
    private void createKnotNodes(final int inputCount, final int outputCount) {
        if(inputCount > outputCount) {
            for(int i = 0; i < inputCount; i++) {
                this.inputNodes.add(new InputKnotNode(this.root, 0, (45*i) + 37.5));
            }

            for(int i=0; i< outputCount; i++) {
                final double availableSpace = this.compHeight - 50;
                final double spaceBetween = availableSpace - outputCount * 25;
                final double spaceLeft = spaceBetween - (outputCount - 1) * 20;
                final double spacer = spaceLeft / (outputCount + 1);
                final double y = 37.5 + spacer + i * (45 + spacer);
                this.outputNodes.add(new OutputKnotNode(this.root, this.compWidth, y));
            }
        }else {
            for (int i = 0; i<outputCount; i++) {
                this.outputNodes.add(new OutputKnotNode(this.root, this.compWidth, (45 * i) + 37.5));
            }

            for (int i = 0; i < inputCount; i++) {
                final double availableSpace = this.compHeight - 50;
                final double spaceBetween = availableSpace - outputCount * 25;
                final double spaceLeft = spaceBetween - (outputCount - 1) * 20;
                final double spacer = spaceLeft / (outputCount + 1);
                final double y = 37.5 + spacer + i * (45 + spacer);
                this.inputNodes.add(new InputKnotNode(this.root, 0, y));
            }
        }
    }

    private void eventListener() {
        //DraggEvent
        this.setOnMousePressed(event -> {
            this.mouseAnchorX = event.getX();
            this.mouseAnchorY = event.getY();
        });

        //DraggEvent
        this.setOnMouseDragged(event -> {
            final double newX = event.getSceneX() - this.mouseAnchorX - 200;
            final double newY = event.getSceneY() - this.mouseAnchorY;

            if (newX > 0 && newX < (1000 - this.compWidth)) {
                this.setLayoutX(newX);
                //System.out.println("NewX: event.getSceneX()=" + event.getSceneX() + " mouseAnchorX=" + this.mouseAnchorX);
            }
            if (newY > 0 && newY < (800 - this.compHeight)) {
                this.setLayoutY(newY);
                //System.out.println("NewY: event.getSceneY()=" + event.getSceneY() + " mouseAnchorY=" + this.mouseAnchorY);
            }
        });
    }

    public List<InputKnotNode> getInputNodes() {
        return this.inputNodes;
    }
    public List<OutputKnotNode> getOutputNodes() {
        return this.outputNodes;
    }
}
