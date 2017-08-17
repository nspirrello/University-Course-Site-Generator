/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectData;

import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nick
 */
public class ProjectData {
    ObservableList<Team> team;
    ObservableList<Student> student;
    ObservableList<String> teamNames;

    public ProjectData(){
        team = FXCollections.observableArrayList();
        student = FXCollections.observableArrayList();
        teamNames = FXCollections.observableArrayList();
        
    }
    public void addTeam(String teamName, String teamColor, String teamTextColor, String teamLink) {
        Team newTeam = new Team(teamName,teamColor,teamTextColor,teamLink);
        team.add(newTeam);
        teamNames.add(newTeam.getName());
    }
    
    public void addStudent(String firstName, String lastName, String teamName, String role) {
        Student newStudent = new Student(firstName,lastName,teamName,role);
        student.add(newStudent);
    }

    public ObservableList<Team> getTeam() {
        return team;
    }

    public ObservableList<Student> getStudent() {
        return student;
    }

    public ObservableList<String> getTeamNames() {
        return teamNames;
    }  
    
    public void removeStudent(String firstName, String lastName) {
        Student toRemove = findStudent(firstName, lastName);
        if(toRemove != null){
            student.remove(toRemove);
        }
    }
    public Student findStudent(String firstName, String lastName){
        for(int i = 0;i < student.size();i++){
            if(student.get(i).getFirstName().equals(firstName) && student.get(i).getLastName().equals(lastName)){
                return student.get(i);
            }
        }
        return null;
    }
    
    public Team findTeam(String name){
        for(int i = 0;i < team.size();i++){
            if(team.get(i).getName().equals(name)){
                return team.get(i);
            }
        }
        return null;
    }
    
    public void removeTeam(String name) {
        Team toRemove = findTeam(name);
        if(toRemove != null){
            team.remove(toRemove);
            teamNames.remove(toRemove.getName());
        }
    }
    
}
