package com.hugohirling.logicalcomponents.components.util;

import java.util.Optional;

import com.hugohirling.logicalcomponents.components.util.ComponentKnot.KnotType;

/**
 * @author Hugo Hirling
 * @version 31.03.2022
 * @url https://hugohirling.com
 * 
 * Represents the cable between two components.
 */
public class ComponentTrace {
    
    private final ComponentKnot outputKnot;
    private final ComponentKnot inputKnot;

    public ComponentTrace(final ComponentKnot outputKnot, final ComponentKnot inputKnot) {
        if (inputKnot.getType() != KnotType.OUTPUT || outputKnot.getType() != KnotType.INPUT) {
            throw new IllegalArgumentException("InputKnot must be KnotType.OUTPUT. OutputKnot must be KnotType.INPUT.");
        }

        this.outputKnot = outputKnot;
        this.outputKnot.setTrace(Optional.of(this));

        this.inputKnot = inputKnot;
        this.inputKnot.setTrace(Optional.of(this));
    }

    public void removeTrace() {
        this.outputKnot.removeTrace();
        this.inputKnot.removeTrace();
    }

    public ComponentKnot getOutputKnot() {
        return this.outputKnot;
    }
    public ComponentKnot getInputKnot() {
        return this.inputKnot;
    }
}
