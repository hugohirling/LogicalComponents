package com.hugohirling.logicalcomponents.components.util;

import java.util.Optional;

/**
 * @author Hugo Hirling
 * @version 30.03.2022
 * @url https://hugohirling.com
 * 
 * Represents the cable between two components.
 */
public class ComponentTrace {
    
    private final ComponentKnot outputKnot;
    private final ComponentKnot inputKnot;

    public ComponentTrace(final ComponentKnot outputKnot, final ComponentKnot inputKnot) {
        this.outputKnot = outputKnot;
        this.outputKnot.setTrace(Optional.of(this));

        this.inputKnot = inputKnot;
        this.inputKnot.setTrace(Optional.of(this));
    }

    public void removeTrace() {
        this.outputKnot.removeTrace();
        this.inputKnot.removeTrace();
    }
}
