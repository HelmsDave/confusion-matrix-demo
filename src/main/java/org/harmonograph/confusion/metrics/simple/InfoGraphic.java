/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.simple;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.util.List;
import java.util.Arrays;
import javax.swing.JSeparator;
import org.harmonograph.confusion.messages.ColorScheme;

/**
 * Widget for representing simple metrics in terms
 * of the elements of the confusion matrix used 
 * in the formula.  
 * 
 * @author Dave
 */
public class InfoGraphic {
     
    public enum Elements {
        /** Enable Numerator for True Positive. */
        NUM_TRUE_POS,
        /** Enable Numerator for False Negative. */
        NUM_FALSE_NEG,
        /** Enable Numerator for False Positive. */
        NUM_FALSE_POS,
        /** Enable Numerator for True Negative. */
        NUM_TRUE_NEG,
        
        /** Enable Denominator for True Positive. */
        DENOM_TRUE_POS,
        /** Enable Denominator for False Negative. */
        DENOM_FALSE_NEG,
        /** Enable Denominator for False Positive. */
        DENOM_FALSE_POS,
        /** Enable Denominator for True Negative. */
        DENOM_TRUE_NEG,             
    }
    
    /** Main Panel for graphic. */
    protected final JPanel m_panel;      
    
    
    /**
     * Simple constructor.
     * @param elements List of elements to light up.
     */
    public InfoGraphic(final Elements[] elements) {
        final List<Elements> elementsList = Arrays.asList(elements);
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
        m_panel.setMinimumSize(new Dimension(50, 100));
        m_panel.setPreferredSize(new Dimension(50, 100));
        m_panel.setMaximumSize(new Dimension(100, 200));
        m_panel.setBorder(BorderFactory.createLineBorder(m_panel.getForeground()));
        
        final String numeratorTooltipText = 
                "<html>Portion of confusion matrix<br>in formula numerator</html>";
        final String denominatorTooltipText = 
                "<html>Portion of confusion matrix<br>in formula denominator</html>";        
        
        // Numerator
        {
            final JPanel panel = new JPanel();
            panel.setToolTipText(numeratorTooltipText);
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.weightx = .5f;
            c.weighty = .5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(panel, c);
            if (elementsList.contains(Elements.NUM_TRUE_POS)) {
                panel.setBackground(ColorScheme.TRUE_POS_COLOR);
            }
        }      
        {
            final JPanel panel = new JPanel();
            panel.setToolTipText(numeratorTooltipText);
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 1;
            c.weightx = .5f;
            c.weighty = .5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(panel, c);
            if (elementsList.contains(Elements.NUM_FALSE_NEG)) {
                panel.setBackground(ColorScheme.FALSE_NEG_COLOR);
            }
         }  
         {
            final JPanel panel = new JPanel();
            panel.setToolTipText(numeratorTooltipText);
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 3;
            c.weightx = .5f;
            c.weighty = .5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(panel, c);
            if (elementsList.contains(Elements.NUM_FALSE_POS)) {
                panel.setBackground(ColorScheme.FALSE_POS_COLOR);
            }            
        }          
        {
            final JPanel panel = new JPanel();
            panel.setToolTipText(numeratorTooltipText);
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 3;
            c.weightx = .5f;
            c.weighty = .5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(panel, c);
            if (elementsList.contains(Elements.NUM_TRUE_NEG)) {
                panel.setBackground(ColorScheme.TRUE_NEG_COLOR);
            }            
        }          
        
        // Denominator
        {
            final JPanel panel = new JPanel();
            panel.setToolTipText(denominatorTooltipText);
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 5;
            c.weightx = .5f;
            c.weighty = .5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(panel, c);
            if (elementsList.contains(Elements.DENOM_TRUE_POS)) {
                panel.setBackground(ColorScheme.TRUE_POS_COLOR.darker().darker());
            }
        }      
        {
            final JPanel panel = new JPanel();
            panel.setToolTipText(denominatorTooltipText);
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 5;
            c.weightx = .5f;
            c.weighty = .5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(panel, c);
            if (elementsList.contains(Elements.DENOM_FALSE_NEG)) {
                panel.setBackground(ColorScheme.FALSE_NEG_COLOR.darker().darker());
            }
         }  
         {
            final JPanel panel = new JPanel();
            panel.setToolTipText(denominatorTooltipText);
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 7;
            c.weightx = .5f;
            c.weighty = .5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(panel, c);
            if (elementsList.contains(Elements.DENOM_FALSE_POS)) {
                panel.setBackground(ColorScheme.FALSE_POS_COLOR.darker().darker());
            }            
        }          
        {
            final JPanel panel = new JPanel();
            panel.setToolTipText(denominatorTooltipText);
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 3;
            c.gridy = 7;
            c.weightx = .5f;
            c.weighty = .5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(panel, c);
            if (elementsList.contains(Elements.DENOM_TRUE_NEG)) {
                panel.setBackground(ColorScheme.TRUE_NEG_COLOR.darker().darker());
            }            
        }               
        
        // Make a pretty grid
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 1;   
            c.gridheight = 3;
            c.weighty = 1f;
            c.fill = GridBagConstraints.VERTICAL;
            m_panel.add(new JSeparator(JSeparator.VERTICAL), c);
        }         
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 2;   
            c.gridwidth = 3;
            c.weightx = 1f;
            c.fill = GridBagConstraints.HORIZONTAL;
            m_panel.add(new JSeparator(JSeparator.HORIZONTAL), c);
        } 
        
        // Center Divider
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 4;   
            c.gridwidth = 3;
            c.weightx = 1f;
            c.weighty = .1f;
            c.fill = GridBagConstraints.HORIZONTAL;
            final JPanel panel = new JPanel();
            panel.setBackground(panel.getForeground());
            m_panel.add(panel, c);
        } 
        
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 2;
            c.gridy = 5;   
            c.gridheight = 3;
            c.weighty = 1f;
            c.fill = GridBagConstraints.VERTICAL;
            m_panel.add(new JSeparator(JSeparator.VERTICAL), c);
        }           
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 6;   
            c.gridwidth = 3;
            c.weightx = 1f;
            c.fill = GridBagConstraints.HORIZONTAL;
            m_panel.add(new JSeparator(JSeparator.HORIZONTAL), c);
        }              
    }
    
    /**
     * Get JPanel
     * @return JPanel
     */
    public JPanel getPanel() {
        return m_panel;
    }        
    
}
