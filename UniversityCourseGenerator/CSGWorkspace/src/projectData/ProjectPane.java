/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectData;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Workspace;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import properties_manager.PropertiesManager;

/**
 *
 * @author Nick
 */
public class ProjectPane extends Pane{
    Label projects,teams,addEdit,name,color,textColor,link,students,addEditStudent,firstName,lastName,team,role;
    Button removeTeam,addUpdateTeam,clearTeam,removeStudent,addUpdateStudents,clearStudents;
    TableView teamTable,studentTable;
    TableColumn<Team, String> nameCol,colorCol,textColorCol,linkCol;
    TableColumn<Student, String> firstNameCol,lastNameCol,teamCol,roleCol;
    ColorPicker colorPick,textColorPick;
    ComboBox teamBox;
    TextField nameField,linkField,firstNameField,lastNameField,roleField;
    VBox contentHolder,studentsHolder,teamsHolder;
    HBox teamHeader,teamButtons,teamName,teamColor,teamLink;
    HBox studentHeader, studentButtons,first,last,teamHolder,roleHolder;
    VBox content;
    
    ProjectData Data;
    private final ScrollPane scroll;
    
    public void clearFields(){
        colorPick = new ColorPicker();
        textColorPick = new ColorPicker();
        teamBox.getSelectionModel().clearSelection();
        nameField.clear();
        linkField.clear();
        firstNameField.clear();
        lastNameField.clear();
        roleField.clear();
    }

    public VBox getContentHolder() {
        return contentHolder;
    }

    public VBox getStudentsHolder() {
        return studentsHolder;
    }

    public VBox getTeamsHolder() {
        return teamsHolder;
    }

    public ColorPicker getColorPick() {
        return colorPick;
    }

    public void setColorPick(ColorPicker colorPick) {
        this.colorPick = colorPick;
    }

    public ColorPicker getTextColorPick() {
        return textColorPick;
    }

    public void setTextColorPick(ColorPicker textColorPick) {
        this.textColorPick = textColorPick;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public TextField getLinkField() {
        return linkField;
    }

    public void setLinkField(TextField linkField) {
        this.linkField = linkField;
    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public void setFirstNameField(TextField firstNameField) {
        this.firstNameField = firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(TextField lastNameField) {
        this.lastNameField = lastNameField;
    }

    public TextField getRoleField() {
        return roleField;
    }

    public void setRoleField(TextField roleField) {
        this.roleField = roleField;
    }

    public ComboBox getTeamBox() {
        return teamBox;
    }
    
    public TableView getTeamTable() {
        return teamTable;
    }

    public TableView getStudentTable() {
        return studentTable;
    }
    
    public void setTeamBox(ComboBox teamBox) {
        this.teamBox = teamBox;
    }
    
    public HBox getTeamHeader() {
        return teamHeader;
    }

    public HBox getTeamButtons() {
        return teamButtons;
    }

    public HBox getTeamName() {
        return teamName;
    }

    public HBox getTeamColor() {
        return teamColor;
    }

    public HBox getTeamLink() {
        return teamLink;
    }

    public HBox getStudentHeader() {
        return studentHeader;
    }

    public HBox getStudentButtons() {
        return studentButtons;
    }

    public HBox getFirst() {
        return first;
    }

    public HBox getLast() {
        return last;
    }

    public HBox getTeamHolder() {
        return teamHolder;
    }

    public HBox getRoleHolder() {
        return roleHolder;
    }

    public Label getProjects() {
        return projects;
    }
    CSGApp app;
    public ProjectPane(CSGApp app){
        this.app = app;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        teamTable = new TableView();
        nameCol = new TableColumn("Name");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("name")
        );
        colorCol = new TableColumn("Color(hex#)");
        colorCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("colorHex")
        );
        textColorCol = new TableColumn("Text Color (hex#)");
        textColorCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("textColorHex")
        );
        linkCol = new TableColumn("Link");
        linkCol.setCellValueFactory(
                new PropertyValueFactory<Team, String>("link")
        );
        teamTable.getColumns().addAll(nameCol,colorCol,textColorCol,linkCol);
        CSGData data = (CSGData)app.getDataComponent();
        teamTable.setItems(data.getProjectData().getTeam());
        studentTable = new TableView();
        firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("firstName")
        );
        lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("lastName")
        );
        teamCol = new TableColumn("Team");
        teamCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("team")
        );
        roleCol = new TableColumn("Role");
        roleCol.setCellValueFactory(
                new PropertyValueFactory<Student, String>("role")
        );
        studentTable.getColumns().addAll(firstNameCol,lastNameCol,teamCol,roleCol);
        studentTable.setItems(data.getProjectData().getStudent());
        colorPick = new ColorPicker();
        textColorPick = new ColorPicker();
        teamBox = new ComboBox();
        teamBox.setItems(data.getProjectData().getTeamNames());
        nameField = new TextField();
        linkField = new TextField();
        firstNameField = new TextField();
        lastNameField = new TextField();
        roleField = new TextField();
        
        String projectsText = props.getProperty(CSG.CSGProp.PROJECTS_HEADER.toString());
        projects = new Label(projectsText);
        String teamsText = props.getProperty(CSG.CSGProp.TEAMS_TEXT.toString());
        teams = new Label(teamsText);
        String addEditText = props.getProperty(CSG.CSGProp.ADD_EDIT_TEAM_TEXT.toString());
        addEdit = new Label(addEditText);
        String nameText = props.getProperty(CSG.CSGProp.NAME_TEAM_TEXT.toString());
        name = new Label(nameText);
        String colorText = props.getProperty(CSG.CSGProp.COLOR_TEAM_TEXT.toString());
        color = new Label(colorText);
        String textColorText = props.getProperty(CSG.CSGProp.TEXT_COLOR_TEXT.toString());
        textColor = new Label(textColorText);
        String linkText = props.getProperty(CSG.CSGProp.LINK_TEAM_TEXT.toString());
        link = new Label(linkText);
        String studentsText = props.getProperty(CSG.CSGProp.STUDENTS_TEXT.toString());
        students = new Label(studentsText);
        String addEditStudentsText = props.getProperty(CSG.CSGProp.ADD_EDIT_TEAM_TEXT.toString());
        addEditStudent = new Label(addEditStudentsText);
        String firstNameText = props.getProperty(CSG.CSGProp.FIRST_NAME_STUDENTS_TEXT.toString());
        firstName = new Label(firstNameText);
        String lastNameText = props.getProperty(CSG.CSGProp.LAST_NAME_STUDENTS_TEXT.toString());
        lastName = new Label(lastNameText);
        String teamText = props.getProperty(CSG.CSGProp.TEAM_STUDENT_TEXT.toString());
        team = new Label(teamText);
        String roleText = props.getProperty(CSG.CSGProp.ROLE_STUDENTS.toString());
        role = new Label(roleText);
        String addUpdateStudentText = props.getProperty(CSG.CSGProp.ADD_UPDATE_STUDENTS_BUTTON.toString());
        addUpdateStudents = new Button(addUpdateStudentText);
        String clearStudentText = props.getProperty(CSG.CSGProp.CLEAR_STUDENTS_BUTTON.toString());
        clearStudents = new Button(clearStudentText);
        String removeStudentText = props.getProperty(CSG.CSGProp.REMOVE_STUDENTS_BUTTON.toString());
        removeStudent = new Button(removeStudentText);
        String clearTeamText = props.getProperty(CSG.CSGProp.CLEAR_TEAM_BUTTON.toString());
        clearTeam = new Button(clearTeamText);
        String addUpdateTeamText = props.getProperty(CSG.CSGProp.ADD_UPDATE_TEAM_BUTTON.toString());
        addUpdateTeam = new Button(addUpdateTeamText);
        String removeTeamText = props.getProperty(CSG.CSGProp.REMOVE_TEAM_BUTTON.toString());
        removeTeam = new Button(removeTeamText);
        
        
        
        teamHeader = new HBox(15);
        teamHeader.getChildren().addAll(teams,removeTeam);
        teamButtons = new HBox(15);
        teamButtons.getChildren().addAll(addUpdateTeam,clearTeam);
        teamName = new HBox(30);
        teamName.getChildren().addAll(name, nameField);
        teamColor = new HBox(25);
        teamColor.getChildren().addAll(color,colorPick,textColor,textColorPick);
        teamLink = new HBox(30);
        teamLink.getChildren().addAll(link,linkField);
        
        studentHeader = new HBox(15);
        studentHeader.getChildren().addAll(students,removeStudent);
        studentButtons = new HBox(15);
        studentButtons.getChildren().addAll(addUpdateStudents,clearStudents);
        first = new HBox(30);
        first.getChildren().addAll(firstName,firstNameField);
        last = new HBox(38);
        last.getChildren().addAll(lastName,lastNameField);
        teamHolder = new HBox(85);
        teamHolder.getChildren().addAll(team,teamBox);
        roleHolder = new HBox(85);
        roleHolder.getChildren().addAll(role,roleField);
        
        studentsHolder = new VBox(10);
        studentsHolder.getChildren().addAll(studentHeader,studentTable,addEditStudent,first,last,teamHolder,roleHolder,studentButtons);
        teamsHolder = new VBox(10);
        teamsHolder.getChildren().addAll(teamHeader,teamTable,addEdit,teamName,teamColor,teamLink,teamButtons);
        content = new VBox();
        scroll = new ScrollPane();
        contentHolder = new VBox(20);
        contentHolder.getChildren().addAll(projects,teamsHolder,studentsHolder);
        scroll.setContent(contentHolder);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setFitToWidth(true);
        content.getChildren().add(scroll);
        content.setAlignment(Pos.CENTER);
        
        colorPick.setOnAction(e -> {
            
        });
        textColorPick.setOnAction(e -> {
            System.out.println(textColorPick.getValue());
        });
        
        addUpdateStudents.setOnAction(e -> {
            Workspace work = (Workspace)app.getWorkspaceComponent();
            if(studentTable.getSelectionModel().getSelectedItem() == null)
                work.getController().handleAddStudent();
            else
                work.getController().handleUpdateStudent();
        });
        removeStudent.setOnAction(e -> {
            Workspace work = (Workspace)app.getWorkspaceComponent();
            work.getController().handleRemoveStudent();
        });
        
        clearStudents.setOnAction(e -> {
            studentTable.getSelectionModel().clearSelection();
            firstNameField.clear();
            lastNameField.clear();
            teamBox.getSelectionModel().clearSelection();
            roleField.clear();
        });
        
        addUpdateTeam.setOnAction(e -> {
            Workspace work = (Workspace)app.getWorkspaceComponent();
            if(teamTable.getSelectionModel().getSelectedItem() == null)
                work.getController().handleAddTeam();
            else
                work.getController().handleUpdateTeam();
        });
        
        removeTeam.setOnAction(e -> {
            Workspace work = (Workspace)app.getWorkspaceComponent();
            work.getController().handleRemoveTeam();
        });
        
        clearTeam.setOnAction(e -> {
            teamTable.getSelectionModel().clearSelection();
            nameField.clear();
            linkField.clear();
        });  
        
        studentTable.setOnMouseClicked(e -> {
            Student s = (Student)studentTable.getSelectionModel().getSelectedItem();
            if(s != null){
                firstNameField.setText(s.getFirstName());
                lastNameField.setText(s.getLastName());
                teamBox.getSelectionModel().select(s.getTeam());
                roleField.setText(s.getRole());
            }
        });
        
        teamTable.setOnMouseClicked(e -> {
            Team t = (Team)teamTable.getSelectionModel().getSelectedItem();
            if(t != null){
                nameField.setText(t.getName());
                Color color = Color.valueOf(t.getColorHex());
                colorPick.setValue(color);
                Color textColor = Color.valueOf(t.getTextColorHex());
                textColorPick.setValue(textColor);
                linkField.setText(t.getLink());
            }
        });
    }
    public VBox getContent(){
        return content;
    }
    
}
