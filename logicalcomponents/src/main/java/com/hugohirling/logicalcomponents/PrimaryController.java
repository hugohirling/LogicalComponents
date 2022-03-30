package com.hugohirling.logicalcomponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hugohirling.logicalcomponents.components.specific.ANDComponent;
import com.hugohirling.logicalcomponents.components.specific.NOTComponent;
import com.hugohirling.logicalcomponents.components.specific.ORComponent;
import com.hugohirling.logicalcomponents.gui.components.ComponentNode;
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

    public PrimaryController() {
        this.componentList = new ArrayList<>();
        this.componentList.add(new ComponentNode(new ORComponent()));
        this.componentList.add(new ComponentNode(new ANDComponent()));
        this.componentList.add(new ComponentNode(new NOTComponent()));

        draggable = new Draggable();
    }

    @FXML
    private Pane actionPane;

    @FXML
    private void addInput() throws IOException{
    }
    
    @FXML
    private void removeInput() throws IOException {
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

        draggable.makeDraggable(this.componentList);
    }
    
}
