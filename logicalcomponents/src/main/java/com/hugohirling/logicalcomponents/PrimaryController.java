package com.hugohirling.logicalcomponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hugohirling.logicalcomponents.gui.CabelNode;
import com.hugohirling.logicalcomponents.gui.components.ComponentNode;
import com.hugohirling.logicalcomponents.gui.components.specific.ANDComponentNode;
import com.hugohirling.logicalcomponents.gui.components.specific.NOTComponentNode;
import com.hugohirling.logicalcomponents.gui.components.specific.ORComponentNode;
import com.hugohirling.logicalcomponents.gui.knots.GeneralInputNode;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

/**
 * @author Hugo Hirling
 * @version 30.03.2022
 * @url https://hugohirling.com
 * 
 * Controller of the Primary View/Scene
 */
public class PrimaryController{

    private final List<ComponentNode> componentList;

    private final List<GeneralInputNode> inputList;

    public PrimaryController() {
        this.componentList = new ArrayList<>();
        this.inputList = new ArrayList<>();
    }

    @FXML
    private Pane actionPane;

    @FXML
    private void addInput() throws IOException{
        final double spacer = 30;
        final double diameter = 25;

        final double startY = 400 - this.inputList.size()*(spacer/2 + diameter/2);

        //Preventing to many Inputs
        if(startY > 75) {
            for(int i=0; i<this.inputList.size(); i++) {
                this.inputList.get(i).setY(startY + i*(diameter + spacer));
            }
            this.inputList.add(new GeneralInputNode(
                    this.actionPane, startY + this.inputList.size()*(diameter + spacer)));
            actionPane.getChildren().add(this.inputList.get(this.inputList.size()-1));
        }
    }
    
    @FXML
    private void removeInput() throws IOException {
        if(this.inputList.size() > 1) {
            final GeneralInputNode node = this.inputList.get(this.inputList.size() - 1);

            actionPane.getChildren().remove(node);
            this.inputList.remove(node);

            final double spacer = 30;
            final double diameter = 25;

            final double startY = 400 - (this.inputList.size()-1)* (spacer / 2 + diameter / 2);

            for (int i = 0; i < this.inputList.size(); i++) {
                this.inputList.get(i).setY(startY + i * (diameter + spacer));
            }
        }
    }
    
    @FXML
    private void addOutput() throws IOException {
    }
    
    @FXML
    private void removeOutput() throws IOException {
    }

    @FXML
    public void initialize() {
        this.componentList.add(new ORComponentNode(this.actionPane, new Point2D(0, 0)));
        this.componentList.add(new ANDComponentNode(this.actionPane, new Point2D(100, 100)));
        this.componentList.add(new NOTComponentNode(this.actionPane, new Point2D(200, 200)));
        this.componentList.add(new NOTComponentNode(this.actionPane, new Point2D(300, 300)));

        this.inputList.add(new GeneralInputNode(this.actionPane, 400));

        actionPane.getChildren().addAll(this.componentList);

        actionPane.getChildren().addAll(this.inputList);

        actionPane.getChildren().add(new CabelNode(this.componentList.get(2), 0,
                this.componentList.get(1), 0));
        actionPane.getChildren().add(new CabelNode(this.componentList.get(3), 0,
                this.componentList.get(1), 1));

        actionPane.setOnMouseClicked(mouseEvent -> {
            for (final GeneralInputNode node : this.inputList) {
                if (!node.equals(mouseEvent.getSource())) {
                    actionPane.requestFocus();
                }
            }
        });
    }
}
