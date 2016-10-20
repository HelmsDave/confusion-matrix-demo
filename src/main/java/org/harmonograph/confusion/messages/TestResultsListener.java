/*
 * (C) 2016 by Dave Helms (dave.helms.home@gmail.com)
 *  Released under the GPL 3.
 */
package org.harmonograph.confusion.messages;

/**
 * Simple listener interface for TestResults.
 * This is always called on AWT Event thread.
 * @author Dave
 */
public interface TestResultsListener {
    
    /** 
     * Process Test Results.
     * 
     * @param testResults Test Results to process 
     */
    void processTestResults(TestResults testResults);
    
}
