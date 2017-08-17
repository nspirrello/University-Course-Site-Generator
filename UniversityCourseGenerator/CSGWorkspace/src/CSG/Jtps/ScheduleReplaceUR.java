/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Jtps;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Workspace;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;
import scheduleData.Schedule;

/**
 *
 * @author Nick
 */
public class ScheduleReplaceUR implements jTPS_Transaction{
    private String type;
    private String date;
    private String time;
    private String title;
    private String topic;
    private String link;
    private String criteria;
    private String newType;
    private String newDate;
    private String newTime;
    private String newTitle;
    private String newTopic;
    private String newLink;
    private String newCriteria;
    private CSGApp app;
    private CSGData data;
    public ScheduleReplaceUR(CSGApp app){
        this.app = app;
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        data = (CSGData)app.getDataComponent();
        newType = workspace.getSchedulePane().getTypeField().getText();
        newDate = workspace.getSchedulePane().getDateField().getText();
        newTime = workspace.getSchedulePane().getTimeField().getText();
        newTitle = workspace.getSchedulePane().getTitleField().getText();
        newTopic = workspace.getSchedulePane().getTopicField().getText();
        newLink = workspace.getSchedulePane().getLinkField().getText();
        newCriteria = workspace.getSchedulePane().getCriteriaField().getText();
        TableView schedule = workspace.getSchedulePane().getSchedule();
        Schedule s = (Schedule)schedule.getSelectionModel().getSelectedItem();
        type = s.getType();
        date = s.getDate();
        time = s.getTime();
        title = s.getTitle();
        topic = s.getTopic();
        link = s.getLink();
        criteria = s.getCriteria();
    }
    @Override
    public void doTransaction() {
        data.getScheduleData().removeSchedule(date, title);
        data.getScheduleData().addSchedule(newType, newDate, newTime, newTitle, newTopic, newLink, newCriteria);
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView schedule = workspace.getSchedulePane().getSchedule();
        schedule.getSelectionModel().select(data.getScheduleData().findSchedule(newDate, newTitle));
    }

    @Override
    public void undoTransaction() {
        data.getScheduleData().removeSchedule(newDate, newTitle);
        data.getScheduleData().addSchedule(type, date, time, title, topic, link, criteria);
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView schedule = workspace.getSchedulePane().getSchedule();
        schedule.getSelectionModel().select(data.getScheduleData().findSchedule(date, title));
    }
}
