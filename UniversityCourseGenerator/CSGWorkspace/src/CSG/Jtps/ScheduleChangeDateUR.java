/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Jtps;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Workspace;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import javafx.scene.control.DatePicker;
import jtps.jTPS_Transaction;

/**
 *
 * @author Nick
 */
public class ScheduleChangeDateUR implements jTPS_Transaction{
    private CSGData data;
    private CSGApp app;
    private String startMon;
    private String endFri;
    private String newStartMon;
    private String newEndFri;
    public ScheduleChangeDateUR(CSGApp app){
        this.app = app;
        data = (CSGData)app.getDataComponent();
        startMon = data.getScheduleData().getStartMon();
        endFri = data.getScheduleData().getEndFri();
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        DatePicker start = workspace.getSchedulePane().getStarting();
        DatePicker end = workspace.getSchedulePane().getEnding();
        if(start.getValue().getDayOfWeek().equals(DayOfWeek.MONDAY) && end.getValue().getDayOfWeek().equals(DayOfWeek.FRIDAY)){
            newStartMon = start.getValue().toString();
            newEndFri = end.getValue().toString();
            data.getScheduleData().setDates(startMon, endFri);
        }   
    }
    @Override
    public void doTransaction() {
        ((CSGData)app.getDataComponent()).getScheduleData().changeDates(newStartMon,newEndFri);
    }

    @Override
    public void undoTransaction() {
        ((CSGData)app.getDataComponent()).getScheduleData().changeDates(startMon, endFri);
    }
    
}
