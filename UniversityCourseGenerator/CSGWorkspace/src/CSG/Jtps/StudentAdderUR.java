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
public class StudentAdderUR implements jTPS_Transaction{
    private String firstName;
    private String lastName;
    private String team;
    private String role;
    private CSGApp app;
    private Workspace workspace;
    public StudentAdderUR(CSGApp app){
        this.app = app;
        workspace = (Workspace)app.getWorkspaceComponent();
        firstName = workspace.getProjectPane().getFirstNameField().getText();
        lastName = workspace.getProjectPane().getLastNameField().getText();
        team = (String) workspace.getProjectPane().getTeamBox().getSelectionModel().getSelectedItem();
        role = workspace.getProjectPane().getRoleField().getText();
    }
    @Override
    public void doTransaction() {
        ((CSGData)app.getDataComponent()).getProjectData().addStudent(firstName, lastName, team, role);
    }

    @Override
    public void undoTransaction() {
        ((CSGData)app.getDataComponent()).getProjectData().removeStudent(firstName, lastName);
    }
    
}
