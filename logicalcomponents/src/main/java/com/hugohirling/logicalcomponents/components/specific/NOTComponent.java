package com.hugohirling.logicalcomponents.components.specific;

import com.hugohirling.logicalcomponents.components.SingleComponent;

public class NOTComponent extends SingleComponent {

    public NOTComponent() {
        super(1, 1);
    }

    @Override
    public boolean calculateOutput(int outputIndex) {
        if (this.inputs.get(0).getStatus()) {
            return false;
        }
        return true;
    }

}