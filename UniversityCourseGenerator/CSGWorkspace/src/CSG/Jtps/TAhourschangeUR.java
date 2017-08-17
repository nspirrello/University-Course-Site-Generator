/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Jtps;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Workspace;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;
import properties_manager.PropertiesManager;
import taData.TimeSlot;

/**
 *
 * @author zhaotingyi
 */
public class TAhourschangeUR implements jTPS_Transaction{
    
    private CSGApp app;
    private int startTime;
    private int endTime;
    private int newStartTime;
    private int newEndTime;
    private ArrayList<TimeSlot> officeHours;
    
    public TAhourschangeUR(CSGApp app){
        this.app = app;
        CSGData data = (CSGData)app.getDataComponent();
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ComboBox comboBox1 = workspace.getTaDataPane().getOfficeHour(true);
        ComboBox comboBox2 = workspace.getTaDataPane().getOfficeHour(false);
        startTime = data.getTaData().getStartHour();
        endTime = data.getTaData().getEndHour();
        newStartTime = comboBox1.getSelectionModel().getSelectedIndex();
        newEndTime = comboBox2.getSelectionModel().getSelectedIndex();
        officeHours = TimeSlot.buildOfficeHoursList(data.getTaData());
    }

    @Override
    public void doTransaction() {
        ((Workspace)app.getWorkspaceComponent()).getTaDataPane().getOfficeHoursGridPane().getChildren().clear();
        ((CSGData)app.getDataComponent()).getTaData().changeTime(newStartTime, newEndTime, officeHours);
    }

    @Override
    public void undoTransaction() {
        ((Workspace)app.getWorkspaceComponent()).getTaDataPane().getOfficeHoursGridPane().getChildren().clear();
        ((CSGData)app.getDataComponent()).getTaData().changeTime(startTime, endTime, officeHours);
    }
    
}
