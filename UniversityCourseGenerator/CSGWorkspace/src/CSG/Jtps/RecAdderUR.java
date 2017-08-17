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
public class RecAdderUR implements jTPS_Transaction{
    private String section;
    private String instructor;
    private String dayTime;
    private String location;
    private String taOne;
    private String taTwo;
    private CSGApp app;
    private Workspace workspace;
    public RecAdderUR(CSGApp app){
        this.app = app;
        workspace = (Workspace)app.getWorkspaceComponent();
        section = workspace.getRecitationPane().getSectionField().getText();
        instructor = workspace.getRecitationPane().getInstructorField().getText();
        dayTime = workspace.getRecitationPane().getDayTimeField().getText();
        location = workspace.getRecitationPane().getLocationField().getText();
        taOne = (String)workspace.getRecitationPane().getTa1().getSelectionModel().getSelectedItem().toString();
        taTwo = (String)workspace.getRecitationPane().getTa2().getSelectionModel().getSelectedItem().toString();
    }
    @Override
    public void doTransaction() {
        ((CSGData)app.getDataComponent()).getRecitationData().addRecitation(section, instructor, dayTime, location, taOne, taTwo);
    }

    @Override
    public void undoTransaction() {
        ((CSGData)app.getDataComponent()).getRecitationData().removeRecitation(section);
    }
    
}
