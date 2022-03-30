package com.hugohirling.logicalcomponents.components.specific;

import com.hugohirling.logicalcomponents.components.SingleComponent;

/**
 * @author Hugo Hirling
 * @version 30.03.2022
 * @url https://hugohirling.com
 * 
 * Specific Component
 * Input: 1
 * Output: 1
 * --> NOT (Inverter)
 */
public class NOTComponent extends SingleComponent {

    public NOTComponent() {
        super(1, 1);
    }

    @Override
    public boolean calculateOutput(int outputIndex) {
        if(this.inputs.get(0).getStatus()) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "NOT";
    }

}