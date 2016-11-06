/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.metrics;

import org.harmonograph.confusion.metrics.compound.MetricsPanelMattCorrCoef;
import org.harmonograph.confusion.metrics.compound.MetricsPanelNegativeLikelihoodRatio;
import org.harmonograph.confusion.metrics.compound.MetricsPanelPositiveLikelihoodRatio;
import org.harmonograph.confusion.metrics.compound.MetricsPanelDiagnosticOddsRatio;
import org.harmonograph.confusion.metrics.compound.MetricsPanelF1Score;
import org.harmonograph.confusion.metrics.simple.MetricsPanelPrevalence;
import org.harmonograph.confusion.metrics.simple.MetricsPanelFalseDiscoveryRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelTruePositiveRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelPositivePredictiveValue;
import org.harmonograph.confusion.metrics.simple.MetricsPanelAccuracy;
import org.harmonograph.confusion.metrics.simple.MetricsPanelFalsePositiveRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelFalseNegativeRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelFalseOmissionRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelTrueNegativeRate;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.harmonograph.confusion.messages.TestResultsDistributor;
import org.harmonograph.confusion.metrics.complex.MetricsPanelLiftChart;
import org.harmonograph.confusion.metrics.complex.MetricsPanelPrecisionRecallCurve;
import org.harmonograph.confusion.metrics.complex.MetricsPanelRocCurve;
import org.harmonograph.confusion.metrics.compound.MetricsPanelBalancedAccuracy;
import org.harmonograph.confusion.metrics.compound.MetricsPanelBalancedErrorRate;
import org.harmonograph.confusion.metrics.compound.MetricsPanelBayes;
import org.harmonograph.confusion.metrics.compound.MetricsPanelInformedness;
import org.harmonograph.confusion.metrics.compound.MetricsPanelMarkedness;
import org.harmonograph.confusion.metrics.compound.MetricsPanelYoudendsJ;
import org.harmonograph.confusion.metrics.simple.MetricsPanelErrorRate;
import org.harmonograph.confusion.metrics.simple.MetricsPanelNegativePredictiveValue;

/**
 * The Metrics panel contains a collection of tabs,
 * where each tap represents some common concept 
 * such as sensitivity or specificity.
 * @author Dave
 */
public class MetricsMainPanel {
   
    /** Main Panel for Metrics Tabs. */
    protected final JPanel m_panel;    
    
    /** Tabbed Pane. */
    protected final JTabbedPane m_tabbedPane;
    
    /** List of subpanels. */
    protected final List<MetricsPanel> m_subPanels;
    
    /** Simple Constructor. */
    public MetricsMainPanel() {
        
        m_subPanels = new ArrayList<>();
        
        m_subPanels.add(new MetricsPanelPrevalence());
        
        m_subPanels.add(new MetricsPanelPair(
            new MetricsPanelAccuracy(), 
            new MetricsPanelErrorRate()));

        m_subPanels.add(new MetricsPanelPair(
                new MetricsPanelPositivePredictiveValue(),
                new MetricsPanelTruePositiveRate(),
                "<html>Precision<br>Recall</html>"));
        
        m_subPanels.add(new MetricsPanelPair(
                new MetricsPanelTruePositiveRate(),
                new MetricsPanelTrueNegativeRate(),
                "<html>Sensitivity<br>Specificity</html>"));

        m_subPanels.add(new MetricsPanelPair(
            new MetricsPanelPositivePredictiveValue(), 
            new MetricsPanelNegativePredictiveValue()));        
        
        m_subPanels.add(new MetricsPanelPair(
                new MetricsPanelFalsePositiveRate(),
                new MetricsPanelFalseNegativeRate()));
        
        m_subPanels.add(new MetricsPanelPair(
                new MetricsPanelFalseDiscoveryRate(),
                new MetricsPanelFalseOmissionRate()));
              
        m_subPanels.add(new MetricsPanelPair(
            new MetricsPanelPositiveLikelihoodRatio(),
            new MetricsPanelNegativeLikelihoodRatio()));    
        
        m_subPanels.add(new MetricsPanelPair(
            new MetricsPanelBalancedAccuracy(),
            new MetricsPanelBalancedErrorRate()));
        
        m_subPanels.add(new MetricsPanelPair(
            new MetricsPanelInformedness(),
            new MetricsPanelMarkedness()));
        
        m_subPanels.add(new MetricsPanelDiagnosticOddsRatio());
        m_subPanels.add(new MetricsPanelF1Score());
        m_subPanels.add(new MetricsPanelMattCorrCoef());
        m_subPanels.add(new MetricsPanelYoudendsJ());
       
        m_subPanels.add(new MetricsPanelRocCurve());
        m_subPanels.add(new MetricsPanelPrecisionRecallCurve());
        m_subPanels.add(new MetricsPanelLiftChart());
        m_subPanels.add(new MetricsPanelBayes());
        
        // Add any new panels here
        
        m_tabbedPane = new JTabbedPane();
        for (final MetricsPanel p : m_subPanels) {
            m_tabbedPane.add(p.getName(), p.getPanel());
            TestResultsDistributor.DISTRIBUTOR.addListener(p);
        }
        
        m_panel = new JPanel();
        m_panel.setLayout(new GridBagLayout());
        {
            final GridBagConstraints c = new GridBagConstraints();
            c.gridx = 1;
            c.gridy = 1;
            c.gridheight = 2;
            c.weightx = 0.5f;
            c.weighty = 0.5f;
            c.fill = GridBagConstraints.BOTH;
            m_panel.add(m_tabbedPane, c);        
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
