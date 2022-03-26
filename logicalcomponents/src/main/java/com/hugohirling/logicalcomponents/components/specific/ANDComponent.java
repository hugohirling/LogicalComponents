package com.hugohirling.logicalcomponents.components.specific;

import com.hugohirling.logicalcomponents.components.SingleComponent;

public class ANDComponent extends SingleComponent{

    public ANDComponent() {
        super(2,1);
    }

    @Override
    public boolean calculateOutput(int outputIndex) {
        if(this.inputs.get(0).getStatus() && this.inputs.get(1).getStatus()) {
            return true;
        }
        return false;
    }
    
}
