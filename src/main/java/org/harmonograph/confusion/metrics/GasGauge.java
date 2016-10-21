/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Simple Gas Gauge display.
 * @author Dave
 */
public class GasGauge extends JPanel {
    
    /** Current level, range 0..1. */
    protected float m_level = 0f;
    
    /** Color of fill of gauge. */
    protected final Color m_color;
    
    /**
     * Simple Constructor
     * @param color Foreground color
     */
    public GasGauge(final Color color) {
        super();
        m_color = color;
        this.setMinimumSize(new Dimension(20, 20));
    }
    
    /**
     * Set level, normalized from 0..1.
     * @param level Level 
     */
    public void setLevel(final float level) {
        m_level = level;
        repaint();
    }
    
    /** {@inheritDoc} */
    @Override      
    public void paint(final Graphics g) {
        super.paint(g);
        final Graphics2D g2d = (Graphics2D) g; 
        final int height = (int)((float)getHeight() * m_level);
        g2d.setColor(m_color);
        g2d.fillRect(0, getHeight() - height, getWidth(), height);
    }
    
}
