/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.InfoGraphic.Elements;

/**
 * Utility abstract metrics panel,
 * simply to avoid boilerplate.
 * Most panels should subclass this and implement
 * updateLabelString() to show the desired text.
 * @author Dave
 */
public abstract class AbstractMetricsPanel implements MetricsPanel {

    /** Main Panel for Confusion Matrix. */
    protected final JPanel m_panel;      
    
    /** This label holds a multi-line html string, 
     * which is the contents of this panel. */
    protected final JLabel m_label;    
    
    /** Generic gas gauge. */
    protected final GasGauge m_gas;
    
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
        
        {
            m_label = new JLabel();
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = 1f;
            c.weighty = 1f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_label, c);
      }       
        
      { 
            final Elements[] elements = updateGraphic();
            if (elements.length > 0) {
                final InfoGraphic graphic = new InfoGraphic(elements);

                final GridBagConstraints c = new GridBagConstraints();
                c.gridx = 2;
                c.gridy = 1;
                c.weightx = .1f;
                c.weighty = .1f;                
                //c.fill = GridBagConstraints.BOTH;
                m_panel.add(graphic.getPanel(), c);
            }
        }           
        
        {
            m_gas = new GasGauge(Color.CYAN);
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 1;
            c.weighty = 1f;
            c.weighty = .1f;
            c.fill = GridBagConstraints.VERTICAL;
            m_panel.add(m_gas, c);
        }          
        
        
        processTestResults(TestResults.DEFAULT);
    }
    
    /** {@inheritDoc} */
    @Override    
    public void processTestResults(final TestResults testResults) {
       final String labelText = updateLabelString(testResults);
       m_label.setText(labelText);
       
       final float level = updateGas(testResults);
       m_gas.setLevel(level);
    }
    
    /** 
     * Perform any update for new test results.
     * 
     * @param testResults Test results for update
     * @return formatted string for label
     */
    public abstract String updateLabelString(final TestResults testResults);
    
    /** 
     * Calculate value for gas gauge, range 0..1,
     * representing normalized value of metric, if applicable.
     * 
     * @param testResults Test results for update
     * @return Level for gas gauge, from 0 to 1
     */
    public float updateGas(final TestResults testResults) {
        return 0f;
    }
    
    /** 
     * Get data for graphic. 
     * 
     * @return Graphic elements
     */
    public InfoGraphic.Elements[] updateGraphic() {
        return new InfoGraphic.Elements[] {};
    }
    
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
