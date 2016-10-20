/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import javax.swing.JPanel;

/**
 * Interface for each pane of the Metrics Tab Panel.
 * @author Dave
 */
public interface MetricsPanel {
    
    /**
     * Get JPanel. 
     * @return JPanel for MetricPanel
     */
    JPanel getPanel();
    
    /** 
     * Get name of panel.
     * 
     * @return Name of panel
     */
    String getName();
}
