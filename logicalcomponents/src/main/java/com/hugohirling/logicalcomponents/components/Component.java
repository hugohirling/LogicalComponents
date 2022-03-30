package com.hugohirling.logicalcomponents.components;

import java.util.List;

import com.hugohirling.logicalcomponents.components.util.ComponentKnot;

/**
 * @author Hugo Hirling
 * @version 30.03.2022
 * @url https://hugohirling.com
 * 
 * A logical component
 */
public interface Component {

    /**
     * Calculates the outputstreams dependent on the inputstreams
     */
    public void step();

    /**
     * Inputstreams with true/false values
     * @return list of all inputstreams
     */
    public List<ComponentKnot> getInputs();
    
    /**
     * Outputstreams with true/false values
     * 
     * @return list of all outputstreams
     */
    public List<ComponentKnot> getOutputs();

    /**
     * Name of logical component
     * 
     * @return name of logical component
     */
    public String getName();
}
