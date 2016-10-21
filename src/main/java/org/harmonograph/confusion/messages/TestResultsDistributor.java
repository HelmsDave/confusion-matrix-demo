/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.messages;

import java.util.List;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 * Distributor for TestResults messages.
 * Since this is a simple demo application, 
 * use a central instance to distribute events.
 * @author Dave
 */
public class TestResultsDistributor implements TestResultsListener {

    /** Singleton instance.  */
    public static final TestResultsDistributor DISTRIBUTOR = new TestResultsDistributor();
    
    /** List of registered listeners. */
    protected final List<TestResultsListener> m_listeners = new ArrayList<>();
    
    
    
    /**
     * Distribute message.
     * 
     * @param testResults Message to distribute
     */
    public void processTestResults(final TestResults testResults) {
       if (testResults == null) {
           return;
       }
       final List<TestResultsListener> listeners = new ArrayList<>();
       listeners.addAll(m_listeners);
        SwingUtilities.invokeLater(new Runnable() {
            /** {@inheritDoc} */
            @Override            
            public void run() {
                for (final TestResultsListener l: listeners) {
                    try {
                        l.processTestResults(testResults);
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });       
    }
    
    /** 
     * Add listener to Distributor. 
     * 
     * @param listener Listener to add 
     */
    public void addListener(final TestResultsListener listener) {
        if (listener != null) {
            m_listeners.add(listener);
        }
    }
    
    
    /** 
     * Remove listener from Distributor. 
     * 
     * @param listener Listener to remove 
     */
    public void removeListener(final TestResultsListener listener) {
        if (listener != null) {
            m_listeners.remove(listener);
        }
    }    
    
    
}
