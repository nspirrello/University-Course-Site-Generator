/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Workspace;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.File.CSGFile;
import courseDetails.CourseDetailPane;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import projectData.ProjectPane;
import properties_manager.PropertiesManager;
import recitationData.RecitationPane;
import scheduleData.Schedule;
import scheduleData.SchedulePane;
import taData.TAData;
import taData.TaDataPane;

/**
 *
 * @author Nick
 */
public class Workspace extends AppWorkspaceComponent{
    TabPane tabs;
    CourseDetailPane courseDetailPane;
    TaDataPane taDataPane;
    RecitationPane recitationPane;
    SchedulePane schedulePane;
    ProjectPane projectPane;
    VBox window;
    Tab course;
    Tab ta;
    Tab recitation;
    Tab schedule;
    Tab project;
    CSGApp app;
    Controller controller;
    public Workspace(CSGApp initApp){
        window = new VBox();
        app = initApp;
        controller = new Controller(app);
        courseDetailPane = new CourseDetailPane(app);
        taDataPane = new TaDataPane(app);
        recitationPane = new RecitationPane(app);
        schedulePane = new SchedulePane(app);
        projectPane = new ProjectPane(app);
        
        tabs = new TabPane();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        String courseText = props.getProperty(CSG.CSGProp.COURSE);
        course = new Tab(courseText);
        course.setContent(courseDetailPane.getContent());
        String taText = props.getProperty(CSG.CSGProp.TA);
        ta = new Tab(taText);
        ta.setContent(taDataPane.getContent());
        String recitationText = props.getProperty(CSG.CSGProp.RECITATION);
        recitation = new Tab(recitationText);
        recitation.setContent(recitationPane.getContent());
        String scheduleText = props.getProperty(CSG.CSGProp.SCHEDULE);
        schedule = new Tab(scheduleText);
        schedule.setContent(schedulePane.getContent());
        String projectsText = props.getProperty(CSG.CSGProp.PROJECTS);
        project = new Tab(projectsText);
        project.setContent(projectPane.getContent());
        tabs.getTabs().addAll(course,ta,recitation,schedule,project);
        workspace = new BorderPane();
        window.getChildren().addAll(tabs);
        ((BorderPane) workspace).setCenter(window);
        course.setClosable(false);
        ta.setClosable(false);
        project.setClosable(false);
        schedule.setClosable(false);
        recitation.setClosable(false);
        
        app.getGUI().getRedoButton().setOnAction(e -> {
            controller.Redo();
        });
        app.getGUI().getUndoButton().setOnAction(eh -> {
            controller.Undo();
        });
    }
    
    public TabPane getTabPane(){
        return tabs;
    }
    public Tab getC(){
        return course;
    }
    public Tab getT(){
        return ta;
    }
    public Tab getR(){
        return recitation;
    }
    public Tab getP(){
        return project;
    }
    public Tab getS(){
        return schedule;
    }
    public VBox getWindow(){
        return window;
    }
    
    
    
    public TaDataPane getTaDataPane(){
        return taDataPane;
    }
    public RecitationPane getRecitationPane(){
        return recitationPane;
    }
    public CourseDetailPane getCourseDetailPane(){
        return courseDetailPane;
    }
    public SchedulePane getSchedulePane(){
        return schedulePane;
    }
    public ProjectPane getProjectPane(){
        return projectPane;
    }
    public Controller getController(){
        return controller;
    }
    @Override
    public void resetWorkspace() {
        courseDetailPane.clearFields();
        recitationPane.clearFields();
        projectPane.clearFields();
        schedulePane.clearFields();
//        taDataPane.clearFields();
    }
    
    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        CSGData taData = (CSGData) dataComponent;
        taDataPane.reloadOfficeHoursGrid(taData.getTaData());
    }
    
}
