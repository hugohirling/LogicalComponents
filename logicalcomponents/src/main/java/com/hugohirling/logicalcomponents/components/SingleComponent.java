package com.hugohirling.logicalcomponents.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hugohirling.logicalcomponents.components.util.ComponentKnot;
import com.hugohirling.logicalcomponents.components.util.ComponentKnot.KnotType;
import com.hugohirling.logicalcomponents.gui.components.ComponentNode;

/**
 * @author Hugo Hirling
 * @version 31.03.2022
 * @url https://hugohirling.com
 * 
 * Simple logical component existing from one one component(OR, AND, NOT, ...)
 */
public abstract class SingleComponent implements Component {

    // invariant this.inputs.size() > 0;
    protected final List<ComponentKnot> inputs;
    // invariant this.outputs.size() > 0;
    protected final List<ComponentKnot> outputs;

    private Optional<ComponentNode> guiNode;

    public SingleComponent(final int inputCount, final int outputCount) {
        if(inputCount < 1 || outputCount < 1) {
            throw new IllegalArgumentException("Component must have at least one input and output.");
        }

        this.guiNode = Optional.empty();

        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();

        for (int inputIndex = 0; inputIndex < inputCount; inputIndex++) {
            this.inputs.add(new ComponentKnot(KnotType.INPUT));
        }
        for (int outputIndex = 0; outputIndex < outputCount; outputIndex++) {
            this.outputs.add(new ComponentKnot(KnotType.OUTPUT));
        }

        this.step();
    }

    @Override
    public final void step() {
        for (int i = 0; i < this.outputs.size(); i++) {
            final ComponentKnot knot = this.outputs.get(i);
            knot.setStatus(this.calculateOutput(i));
        }
    }

    @Override
    public final List<ComponentKnot> getInputs() {
        return this.inputs;
    } 
    @Override
    public final List<ComponentKnot> getOutputs() {
        return this.outputs;
    }
    @Override
    public final Optional<ComponentNode> getNode() {
        return this.guiNode;
    }
    @Override
    public final void setNode(final ComponentNode node) {
        this.guiNode = Optional.of(node);
    }

    public abstract boolean calculateOutput(final int outputIndex);

}
