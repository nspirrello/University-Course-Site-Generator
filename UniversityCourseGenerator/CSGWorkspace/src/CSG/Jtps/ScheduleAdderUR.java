/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Jtps;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Workspace;
import jtps.jTPS_Transaction;

/**
 *
 * @author Nick
 */
public class ScheduleAdderUR implements jTPS_Transaction{
    private String type;
    private String date;
    private String time;
    private String title;
    private String topic;
    private String link;
    private String criteria;
    private CSGApp app;
    private Workspace workspace;
    public ScheduleAdderUR(CSGApp app){
        this.app = app;
        workspace = (Workspace)app.getWorkspaceComponent();
        type = workspace.getSchedulePane().getTimeField().getText();
        date = workspace.getSchedulePane().getDateField().getText();
        time = workspace.getSchedulePane().getTimeField().getText();
        title = workspace.getSchedulePane().getTitleField().getText();
        topic = workspace.getSchedulePane().getTopicField().getText();
        link = workspace.getSchedulePane().getLinkField().getText();
        criteria = workspace.getSchedulePane().getCriteriaField().getText();
    }
    @Override
    public void doTransaction() {
        ((CSGData)app.getDataComponent()).getScheduleData().addSchedule(type, date, time, title, topic, link, criteria);
    }

    @Override
    public void undoTransaction() {
        ((CSGData)app.getDataComponent()).getScheduleData().removeSchedule(date, title);
    }
    
}
