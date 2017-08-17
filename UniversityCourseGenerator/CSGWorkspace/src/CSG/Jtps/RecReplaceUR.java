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
import recitationData.Recitation;

/**
 *
 * @author Nick
 */
public class RecReplaceUR implements jTPS_Transaction{
    private String section;
    private String instructor;
    private String dayTime;
    private String location;
    private String taOne;
    private String taTwo;
    private String newSection;
    private String newInstructor;
    private String newDayTime;
    private String newLocation;
    private String newTaOne;
    private String newTaTwo;
    private CSGApp app;
    private CSGData data;
    public RecReplaceUR(CSGApp app){
        this.app = app;
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        data = (CSGData)app.getDataComponent();
        newSection = workspace.getRecitationPane().getSectionField().getText();
        newInstructor = workspace.getRecitationPane().getInstructorField().getText();
        newDayTime = workspace.getRecitationPane().getDayTimeField().getText();
        newLocation = workspace.getRecitationPane().getLocationField().getText();
        newTaOne = (String)workspace.getRecitationPane().getTa1().getSelectionModel().getSelectedItem().toString();
        newTaTwo = (String)workspace.getRecitationPane().getTa2().getSelectionModel().getSelectedItem().toString();
        TableView recitationTable = workspace.getRecitationPane().getRecitationTable();
        Recitation r = (Recitation)recitationTable.getSelectionModel().getSelectedItem();
        section = r.getSection();
        instructor = r.getInstructor();
        dayTime = r.getDayTime();
        location = r.getLocation();
        taOne = r.getTaOne();
        taTwo = r.getTaTwo();
    }  
    @Override
    public void doTransaction() {
        data.getRecitationData().removeRecitation(section);
        data.getRecitationData().addRecitation(newSection, newInstructor, newDayTime, newLocation,newTaOne,newTaTwo);
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView recitationTable = workspace.getRecitationPane().getRecitationTable();
        recitationTable.getSelectionModel().select(data.getRecitationData().find(newSection));
    }

    @Override
    public void undoTransaction() {
       data.getRecitationData().removeRecitation(newSection);
       data.getRecitationData().addRecitation(section, instructor, dayTime, location,taOne,taTwo);
       Workspace workspace = (Workspace)app.getWorkspaceComponent();
       TableView recitationTable = workspace.getRecitationPane().getRecitationTable();
       recitationTable.getSelectionModel().select(data.getRecitationData().find(section));
    }
    
}
