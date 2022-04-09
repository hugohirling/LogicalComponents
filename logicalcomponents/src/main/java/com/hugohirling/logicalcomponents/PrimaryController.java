package com.hugohirling.logicalcomponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hugohirling.logicalcomponents.gui.components.ComponentShowNode;
import com.hugohirling.logicalcomponents.gui.components.specific.ANDComponentNode;
import com.hugohirling.logicalcomponents.gui.components.specific.NOTComponentNode;
import com.hugohirling.logicalcomponents.gui.components.specific.ORComponentNode;
import com.hugohirling.logicalcomponents.gui.knots.appnodes.AppInputKnotNode;
import com.hugohirling.logicalcomponents.gui.knots.appnodes.AppOutputKnotNode;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

/**
 * @author Hugo Hirling
 * @version 09.04.2022
 * @url https://hugohirling.com
 * 
 * Controller of the Primary View/Scene
 */
public class PrimaryController{

    private final List<AppInputKnotNode> inputList;
    private final List<AppOutputKnotNode> outputList;

    private final List<ComponentShowNode> componentList;

    public PrimaryController() {
        this.componentList = new ArrayList<>();
        this.inputList = new ArrayList<>();
        this.outputList = new ArrayList<>();


        this.componentList.add(
            new ComponentShowNode("OR",
                () -> {this.actionPane.getChildren().add(new ORComponentNode(this.actionPane, new Point2D(500, 400)));}
            )
        );
        this.componentList.add(
            new ComponentShowNode("AND",
                () -> {this.actionPane.getChildren().add(new ANDComponentNode(this.actionPane, new Point2D(500, 400)));}
            )
        );
        this.componentList.add(
            new ComponentShowNode("NOT",
                () -> {this.actionPane.getChildren().add(new NOTComponentNode(this.actionPane, new Point2D(500, 400)));}
            )
        );
    }

    @FXML
    private Pane actionPane;

    @FXML
    private ListView<ComponentShowNode> componentListView;

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
            this.inputList.add(new AppInputKnotNode(
                    this.actionPane, startY + this.inputList.size()*(diameter + spacer)));
            actionPane.getChildren().add(this.inputList.get(this.inputList.size()-1));
        }
    }
    
    @FXML
    private void removeInput() throws IOException {
        if(this.inputList.size() > 1) {
            final AppInputKnotNode node = this.inputList.get(this.inputList.size() - 1);

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
        final double spacer = 30;
        final double diameter = 25;

        final double startY = 400 - this.outputList.size() * (spacer / 2 + diameter / 2);

        // Preventing to many Outputs
        if (startY > 75) {
            for (int i = 0; i < this.outputList.size(); i++) {
                this.outputList.get(i).setY(startY + i * (diameter + spacer));
            }
            this.outputList.add(new AppOutputKnotNode(
                    this.actionPane, startY + this.outputList.size() * (diameter + spacer)));
            actionPane.getChildren().add(this.outputList.get(this.outputList.size() - 1));
        }
    }
    
    @FXML
    private void removeOutput() throws IOException {
        if (this.outputList.size() > 1) {
            final AppOutputKnotNode node = this.outputList.get(this.outputList.size() - 1);

            actionPane.getChildren().remove(node);
            this.outputList.remove(node);

            final double spacer = 30;
            final double diameter = 25;

            final double startY = 400 - (this.outputList.size() - 1) * (spacer / 2 + diameter / 2);

            for (int i = 0; i < this.outputList.size(); i++) {
                this.outputList.get(i).setY(startY + i * (diameter + spacer));
            }
        }
    }

    @FXML
    public void initialize() {

        this.inputList.add(new AppInputKnotNode(this.actionPane, 400));
        this.outputList.add(new AppOutputKnotNode(this.actionPane, 400));

        actionPane.getChildren().addAll(this.inputList);
        actionPane.getChildren().addAll(this.outputList);

        actionPane.setOnMouseClicked(mouseEvent -> {
            for (final AppInputKnotNode node : this.inputList) {
                if (!node.equals(mouseEvent.getSource())) {
                    actionPane.requestFocus();
                }
            }
        });

        this.componentListView.getItems().addAll(this.componentList);
    }
}
