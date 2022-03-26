package com.hugohirling.logicalcomponents.util;

import java.util.Optional;

/**
 * @author Hugo Hirling
 * @version 1.0
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