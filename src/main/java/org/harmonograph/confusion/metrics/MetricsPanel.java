/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import javax.swing.JPanel;
import org.harmonograph.confusion.messages.TestResultsListener;

/**
 * Interface for each pane of the Metrics Tab Panel.
 * @author Dave
 */
public interface MetricsPanel extends TestResultsListener {
    
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
