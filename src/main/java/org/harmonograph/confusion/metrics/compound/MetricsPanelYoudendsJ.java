/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics.compound;

import org.harmonograph.confusion.messages.TestResults;
import org.harmonograph.confusion.metrics.AbstractMetricsPanel;
import org.harmonograph.confusion.metrics.simple.MetricsPanelTrueNegativeRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelTruePositiveRate;

/**
 * MetricsPanel for Youden's J statistic formula.
 * @author Dave
 */
public class MetricsPanelYoudendsJ extends AbstractMetricsPanel {
      
    /** Simple Constructor. */
    public MetricsPanelYoudendsJ() {
        super("Youden's J Statistic");
    }
    
    /**
     * Calculate Youden's J statistic.
     * @param results Test Results
     * @return Youden's J statistic
     */
    protected static float getYoudendsJ(final TestResults results) {
       final float youdendsJ = 
               MetricsPanelTruePositiveRate.getTPR(results) +
               MetricsPanelTrueNegativeRate.getTNR(results) - 1;
       
       return youdendsJ;
    }
    
    /** {@inheritDoc} */
    @Override 
    public float updateGas(final TestResults results) {
        return getYoudendsJ(results);
    }
    
    /** {@inheritDoc} */
    @Override    
    public String updateLabelString(final TestResults results) {
        
        final StringBuilder out = new StringBuilder();
        out.append("<html>");
        out.append("Youden's J statistic, aka. Youden's index, J<p>");
        out.append("J = Sensitivity + Specificity - 1<p>");
        
        out.append(String.format("%.3f = %.3f + %.3f - 1<p>",
               getYoudendsJ(results), 
               MetricsPanelTruePositiveRate.getTPR(results),
               MetricsPanelTrueNegativeRate.getTNR(results)));
        
        out.append("</html>");
        return out.toString();        
    }
    
}
