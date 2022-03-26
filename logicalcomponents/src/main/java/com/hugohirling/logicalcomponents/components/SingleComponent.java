package com.hugohirling.logicalcomponents.components;

import java.util.ArrayList;
import java.util.List;

import com.hugohirling.logicalcomponents.util.ComponentKnot;

/**
 * @author Hugo Hirling
 * @version 1.0
 */

public abstract class SingleComponent implements Component {

    protected final List<ComponentKnot> inputs;
    protected final List<ComponentKnot> outputs;

    public SingleComponent(final int inputCount, final int outputCount) {
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();

        for (int inputIndex = 0; inputIndex < inputCount; inputIndex++) {
            this.inputs.add(new ComponentKnot());
        }
        for (int outputIndex = 0; outputIndex < outputCount; outputIndex++) {
            this.outputs.add(new ComponentKnot());
        }
    }

    @Override
    public final void step() {
        for (int i = 0; i < this.outputs.size(); i++) {
            final ComponentKnot knot = this.outputs.get(i);
            knot.setStatus(this.calculateOutput(i));
        }
    }

    public abstract boolean calculateOutput(final int outputIndex);

}
