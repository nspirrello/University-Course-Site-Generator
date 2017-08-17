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
public class StudentDeleteUR implements jTPS_Transaction{
    private CSGApp app;
    private CSGData data;
    private String firstName;
    private String lastName;
    private String team;
    private String role;
    
    public StudentDeleteUR(CSGApp app, String firstName, String lastName){
        this.app = app;
        data = (CSGData)app.getDataComponent();
        this.firstName = firstName;
        this.lastName = lastName;
        team = data.getProjectData().findStudent(firstName, lastName).getTeam();
        role = data.getProjectData().findStudent(firstName, lastName).getRole();
    }
    
    @Override
    public void doTransaction() {
        data.getProjectData().removeStudent(firstName, lastName);
    }

    @Override
    public void undoTransaction() {
        data.getProjectData().addStudent(firstName, lastName, team, role);
    }
    
}
