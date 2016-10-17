/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.traccar.model;

/**
 *
 * @author Robert
 */
public class SOSNumberPriority {
    private int priority = -1;
    private String sosNumber = null;
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getPriority() {
        return priority;
    }
    public void setSOSNumber(String sosNumber) {
        this.sosNumber = sosNumber;
    }
    public String getSOSNumber() {
        return sosNumber;
    }
}
