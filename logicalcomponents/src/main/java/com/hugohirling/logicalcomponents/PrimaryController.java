package com.hugohirling.logicalcomponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hugohirling.logicalcomponents.components.specific.ANDComponent;
import com.hugohirling.logicalcomponents.components.specific.NOTComponent;
import com.hugohirling.logicalcomponents.components.specific.ORComponent;
import com.hugohirling.logicalcomponents.gui.components.ComponentNode;
import com.hugohirling.logicalcomponents.gui.components.GeneralInputNode;
import com.hugohirling.logicalcomponents.util.Draggable;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * @author Hugo Hirling
 * @version 30.03.2022
 * @url https://hugohirling.com
 * 
 * Controller of the Primary View/Scene
 */
public class PrimaryController{

    private final Draggable draggable;

    private final List<ComponentNode> componentList;

    private final List<GeneralInputNode> inputList;

    public PrimaryController() {
        this.componentList = new ArrayList<>();
        this.componentList.add(new ComponentNode(new ORComponent()));
        this.componentList.add(new ComponentNode(new ANDComponent()));
        this.componentList.add(new ComponentNode(new NOTComponent()));

        this.inputList = new ArrayList<>();
        this.inputList.add(new GeneralInputNode(400));

        draggable = new Draggable();
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
            this.inputList.add(new GeneralInputNode(startY + this.inputList.size()*(diameter + spacer)));
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
        actionPane.getChildren().addAll(this.componentList);

        actionPane.getChildren().addAll(this.inputList);

        draggable.makeDraggable(this.componentList);

        actionPane.setOnMouseClicked(mouseEvent -> {
            for (final GeneralInputNode node : this.inputList) {
                if (!node.equals(mouseEvent.getSource())) {
                    actionPane.requestFocus();
                }
            }
        });
    }
}
