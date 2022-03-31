package com.hugohirling.logicalcomponents.components.util;

import java.util.Optional;

/**
 * @author Hugo Hirling
 * @version 31.03.2022
 * @url https://hugohirling.com
 * 
 * Represents the Input/Output of a component.
 * Has true/false value for the logical numbers 0/1.
 */
public class ComponentKnot {

    public enum KnotType {
        OUTPUT,
        INPUT
    }
    
    private boolean status;
    private Optional<ComponentTrace> trace;

    private final KnotType type;

    public ComponentKnot(final KnotType type) {
        this.status = false;
        this.trace = Optional.empty();
        this.type = type;
    }   
    public ComponentKnot(final KnotType type, final ComponentTrace trace) {
        this.type = type;
        this.status = false;
        this.trace = Optional.of(trace);
    }
    public ComponentKnot(final KnotType type, final boolean status) {
        this.type = type;
        this.status = status;
    }   
    public ComponentKnot(final KnotType type, final boolean status, final ComponentTrace trace) {
        this.type = type;
        this.status = status;
        this.trace = Optional.of(trace);
    }

    public boolean getStatus() {
        return this.status;
    }
    public Optional<ComponentTrace> getTrace() {
        return this.trace;
    }

    public void setStatus(final boolean status) {
        this.status = status;
    }
    public void setTrace(final Optional<ComponentTrace> trace) {
        this.trace = trace;
    }
    public void removeTrace() {
        this.trace = Optional.empty();
    }

    public KnotType getType() {
        return this.type;
    }
}