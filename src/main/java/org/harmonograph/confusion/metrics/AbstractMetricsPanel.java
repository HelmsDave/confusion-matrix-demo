/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import org.harmonograph.confusion.messages.TestResults;

/**
 * Utility abstract metrics panel,
 * simply to avoid boilerplate.
 * @author Dave
 */
public abstract class AbstractMetricsPanel implements MetricsPanel {

    /** Main Panel for Confusion Matrix. */
    protected final JPanel m_panel;      
    
    /** Current Test Results. */
    protected TestResults m_testResults = TestResults.DEFAULT;
    
    /** Name of this panel. */
    protected String m_name;
    
    /** 
     * Simple Constructor.
     * 
     * @param name Name of this panel 
     */
    public AbstractMetricsPanel(final String name) {
        m_name = name;
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
    }
    
    /** {@inheritDoc} */
    @Override    
    public void processTestResults(final TestResults testResults) {
       m_testResults = testResults;
       update();
    }
    
    /** 
     * Perform any update for new test results.
     */
    public abstract void update();
    
    /** {@inheritDoc} */
    @Override
    public JPanel getPanel() {
        return m_panel;
    }
    
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return m_name;
    }      
}
