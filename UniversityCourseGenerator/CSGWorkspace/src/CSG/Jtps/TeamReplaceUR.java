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
import projectData.Team;

/**
 *
 * @author Nick
 */
public class TeamReplaceUR implements jTPS_Transaction{
    private String name;
    private String color;
    private String textColor;
    private String link;
    private String newName;
    private String newColor;
    private String newTextColor;
    private String newLink;
    private CSGApp app;
    private CSGData data;
    private ArrayList<Student> students;
    
    public TeamReplaceUR(CSGApp app){
        this.app = app;
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        data = (CSGData)app.getDataComponent();
        newName = workspace.getProjectPane().getNameField().getText();
        newColor = workspace.getProjectPane().getColorPick().getValue().toString();
        newTextColor = workspace.getProjectPane().getTextColorPick().getValue().toString();
        newLink = workspace.getProjectPane().getLinkField().getText();
        TableView teamTable = workspace.getProjectPane().getTeamTable();
        Team t = (Team)teamTable.getSelectionModel().getSelectedItem();
        name = t.getName();
        color = t.getColorHex();
        textColor = t.getTextColorHex();
        link = t.getLink();
        ObservableList<Student> stud = data.getProjectData().getStudent();
        students = new ArrayList();
        for(int i = 0;i < stud.size();i++){
            if(stud.get(i).getTeam().equals(name)){
                students.add(stud.get(i));
            }
        }
    }

    @Override
    public void doTransaction() {
        data.getProjectData().removeTeam(name);
        data.getProjectData().addTeam(newName, newColor, newTextColor, newLink);
        for(int i = 0;i < students.size();i++){
            students.get(i).setTeam(newName);
        }
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView teamTable = workspace.getProjectPane().getTeamTable();
        teamTable.getSelectionModel().select(data.getProjectData().findTeam(newName));
        workspace.getProjectPane().getStudentTable().refresh();
    }

    @Override
    public void undoTransaction() {
        data.getProjectData().removeTeam(newName);
        data.getProjectData().addStudent(name, color, textColor, link);
        data.getProjectData().addTeam(name, textColor, textColor, link);
        for(int i = 0;i < students.size();i++){
            students.get(i).setTeam(name);
        }
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        TableView teamTable = workspace.getProjectPane().getTeamTable();
        teamTable.getSelectionModel().select(data.getProjectData().findTeam(name));
        workspace.getProjectPane().getStudentTable().refresh();
    }
    
}
