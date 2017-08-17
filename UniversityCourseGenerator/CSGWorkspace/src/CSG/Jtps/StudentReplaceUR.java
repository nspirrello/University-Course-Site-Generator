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
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;
import projectData.Student;
import recitationData.Recitation;

/**
 *
 * @author Nick
 */
public class StudentReplaceUR implements jTPS_Transaction{
    private String firstName;
    private String lastName;
    private String team;
    private String role;
    private String newFirst;
    private String newLast;
    private String newTeam;
    private String newRole;
    private CSGApp app;
    private CSGData data;
    
    public StudentReplaceUR(CSGApp app){
        this.app = app;
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        data = (CSGData)app.getDataComponent();
        newFirst = workspace.getProjectPane().getFirstNameField().getText();
        newLast = workspace.getProjectPane().getLastNameField().getText();
        team = (String) workspace.getProjectPane().getTeamBox().getSelectionModel().getSelectedItem();
        TableView studentTable = workspace.getProjectPane().getStudentTable();
        Student s = (Student)studentTable.getSelectionModel().getSelectedItem();
        firstName = s.getFirstName();
        lastName = s.getLastName();
        team = s.getTeam();
        role = s.getRole();
        
    }
    @Override
    public void doTransaction() {
        data.getProjectData().removeStudent(firstName, lastName);
        data.getProjectData().addStudent(newFirst, newLast, newTeam, newRole);
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView studentTable = workspace.getProjectPane().getStudentTable();
        studentTable.getSelectionModel().select(data.getProjectData().findStudent(newFirst, newLast));
    }

    @Override
    public void undoTransaction() {
       data.getProjectData().removeStudent(newFirst, newLast);
       data.getProjectData().addStudent(firstName, lastName, team, role);
       Workspace workspace = (Workspace)app.getWorkspaceComponent();
       TableView studentTable = workspace.getProjectPane().getStudentTable();
       studentTable.getSelectionModel().select(data.getProjectData().findStudent(firstName, lastName));
       
    }
    
}
