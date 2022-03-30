package com.hugohirling.logicalcomponents.components.util;

import java.util.Optional;

/**
 * @author Hugo Hirling
 * @version 30.03.2022
 * @url https://hugohirling.com
 * 
 * Represents the Input/Output of a component.
 * Has true/false value for the logical numbers 0/1.
 */
public class ComponentKnot {
    
    private boolean status;
    private Optional<ComponentTrace> trace;

    public ComponentKnot() {
        this.status = false;
        this.trace = Optional.empty();
    }   
    public ComponentKnot(final ComponentTrace trace) {
        this.status = false;
        this.trace = Optional.of(trace);
    }
    public ComponentKnot(final boolean status) {
        this.status = status;
    }   
    public ComponentKnot(final boolean status, final ComponentTrace trace) {
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
}