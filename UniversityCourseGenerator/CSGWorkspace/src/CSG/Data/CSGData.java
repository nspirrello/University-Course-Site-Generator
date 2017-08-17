/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.Data;

import CSG.CSGApp;
import CSG.Workspace.Workspace;
import courseDetails.CourseDetailPane;
import courseDetails.CourseDetailsData;
import courseDetails.SitePages;
import djf.components.AppDataComponent;
import projectData.ProjectData;
import recitationData.RecitationData;
import scheduleData.ScheduleData;
import taData.TAData;

/**
 *
 * @author Nick
 */
public class CSGData implements AppDataComponent{
    TAData taData;
    CSGApp app;
    CourseDetailsData courseData;
    RecitationData recitationData;
    ProjectData projectData;
    ScheduleData scheduleData;
    boolean hasChanged = false;

    public CSGData(CSGApp app) {
        this.app = app;
        taData = new TAData(app);
        courseData = new CourseDetailsData();
        recitationData = new RecitationData(app);
        projectData = new ProjectData();
        scheduleData = new ScheduleData();
    }
    @Override
    public void resetData() {
        taData.setEndHour(taData.getMaxHour());
        taData.setStartHour(taData.getMinHour());
        taData.getTeachingAssistants().clear();
        taData.getOfficeHours().clear();
        
        Workspace workspaceComponent = (Workspace)app.getWorkspaceComponent();
        
        workspaceComponent.getTaDataPane().getOfficeHour(true).getSelectionModel().select(null);
        workspaceComponent.getTaDataPane().getOfficeHour(true).getSelectionModel().select(taData.getStartHour());
        workspaceComponent.getTaDataPane().getOfficeHour(false).getSelectionModel().select(null);
        workspaceComponent.getTaDataPane().getOfficeHour(false).getSelectionModel().select(taData.getEndHour());
    }
    public void changeCon(boolean change){
        hasChanged = true;
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        workspace.getCourseDetailPane().setCourseInfo();
    }
    public CourseDetailsData getCourseData() {
        return courseData;
    }

    public RecitationData getRecitationData() {
        return recitationData;
    }

    public ProjectData getProjectData() {
        return projectData;
    }

    public ScheduleData getScheduleData() {
        return scheduleData;
    }
    
    public TAData getTaData() {
        return taData;
    }
    
}
