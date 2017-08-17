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
import jtps.jTPS_Transaction;
import projectData.Student;

/**
 *
 * @author Nick
 */
public class TeamDeleteUR implements jTPS_Transaction{
    private CSGApp app;
    private CSGData data;
    private String name;
    private String color;
    private String textColor;
    private String link;
    private ArrayList<Student> students;

    public TeamDeleteUR(CSGApp app, String name){
        this.app = app;
        data = (CSGData)app.getDataComponent();
        this.name = name;
        color = data.getProjectData().findTeam(name).getColorHex();
        textColor = data.getProjectData().findTeam(name).getTextColorHex();
        link = data.getProjectData().findTeam(name).getLink();
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
        for(int i = 0;i < students.size();i++){
            students.get(i).setTeam("");
        }
        Workspace work = (Workspace)app.getWorkspaceComponent();
        work.getProjectPane().getStudentTable().refresh();
        
    }

    @Override
    public void undoTransaction() {
        data.getProjectData().addTeam(name, textColor, textColor, link);
        for(int i = 0;i < students.size();i++){
            students.get(i).setTeam(name);
        }
        Workspace work = (Workspace)app.getWorkspaceComponent();
        work.getProjectPane().getStudentTable().refresh();
    }
}
