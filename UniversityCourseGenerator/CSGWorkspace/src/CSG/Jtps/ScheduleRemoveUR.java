/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Jtps;

import CSG.CSGApp;
import CSG.Data.CSGData;
import jtps.jTPS_Transaction;

/**
 *
 * @author Nick
 */
public class ScheduleRemoveUR implements jTPS_Transaction{

    private final CSGApp app;
    private final CSGData data;
    private String date;
    private String type;
    private String time;
    private String title;
    private String topic;
    private String link;
    private String criteria;
    public ScheduleRemoveUR(CSGApp app, String date, String title){
        this.app = app;
        data = (CSGData)app.getDataComponent();
        this.date = date;
        this.title = title;
        type = data.getScheduleData().findSchedule(date, title).getType();
        time = data.getScheduleData().findSchedule(date, title).getTime();
        topic = data.getScheduleData().findSchedule(date, title).getTopic();
        link = data.getScheduleData().findSchedule(date, title).getLink();
        criteria = data.getScheduleData().findSchedule(date, title).getCriteria();
    }
    @Override
    public void doTransaction() {
        data.getScheduleData().removeSchedule(date,title);
    }

    @Override
    public void undoTransaction() {
        data.getScheduleData().addSchedule(type, date, time, title, topic, link, criteria);
    }
    
}
