/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.File;

import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.Workspace.Workspace;
import com.google.gson.Gson;
import courseDetails.CourseDetailPane;
import courseDetails.CourseDetails;
import courseDetails.CourseDetailsData;
import courseDetails.SitePages;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import projectData.Student;
import projectData.Team;
import recitationData.Recitation;
import scheduleData.Schedule;
import taData.TAData;
import taData.TeachingAssistant;
import taData.TimeSlot;

/**
 *
 * @author Nick
 */
public class CSGFile implements AppFileComponent{
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_DAY = "day";
    static final String JSON_TIME = "time";
    static final String JSON_NAME = "taName";
    static final String JSON_TA_UNDERGRAD = "taUndergrad";
    static final String JSON_TAS = "tas";
    static final String JSON_EMAIL = "email";
    static final String JSON_TA_DATA = "taData";
    
    static final String JSON_TEAM = "team";
    static final String JSON_TEAM_NAME = "name";
    static final String JSON_TEAM_COLOR = "color";
    static final String JSON_TEAM_TEXT_COLOR = "textColor";
    static final String JSON_TEAM_LINK = "link";
    static final String JSON_STUDENT = "student";
    static final String JSON_STUDENT_FIRST_NAME = "firstName";
    static final String JSON_STUDENT_LAST_NAME = "lastName";
    static final String JSON_STUDENT_TEAM_NAME = "teamName";
    static final String JSON_ROLE = "role";
    static final String JSON_PROJECTS = "projects";
    
    static final String JSON_RECITATION = "recitation";
    static final String JSON_SECTION = "section";
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_DAY_TIME = "dayTime";
    static final String JSON_LOCATION = "location";
    static final String JSON_SUP_TA_ONE = "supTaOne";
    static final String JSON_SUP_TA_TWO = "supTaTwo";
    
    static final String JSON_SCHEDULE_DATA = "scheduleData";
    static final String JSON_SCHEDULE = "schedule";
    static final String JSON_START_DAY = "startDay";
    static final String JSON_END_DAY = "endDay";
    static final String JSON_SCHEDULE_TYPE = "type";
    static final String JSON_SCHEDULE_DATE = "date";
    static final String JSON_SCHEDULE_TIME = "time";
    static final String JSON_SCHEDULE_TITLE = "title";
    static final String JSON_SCHEDULE_TOPIC = "topic";
    static final String JSON_SCHEDULE_LINK = "link";
    static final String JSON_SCHEDULE_CRITERIA = "criteria";
    
    static final String JSON_COURSE_DETAILS = "courseDetails";
    static final String JSON_COURSE_DATA = "courseDetailsData";
    static final String JSON_SITE_PAGES_DATA = "sitePagesData";
    
    static final String JSON_COURSE_SUBJECT = "subject";
    static final String JSON_COURSE_NUMBER = "number";
    static final String JSON_COURSE_SEMESTER = "semester";
    static final String JSON_COURSE_YEAR = "year";
    static final String JSON_COURSE_TITLE = "title";
    static final String JSON_COURSE_INAME = "instructorName";
    static final String JSON_COURSE_IHOME = "instructorHome";
    static final String JSON_COURSE_EXPORT_DIR = "exportDir";
    
    static final String JSON_COURSE_SITE_TEMP = "siteTemplate";
    static final String JSON_COURSE_EXPORT = "exportFiles";
    
    static final String JSON_COURSE_BANNER = "banner";
    static final String JSON_COURSE_LEFTF = "leftFooter";
    static final String JSON_COURSE_RIGHTF = "rightFooter";
    static final String JSON_COURSE_STYLE = "stylesheet";
    
    static final String JSON_USED = "used";
    static final String JSON_NAVBAR = "navBar";
    static final String JSON_FILE_NAME = "fileName";
    static final String JSON_SCRIPT = "script";
    
    static final String JSON_TA_HOURS = "startEndTime";
    private ArrayList<String> nodeHolder;
    CSGApp app;
    public CSGFile(CSGApp app) {
        this.app = app;
    }

    public ArrayList<String> getNodeHolder() {
        return nodeHolder;
    }
    
    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        CSGData cData = (CSGData)app.getDataComponent();
        JsonObject cdJson = null;
        CourseDetails cd =cData.getCourseData().getCourses();
        cd.setSubject(workspace.getCourseDetailPane().getSubjectBox().getSelectionModel().getSelectedItem().toString());
        cd.setBannerImage(workspace.getCourseDetailPane().getBanner().getImage().toString());
        cd.setExportDir(workspace.getCourseDetailPane().getExportDir().getText());
        cd.setLeftFooterImage(workspace.getCourseDetailPane().getLeftFooter().getImage().toString());
        cd.setNumber(workspace.getCourseDetailPane().getNumberBox().getSelectionModel().getSelectedItem().toString());
        cd.setRightFooterImage(workspace.getCourseDetailPane().getRightFooter().getImage().toString());
        cd.setSemester(workspace.getCourseDetailPane().getSemesterBox().getSelectionModel().getSelectedItem().toString());
        cd.setSiteTemplate(workspace.getCourseDetailPane().getSiteTemplate().toString());
        cd.setStylesheet(workspace.getCourseDetailPane().getStylesheetBox().getSelectionModel().getSelectedItem().toString());
        cd.setSubject(workspace.getCourseDetailPane().getSubjectBox().getSelectionModel().getSelectedItem().toString());
        cd.setTitle(workspace.getCourseDetailPane().getTitleField().getText());
        cd.setYear(workspace.getCourseDetailPane().getYearBox().getSelectionModel().getSelectedItem().toString());
        cd.setiHome(workspace.getCourseDetailPane().getiHomeField().getText());
        cd.setiName(workspace.getCourseDetailPane().getiNameField().getText());
            cdJson = Json.createObjectBuilder()
                    .add(JSON_COURSE_SUBJECT, cd.getSubject())
                    .add(JSON_COURSE_SEMESTER, cd.getSemester())
                    .add(JSON_COURSE_NUMBER, cd.getNumber())
                    .add(JSON_COURSE_YEAR, cd.getYear())
                    .add(JSON_COURSE_TITLE, cd.getTitle())
                    .add(JSON_COURSE_INAME, cd.getiName())
                    .add(JSON_COURSE_IHOME, cd.getiHome())
                    .add(JSON_COURSE_EXPORT_DIR, cd.getExportDir())
                    .add(JSON_COURSE_SITE_TEMP, cd.getSiteTemplate())
                    .add(JSON_COURSE_BANNER, cd.getBannerImage())
                    .add(JSON_COURSE_LEFTF, cd.getLeftFooterImage())
                    .add(JSON_COURSE_RIGHTF, cd.getRightFooterImage())
                    .add(JSON_COURSE_STYLE, cd.getStylesheet()).build();     
        
            //Site Pages
        JsonArrayBuilder sitePages = Json.createArrayBuilder();
        ObservableList<SitePages> sitepages = cData.getCourseData().getSitePages().getSitePages();
        for(SitePages sp : sitepages){
            JsonObject spObj = Json.createObjectBuilder()
                    .add(JSON_USED, sp.isUsed())
                    .add(JSON_NAVBAR,sp.getNavBar())
                    .add(JSON_FILE_NAME,sp.getFileName())
                    .add(JSON_SCRIPT,sp.getScript()).build();
            sitePages.add(spObj);
        }
            //Details and Site Pages
        JsonObject courseDetailsJson = Json.createObjectBuilder()
                .add(JSON_COURSE_DATA, cdJson)
                .add(JSON_SITE_PAGES_DATA, sitePages).build();
        
        //TaData
            //Teaching Assistants
            ObservableList<TeachingAssistant> taData = cData.getTaData().getTeachingAssistants();
        JsonArrayBuilder taJson = Json.createArrayBuilder();
        for(TeachingAssistant ta : taData){
            JsonObject teachingAssistant = Json.createObjectBuilder()
                    .add(JSON_NAME, ta.getName())
                    .add(JSON_EMAIL, ta.getEmail())
                    .add(JSON_TA_UNDERGRAD, ta.isUndergrad()).build();
            taJson.add(teachingAssistant);
        }
            //Office Hour Date
        JsonArrayBuilder officeHourJson = Json.createArrayBuilder();
        ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(cData.getTaData());
        for(TimeSlot ts : officeHours){
            JsonObject officeHour = Json.createObjectBuilder()
                    .add(JSON_DAY, ts.getDay())
                    .add(JSON_TIME, ts.getTime())
                    .add(JSON_NAME, ts.getName()).build();
            officeHourJson.add(officeHour);
        }
            //TaAndOfficeHours
        JsonObject taDataJson = Json.createObjectBuilder()
                .add(JSON_TAS, taJson)
                .add(JSON_START_HOUR, cData.getTaData().getStartHour())
                .add(JSON_END_HOUR, cData.getTaData().getEndHour())
                .add(JSON_OFFICE_HOURS, officeHourJson).build();
        
        //Recitation
        ObservableList<Recitation> recitationData = cData.getRecitationData().getRecitation();
        JsonArrayBuilder recitationJson = Json.createArrayBuilder();
        for(Recitation r : recitationData){
            JsonObject rJson = Json.createObjectBuilder()
                    .add(JSON_SECTION, r.getSection())
                    .add(JSON_INSTRUCTOR, r.getInstructor())
                    .add(JSON_DAY_TIME, r.getDayTime())
                    .add(JSON_LOCATION, r.getLocation())
                    .add(JSON_SUP_TA_ONE, r.getTaOne())
                    .add(JSON_SUP_TA_TWO, r.getTaTwo()).build();
            recitationJson.add(rJson);
        }
        
        //Schedule
            //scheduleData
        ObservableList<Schedule> scheduleData = cData.getScheduleData().getSchedules();
        JsonArrayBuilder schedule = Json.createArrayBuilder();
        for(Schedule sched : scheduleData){
            JsonObject schedJson = Json.createObjectBuilder()
                    .add(JSON_SCHEDULE_TYPE, sched.getType())
                    .add(JSON_SCHEDULE_DATE, sched.getDate())
                    .add(JSON_SCHEDULE_TIME, sched.getTime())
                    .add(JSON_SCHEDULE_TITLE, sched.getTitle())
                    .add(JSON_SCHEDULE_TOPIC, sched.getTopic())
                    .add(JSON_SCHEDULE_LINK, sched.getLink())
                    .add(JSON_SCHEDULE_CRITERIA, sched.getCriteria()).build();
            schedule.add(schedJson);
        }
            //DaysDataTogether
        JsonObject scheduleJson = Json.createObjectBuilder()
                .add(JSON_START_DAY, "")
                .add(JSON_END_DAY, "")
                .add(JSON_SCHEDULE_DATA, schedule).build();
        
        //Projects
            //Students
            ObservableList<Student> studentData = cData.getProjectData().getStudent();
        JsonArrayBuilder students = Json.createArrayBuilder();
        for(Student s : studentData){
            JsonObject sJson = Json.createObjectBuilder()
                .add(JSON_STUDENT_FIRST_NAME, s.getFirstName())
                .add(JSON_STUDENT_LAST_NAME, s.getLastName())
                .add(JSON_STUDENT_TEAM_NAME, s.getTeam())
                .add(JSON_ROLE, s.getRole()).build();
            students.add(sJson);
            //Teams
        ObservableList<Team> teamData = cData.getProjectData().getTeam();
        JsonArrayBuilder teams = Json.createArrayBuilder();
        for(Team t : teamData){
            JsonObject tJson = Json.createObjectBuilder()
                    .add(JSON_TEAM_NAME, t.getName())
                    .add(JSON_TEAM_COLOR, t.getColorHex())
                    .add(JSON_TEAM_TEXT_COLOR, t.getTextColorHex())
                    .add(JSON_TEAM_LINK, t.getLink()).build();
            teams.add(tJson);
        }
            //ProjectsTogether
        JsonObject projectsJson = Json.createObjectBuilder()
                .add(JSON_TEAM, teams)
                .add(JSON_STUDENT, students).build();
        
        //Ouput to File
        JsonObject fullFile = Json.createObjectBuilder()
                .add(JSON_COURSE_DETAILS, courseDetailsJson)
                .add(JSON_TA_DATA, taDataJson)
                .add(JSON_RECITATION, recitationJson)
                .add(JSON_SCHEDULE, scheduleJson)
                .add(JSON_PROJECTS, projectsJson).build();
        
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(fullFile);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath+".json");
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(fullFile);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath+".json");
	pw.write(prettyPrinted);
	pw.close();
            System.out.println("Done writing");
        }
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
        CSGData dataManager = (CSGData)data;
        
        JsonObject json = loadJSONFile(filePath);
        
        //CourseDetails
        JsonObject course = json.getJsonObject(JSON_COURSE_DETAILS);
        
        
            JsonObject cData = course.getJsonObject(JSON_COURSE_DATA);
            String subject = cData.getString(JSON_COURSE_SUBJECT);
            String semester = cData.getString(JSON_COURSE_SEMESTER);
            String number = cData.getString(JSON_COURSE_NUMBER);
            String year = cData.getString(JSON_COURSE_YEAR);
            String iName = cData.getString(JSON_COURSE_INAME);
            String iHome = cData.getString(JSON_COURSE_IHOME);
            String exportDir = cData.getString(JSON_COURSE_EXPORT_DIR);
            String siteTemplate = cData.getString(JSON_COURSE_SITE_TEMP);
            String banner = cData.getString(JSON_COURSE_BANNER);
            String leftF = cData.getString(JSON_COURSE_LEFTF);
            String rightF = cData.getString(JSON_COURSE_RIGHTF);
            String stylesheet = cData.getString(JSON_COURSE_STYLE);
            String title = cData.getString(JSON_COURSE_TITLE);
            dataManager.getCourseData().addCourse(subject, semester, number, year, title, iName, iHome, exportDir, siteTemplate, banner, leftF, rightF, stylesheet);
            
            
        JsonArray sitePagesArray = course.getJsonArray(JSON_SITE_PAGES_DATA);
        for(int i = 0;i < sitePagesArray.size();i++){
            JsonObject sitePage = sitePagesArray.getJsonObject(i);
            boolean usedBool = sitePage.getBoolean(JSON_USED);
            BooleanProperty used = new SimpleBooleanProperty(usedBool);
            String navBar = sitePage.getString(JSON_NAVBAR);
            String fileName = sitePage.getString(JSON_FILE_NAME);
            String script = sitePage.getString(JSON_SCRIPT);
            dataManager.getCourseData().getSitePages().find(navBar).setUsed(usedBool);
        }
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        if(workspace != null){
            dataManager.changeCon(true);
        }
        
        //TAData
        JsonObject ta = json.getJsonObject(JSON_TA_DATA);
        
        String startHour = ta.getString(JSON_START_HOUR);
        String endHour = ta.getString(JSON_END_HOUR);
        dataManager.getTaData().initHours(startHour, endHour);
        
        JsonArray jsonTAArray = ta.getJsonArray(JSON_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            boolean undergrad = jsonTA.getBoolean(JSON_TA_UNDERGRAD);
            dataManager.getTaData().addTA(name, email,undergrad);
        }
            
        JsonArray jsonOfficeHoursArray = ta.getJsonArray(JSON_OFFICE_HOURS);
            for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
//            dataManager.getTaData().addOfficeHoursReservation(day, time, name);
        }
            
        //Schedule
        JsonObject schedule = json.getJsonObject(JSON_SCHEDULE);
        
        JsonArray jsonScheduleArray = schedule.getJsonArray(JSON_SCHEDULE_DATA);
        for(int i = 0;i < jsonScheduleArray.size();i++){
            JsonObject jsonSchedule = jsonScheduleArray.getJsonObject(i);
            String type = jsonSchedule.getString(JSON_SCHEDULE_TYPE);
            String date = jsonSchedule.getString(JSON_SCHEDULE_DATE);
            String time = jsonSchedule.getString(JSON_SCHEDULE_TIME);
            String schedTitle = jsonSchedule.getString(JSON_SCHEDULE_TITLE);
            String topic = jsonSchedule.getString(JSON_SCHEDULE_TOPIC);
            String link = jsonSchedule.getString(JSON_SCHEDULE_LINK);
            String criteria = jsonSchedule.getString(JSON_SCHEDULE_CRITERIA);
            dataManager.getScheduleData().addSchedule(type,date,time,schedTitle,topic,link,criteria);
        }
            String startingMon = schedule.getString(JSON_START_DAY);
            String endingFri = schedule.getString(JSON_END_DAY);
            dataManager.getScheduleData().setDates(startingMon,endingFri);
        
        //Recitation
        JsonArray jsonRecitationArray = json.getJsonArray(JSON_RECITATION);
        for(int i = 0;i < jsonRecitationArray.size();i++){
            JsonObject jsonRecitation = jsonRecitationArray.getJsonObject(i);
            String section = jsonRecitation.getString(JSON_SECTION);
            String instructor = jsonRecitation.getString(JSON_INSTRUCTOR);
            String dayTime = jsonRecitation.getString(JSON_DAY_TIME);
            String location = jsonRecitation.getString(JSON_LOCATION);
            String taOne = jsonRecitation.getString(JSON_SUP_TA_ONE);
            String taTwo = jsonRecitation.getString(JSON_SUP_TA_TWO);
            dataManager.getRecitationData().addRecitation(section,instructor,dayTime,location,taOne,taTwo);
        }
        
        //Projects
        JsonObject projects = json.getJsonObject(JSON_PROJECTS);
        
        JsonArray jsonTeamArray = projects.getJsonArray(JSON_TEAM);
        for(int i = 0;i < jsonTeamArray.size();i++){
            JsonObject jsonTeam = jsonTeamArray.getJsonObject(i);
            String teamName = jsonTeam.getString(JSON_TEAM_NAME);
            String teamColor = jsonTeam.getString(JSON_TEAM_COLOR);
            String teamTextColor = jsonTeam.getString(JSON_TEAM_TEXT_COLOR);
            String teamLink = jsonTeam.getString(JSON_TEAM_LINK);
            dataManager.getProjectData().addTeam(teamName,teamColor,teamTextColor,teamLink);
        }
        
        JsonArray jsonStudentArray = projects.getJsonArray(JSON_STUDENT);
        for(int i = 0;i < jsonTeamArray.size();i++){
            JsonObject jsonStudent = jsonStudentArray.getJsonObject(i);
            String firstName = jsonStudent.getString(JSON_STUDENT_FIRST_NAME);
            String lastName = jsonStudent.getString(JSON_STUDENT_LAST_NAME);
            String teamName = jsonStudent.getString(JSON_STUDENT_TEAM_NAME);
            String role = jsonStudent.getString(JSON_ROLE);
            dataManager.getProjectData().addStudent(firstName,lastName,teamName,role);
        }
    }
    
    public SitePages toExport(ObservableList<SitePages> s, String nav){
        for(int i = 0;i< s.size();i++){
            if(s.get(i).getNavBar().equals(nav)){
                if(s.get(i).isUsed()){
                    return s.get(i);
                }
            }
        }   
        return null;
    }
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        
    }
    static final String JSON_MONDAY = "startingMondayDay";
    static final String JSON_FRIDAY = "endingFridayDay";
    static final String JSON_MONMONTH = "startingMondayMonth";
    static final String JSON_FRIMONTH = "endingFridayMonth";
    static final String JSON_MONTH = "month";
    static final String JSON_SDAY = "day";
    static final String JSON_SCHEDULE_ITEMS = "items";
    static final String JSON_TEAM_RED = "red";
    static final String JSON_TEAM_BLUE = "blue";
    static final String JSON_TEAM_GREEN = "green";
    static final String JSON_STUDENT_PROJ = "students";
    static final String JSON_TEAM_PROJ = "teams";
    static final String JSON_STUDENTS_LIST = "students";
    static final String JSON_WORK = "work";
    
    public String getMonth(String date){
        System.out.println(date);
        String concat = "";
        if(date.length() > 7){
            concat = date.substring(5,7);
        System.out.println(concat);
        if(concat.charAt(0) == '0'){
                concat = concat.substring(1);
                System.out.println(concat);
            }
        }
        return concat;
    }
    public String getDay(String date){
        System.out.println(date.length());
        String concat = "";
        if(date.length() > 7){
            concat = date.substring(8);
            if(concat.charAt(0) == '0')
                concat = concat.substring(1);
        }
        return concat;
        
    }
    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        CSGData cData = (CSGData)data;
        //RecitationData.json
        JsonArrayBuilder rec = Json.createArrayBuilder();
        ObservableList<Recitation> recAr = cData.getRecitationData().getRecitation();
        for(Recitation r : recAr){
            JsonObject recitation = Json.createObjectBuilder()
                    .add(JSON_SECTION, r.getSection()+" ("+r.getInstructor()+")")
                    .add(JSON_DAY_TIME, r.getDayTime())
                    .add(JSON_LOCATION, r.getLocation())
                    .add(JSON_SUP_TA_ONE, r.getTaOne())
                    .add(JSON_SUP_TA_TWO, r.getTaTwo()).build();
            rec.add(recitation);
        }
        
        //OfficeHoursGridData.json
        JsonArrayBuilder taJson = Json.createArrayBuilder();
        ObservableList<TeachingAssistant> taAr = cData.getTaData().getTeachingAssistants();
        for(TeachingAssistant ta : taAr){
            JsonObject teachingAssistant = Json.createObjectBuilder()
                    .add(JSON_NAME, ta.getName())
                    .add(JSON_EMAIL, ta.getEmail())
                    .add(JSON_TA_UNDERGRAD, ta.isUndergrad()).build();
            taJson.add(teachingAssistant);
        }
            //Office Hour Date
        JsonArrayBuilder officeHourJson = Json.createArrayBuilder();
        ArrayList<TimeSlot> tsAr = TimeSlot.buildOfficeHoursList(cData.getTaData());
        for(TimeSlot ts : tsAr){
            JsonObject officeHour = Json.createObjectBuilder()
                    .add(JSON_DAY, ts.getDay())
                    .add(JSON_TIME, ts.getTime())
                    .add(JSON_NAME, ts.getName()).build();
            officeHourJson.add(officeHour);
        }
            //TaAndOfficeHours
        JsonObject taDataJson = Json.createObjectBuilder()
                .add(JSON_TAS, taJson)
                .add(JSON_START_HOUR, cData.getTaData().getStartHour())
                .add(JSON_END_HOUR, cData.getTaData().getEndHour())
                .add(JSON_OFFICE_HOURS, officeHourJson).build();
        
        //SCHEDULEDATA.JSON
        JsonObject scheduleData = null;
        JsonArrayBuilder schedule = Json.createArrayBuilder();
        ObservableList<Schedule> sList = cData.getScheduleData().getSchedules();
        System.out.println(cData.getScheduleData().getStartMon());
                
        for(Schedule s : sList){
            scheduleData = Json.createObjectBuilder()
                    .add(JSON_MONTH, getMonth(s.getDate()))
                    .add(JSON_SDAY, getDay(s.getDate()))
                    .add(JSON_SCHEDULE_TITLE, s.getTitle())
                    .add(JSON_SCHEDULE_LINK, s.getLink())
                    .add(JSON_SCHEDULE_CRITERIA, s.getCriteria())
                    .add(JSON_SCHEDULE_TIME, s.getTime())
                    .add(JSON_SCHEDULE_TOPIC, s.getTopic())
                    .add(JSON_SCHEDULE_TYPE, s.getType())
                    .build();
            schedule.add(scheduleData);
        }
        JsonObject scheduleComp = Json.createObjectBuilder()
                .add(JSON_MONMONTH, getMonth(cData.getScheduleData().getStartMon()))
                .add(JSON_MONDAY, getDay(cData.getScheduleData().getStartMon()))
                .add(JSON_FRIMONTH, getMonth(cData.getScheduleData().getEndFri()))
                .add(JSON_FRIDAY, getDay(cData.getScheduleData().getEndFri()))
                .add(JSON_SCHEDULE_ITEMS, schedule).build();
        
        //TeamAndStudents.json
        JsonArrayBuilder teams = Json.createArrayBuilder();
        ObservableList<Team> teamList = cData.getProjectData().getTeam();
        for(Team t : teamList){
            Color color = Color.valueOf(t.getColorHex());
            JsonObject team = Json.createObjectBuilder()
                    .add(JSON_TEAM_NAME, t.getName())
                    .add(JSON_TEAM_RED, color.getRed())
                    .add(JSON_TEAM_GREEN, color.getGreen())
                    .add(JSON_TEAM_BLUE, color.getBlue())
                    .add(JSON_TEAM_TEXT_COLOR, t.getTextColorHex()).build();
            teams.add(team);
        }
        JsonArrayBuilder students = Json.createArrayBuilder();
        ObservableList<Student> studentList = cData.getProjectData().getStudent();
        for(Student s : studentList){
            JsonObject student = Json.createObjectBuilder()
                    .add(JSON_STUDENT_LAST_NAME, s.getLastName())
                    .add(JSON_STUDENT_FIRST_NAME, s.getFirstName())
                    .add(JSON_STUDENT_TEAM_NAME, s.getTeam())
                    .add(JSON_ROLE, s.getRole()).build();
            students.add(student);
        }
        JsonObject projectsComp = Json.createObjectBuilder()
                .add(JSON_TEAM_PROJ, teams)
                .add(JSON_STUDENT_PROJ, students).build();
        
        //PROJECTSDATA.JSON
        JsonArrayBuilder workDat = Json.createArrayBuilder();
        
        JsonArrayBuilder proj = Json.createArrayBuilder();
        ObservableList<Team> teamProj = cData.getProjectData().getTeam();
        ObservableList<Student> studentProj = cData.getProjectData().getStudent();
        for(Team t : teamProj){
            ArrayList<String> sStud = getStudentList(t.getName(),studentProj);
            Gson gson = new Gson();
            String gsonStud = gson.toJson(sStud);
            JsonObject projO = Json.createObjectBuilder()
                    .add(JSON_TEAM_NAME, t.getName())
                    .add(JSON_STUDENTS_LIST, gsonStud)
                    .add(gsonStud, BigDecimal.ONE)
                    .add(JSON_TEAM_LINK, t.getLink()).build();
            proj.add(projO); 
        }
        JsonObject workData = Json.createObjectBuilder()
                .add(JSON_COURSE_SEMESTER, cData.getCourseData().getCourses().getSemester()+" "+cData.getCourseData().getCourses().getYear())
                .add(JSON_PROJECTS, proj).build();
      
        workDat.add(workData);
        
        JsonObject teamStudComp = Json.createObjectBuilder()
                .add(JSON_WORK, workDat).build();
        
//        JsonObject courseDetails = Json.createObjectBuilder()
//                .add(JSON_, BigDecimal.ONE)
        
        //WRITING ALL TO FILES
        JsonObject officeHoursDataJson = Json.createObjectBuilder()
                .add("OfficeHoursData", taDataJson).build();
        JsonObject scheduleDataJson = Json.createObjectBuilder()
                .add("ScheduleData", scheduleComp).build();
        JsonObject teamStudentDataJson = Json.createObjectBuilder()
                .add("TeamAndStudents", teamStudComp).build();
        JsonObject projectsDataJson = Json.createObjectBuilder()
                .add("ProjectsData", projectsComp).build();
        JsonObject recitationDataJson = Json.createObjectBuilder()
                .add("RecitationsData", rec).build();
        
        ObservableList<SitePages> toExport = cData.getCourseData().getSitePages().getSitePages();
        //ScheduleData.json
        
        SitePages homeEx = toExport(toExport,"Home");
        SitePages syllabusEx = toExport(toExport,"Syllabus");
        SitePages scheduleEx = toExport(toExport,"Schedule");
        SitePages hwEx = toExport(toExport,"HWs");
        SitePages projectsEx = toExport(toExport,"Projects");
        Workspace workspace = (Workspace)app.getWorkspaceComponent();
        String fileExt = workspace.getCourseDetailPane().getExportToDir().getText();
        String templateDir = workspace.getCourseDetailPane().getTempSelected().getText();
        
        if(fileExt != null && templateDir != null){
//            copy(templateDir,fileExt);
            Path dest = Paths.get(fileExt);
            Path source = Paths.get(templateDir);
            
//            Files.copy(source, dest, REPLACE_EXISTING);
            copy(templateDir,fileExt);

            if(scheduleEx != null)
                writeJson(scheduleDataJson,fileExt+"/js/ScheduleData.json");
            if(hwEx != null)
                writeJson(scheduleDataJson,fileExt+"/js/ScheduleData.json");
            if(syllabusEx != null){
                writeJson(officeHoursDataJson,fileExt+"/js/OfficeHoursData.json");
                writeJson(recitationDataJson,fileExt+"/js/RecitationData.json");
            }
            if(projectsEx != null){
                writeJson(projectsDataJson,fileExt+"/js/ProjectsData.json");
            }
            if(homeEx != null){
                writeJson(teamStudentDataJson,fileExt+"/js/TeamAndStudentsData.json");
            }
                
        }
        
    }
    public void writeJson(JsonObject obj1, String filePath) throws FileNotFoundException{
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(obj1);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(obj1);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    public ArrayList<String> getStudentList(String teamName, ObservableList<Student> student){
        ArrayList<String> returned = new ArrayList();
        for(int i = 0;i < student.size();i++){
            if(student.get(i).getTeam().equals(teamName)){
                returned.add(student.get(i).getFirstName()+" "+student.get(i).getLastName());
            }
        }
        return returned;
    }
    public void copy(String oldPath, String newPath) { 
        try { 
            (new File(newPath)).mkdirs();
            File old =new File(oldPath); 
            String[] file = old.list(); 
            File targget = null; 
            for (int i = 0; i < file.length; i++) { 
                if(oldPath.endsWith(File.separator))
                    targget = new File(oldPath+file[i]); 
                else
                    targget = new File(oldPath+File.separator+file[i]); 
                if(targget.isFile()){ 
                    FileInputStream input = new FileInputStream(targget); 
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (targget.getName()).toString()); 
                    byte[] b = new byte[1024 * 5]; 
                    int templength; 
                    while ( (templength = input.read(b)) != -1)
                        output.write(b, 0, templength); 
                    output.flush(); 
                    output.close(); 
                    input.close(); 
                } 
                if(targget.isDirectory())
                    copy(oldPath+"/"+file[i],newPath+"/"+file[i]); 
            } 
        } 
        catch (Exception e) {  
            e.printStackTrace(); 
        } 
    }
    public void saveSite(ArrayList<TeachingAssistant> taData,ArrayList<TimeSlot> officeHours,ArrayList<Student> studentData,ArrayList<Team> teamData,ArrayList<Recitation> recitationData,ArrayList<CourseDetails> courseDetailsData, ArrayList<Schedule> scheduleData,String filePath,ArrayList<SitePages> sitePagesData,String startHour, String endHour) throws FileNotFoundException{
        
        //CourseDetails
            //Frame Details
        JsonObject cdJson = null;
        for(CourseDetails cd : courseDetailsData){
            cdJson = Json.createObjectBuilder()
                    .add(JSON_COURSE_SUBJECT, cd.getSubject())
                    .add(JSON_COURSE_SEMESTER, cd.getSemester())
                    .add(JSON_COURSE_NUMBER, cd.getNumber())
                    .add(JSON_COURSE_YEAR, cd.getYear())
                    .add(JSON_COURSE_TITLE, cd.getTitle())
                    .add(JSON_COURSE_INAME, cd.getiName())
                    .add(JSON_COURSE_IHOME, cd.getiHome())
                    .add(JSON_COURSE_EXPORT_DIR, cd.getExportDir())
                    .add(JSON_COURSE_SITE_TEMP, cd.getSiteTemplate())
                    .add(JSON_COURSE_BANNER, cd.getBannerImage())
                    .add(JSON_COURSE_LEFTF, cd.getLeftFooterImage())
                    .add(JSON_COURSE_RIGHTF, cd.getRightFooterImage())
                    .add(JSON_COURSE_STYLE, cd.getStylesheet()).build();     
        }
            //Site Pages
        JsonArrayBuilder sitePages = Json.createArrayBuilder();
        for(SitePages sp : sitePagesData){
            JsonObject spObj = Json.createObjectBuilder()
                    .add(JSON_USED, sp.isUsed())
                    .add(JSON_NAVBAR,sp.getNavBar())
                    .add(JSON_FILE_NAME,sp.getFileName())
                    .add(JSON_SCRIPT,sp.getScript()).build();
            sitePages.add(spObj);
        }
            //Details and Site Pages
        JsonObject courseDetailsJson = Json.createObjectBuilder()
                .add(JSON_COURSE_DATA, cdJson)
                .add(JSON_SITE_PAGES_DATA, sitePages).build();
        
        //TaData
            //Teaching Assistants
        JsonArrayBuilder taJson = Json.createArrayBuilder();
        for(TeachingAssistant ta : taData){
            JsonObject teachingAssistant = Json.createObjectBuilder()
                    .add(JSON_NAME, ta.getName())
                    .add(JSON_EMAIL, ta.getEmail())
                    .add(JSON_TA_UNDERGRAD, ta.isUndergrad()).build();
            taJson.add(teachingAssistant);
        }
            //Office Hour Date
        JsonArrayBuilder officeHourJson = Json.createArrayBuilder();
        for(TimeSlot ts : officeHours){
            JsonObject officeHour = Json.createObjectBuilder()
                    .add(JSON_DAY, ts.getDay())
                    .add(JSON_TIME, ts.getTime())
                    .add(JSON_NAME, ts.getName()).build();
            officeHourJson.add(officeHour);
        }
            //TaAndOfficeHours
        JsonObject taDataJson = Json.createObjectBuilder()
                .add(JSON_TAS, taJson)
                .add(JSON_START_HOUR, startHour)
                .add(JSON_END_HOUR, endHour)
                .add(JSON_OFFICE_HOURS, officeHourJson).build();
        
        //Recitation
        JsonArrayBuilder recitationJson = Json.createArrayBuilder();
        for(Recitation r : recitationData){
            JsonObject rJson = Json.createObjectBuilder()
                    .add(JSON_SECTION, r.getSection())
                    .add(JSON_INSTRUCTOR, r.getInstructor())
                    .add(JSON_DAY_TIME, r.getDayTime())
                    .add(JSON_LOCATION, r.getLocation())
                    .add(JSON_SUP_TA_ONE, r.getTaOne())
                    .add(JSON_SUP_TA_TWO, r.getTaTwo()).build();
            recitationJson.add(rJson);
        }
        
        //Schedule
            //scheduleData
        JsonArrayBuilder schedule = Json.createArrayBuilder();
        for(Schedule sched : scheduleData){
            JsonObject schedJson = Json.createObjectBuilder()
                    .add(JSON_SCHEDULE_TYPE, sched.getType())
                    .add(JSON_SCHEDULE_DATE, sched.getDate())
                    .add(JSON_SCHEDULE_TIME, sched.getTime())
                    .add(JSON_SCHEDULE_TITLE, sched.getTitle())
                    .add(JSON_SCHEDULE_TOPIC, sched.getTopic())
                    .add(JSON_SCHEDULE_LINK, sched.getLink())
                    .add(JSON_SCHEDULE_CRITERIA, sched.getCriteria()).build();
            schedule.add(schedJson);
        }
            //DaysDataTogether
        JsonObject scheduleJson = Json.createObjectBuilder()
                .add(JSON_START_DAY, "")
                .add(JSON_END_DAY, "Ending Date Input Later")
                .add(JSON_SCHEDULE_DATA, schedule).build();
        
        //Projects
            //Students
        JsonArrayBuilder students = Json.createArrayBuilder();
        for(Student s : studentData){
            JsonObject sJson = Json.createObjectBuilder()
                .add(JSON_STUDENT_FIRST_NAME, s.getFirstName())
                .add(JSON_STUDENT_LAST_NAME, s.getLastName())
                .add(JSON_STUDENT_TEAM_NAME, s.getTeam())
                .add(JSON_ROLE, s.getRole()).build();
            students.add(sJson);
            //Teams
        JsonArrayBuilder teams = Json.createArrayBuilder();
        for(Team t : teamData){
            JsonObject tJson = Json.createObjectBuilder()
                    .add(JSON_TEAM_NAME, t.getName())
                    .add(JSON_TEAM_COLOR, t.getColorHex())
                    .add(JSON_TEAM_TEXT_COLOR, t.getTextColorHex())
                    .add(JSON_TEAM_LINK, t.getLink()).build();
            teams.add(tJson);
        }
            //ProjectsTogether
        JsonObject projectsJson = Json.createObjectBuilder()
                .add(JSON_TEAM, teams)
                .add(JSON_STUDENT, students).build();
        
        //Ouput to File
        JsonObject fullFile = Json.createObjectBuilder()
                .add(JSON_COURSE_DETAILS, courseDetailsJson)
                .add(JSON_TA_DATA, taDataJson)
                .add(JSON_RECITATION, recitationJson)
                .add(JSON_SCHEDULE, scheduleJson)
                .add(JSON_PROJECTS, projectsJson).build();
        
        Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(fullFile);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(fullFile);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
            System.out.println("Done writing");
        }
     }
     private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
}
