package com.hugohirling.logicalcomponents.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener{

    private final List<JButton> inputButtonList;
    
    public MainFrame() {
        super("Logical Components");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final JButton addInputButton = new JButton("Add Input");
        addInputButton.addActionListener(this);
        
        this.setLayout(new BorderLayout());

        this.add(addInputButton, BorderLayout.LINE_START);
        this.pack();
        this.setVisible(true);

        this.inputButtonList = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(this.inputButtonList.size() < 7) {
            final String index = "#" + (this.inputButtonList.size());
            this.inputButtonList.add(new JButton(index));

            this.add(this.inputButtonList.get(this.inputButtonList.size()-1));
            this.revalidate();
            this.repaint();
        }
        
    }
}
