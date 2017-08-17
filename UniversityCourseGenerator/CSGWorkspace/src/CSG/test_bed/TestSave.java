/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG.test_bed;

import CSG.CSGApp;
import CSG.File.CSGFile;
import courseDetails.CourseDetailPane;
import courseDetails.CourseDetails;
import courseDetails.SitePages;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import projectData.Student;
import projectData.Team;
import recitationData.Recitation;
import scheduleData.Schedule;
import taData.TeachingAssistant;
import taData.TimeSlot;

/**
 *
 * @author Nick
 */
public class TestSave {
    ArrayList<TimeSlot> timeSlotData;
       ArrayList<Schedule> scheduleData;
       ArrayList<CourseDetails> courseDetailsData;
       ArrayList<Recitation> recitationData;
       ArrayList<Team> teamData;
       ArrayList<Student> studentData;
       ArrayList<TeachingAssistant> taData;
       ArrayList<SitePages> sitePagesData;
    public TestSave(){
        try {
            testSave();
            test_load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String [] args){
        CSGApp app = new CSGApp();
        CSGFile csgFile = new CSGFile(app);
        TestSave ts = new TestSave();
        try {
            csgFile.saveSite(ts.getTaData(),ts.getTimeSlotData(),ts.getStudentData(),ts.getTeamData(),ts.getRecitationData(),ts.getCourseDetailsData(),ts.getScheduleData(),"work\\SiteSaveTest.json",ts.getSitePagesData(),"8","10");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    
    }
    private BooleanProperty undergrad;
    private Recitation recitation;
    private Student student;
    private Team team;
    private TeachingAssistant teachingAssistant;
    private Schedule schedule;
    private CourseDetails courseDetails;
    private TimeSlot timeSlot;
    public void testSave(){
       
       undergrad = new SimpleBooleanProperty();
       recitation = new Recitation("R03","Banerjee","Tuesday 3:00pm-3:53pm","Old CS 2214","Wilfred","Wilfred");
       student = new Student("Leeroy","Miller","C4Comics","Data Designer");
       team = new Team("C4Comics","#123234","#000011","www.fakewebsite.com");
       
       teachingAssistant = new TeachingAssistant("Wilfred","James",true); 
       schedule = new Schedule("Holiday","2/9/2017","2:00pm","Snow Day","Midterm review","www.fakeday.com","midterm");
       courseDetails = new CourseDetails("CSE","Fall","219","2017","Computer Science III","Banerjee","www.banerjee.com","..\\..\\..\\Courses\\CSE219",".\\templates\\CSE219","banner.png","leftFooter.png","rightFooter.png","sea_wolf.css");
       timeSlot = new TimeSlot("Monday","2_00PM","Miller\nBen");
       taData = new ArrayList<>();
       taData.add(teachingAssistant);
       
       studentData = new ArrayList<>();
       studentData.add(student);
       
       teamData = new ArrayList<>();
       teamData.add(team);
       
       recitationData = new ArrayList<>();
       recitationData.add(recitation);
       
       courseDetailsData = new ArrayList<>();
       courseDetailsData.add(courseDetails);
       
       scheduleData = new ArrayList<>();
       scheduleData.add(schedule);
       
       timeSlotData = new ArrayList<>();
       timeSlotData.add(timeSlot);
       
       
       sitePagesData = new ArrayList<>();
       sitePagesData.add(new SitePages(new SimpleBooleanProperty(true),"Home","index.html","HomeBuilder.js"));
       sitePagesData.add(new SitePages(new SimpleBooleanProperty(true),"Syllabus","syllabus.html","SyllabusBuilder.js"));
       sitePagesData.add(new SitePages(new SimpleBooleanProperty(true),"Schedule","schedule.html","ScheduleBuilder.js"));
       sitePagesData.add(new SitePages(new SimpleBooleanProperty(true),"HWs","hws.html","HWsBuilder.js"));
       sitePagesData.add(new SitePages(new SimpleBooleanProperty(true),"Projects","projects.html","ProjectsBuilder.js"));
       
    }
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
    
    static ArrayList<TeachingAssistant> taData2 = new ArrayList<>();
    static ArrayList<Student> studentData2 = new ArrayList<>();
    static ArrayList<Team> teamData2 = new ArrayList<>();
    static ArrayList<Recitation> recitationData2 = new ArrayList<>();
    static ArrayList<CourseDetails> courseDetailsData2 = new ArrayList<>();
    static ArrayList<Schedule> scheduleData2 = new ArrayList<>();
    static ArrayList<TimeSlot> timeSlotData2 = new ArrayList<>();
    static ArrayList<SitePages> sitePagesData2 = new ArrayList<>();
    
    public static void test_load() throws IOException{
        JsonObject json = loadJSONFile("work\\SiteSaveTest.json");
        
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
            CourseDetails cD2 = new CourseDetails(subject,semester,number,year,title,iName,iHome,exportDir,siteTemplate,banner,leftF,rightF,stylesheet);
            courseDetailsData2.add(cD2);
            
        JsonArray sitePagesArray = course.getJsonArray(JSON_SITE_PAGES_DATA);
        for(int i = 0;i < sitePagesArray.size();i++){
            JsonObject sitePage = sitePagesArray.getJsonObject(i);
            boolean usedBool = sitePage.getBoolean(JSON_USED);
            BooleanProperty used = new SimpleBooleanProperty(usedBool);
            String navBar = sitePage.getString(JSON_NAVBAR);
            String fileName = sitePage.getString(JSON_FILE_NAME);
            String script = sitePage.getString(JSON_SCRIPT);
            SitePages sP2 = new SitePages(used,navBar,fileName,script);
            sitePagesData2.add(sP2);
        }
        
        
        //TAData
        JsonObject ta = json.getJsonObject(JSON_TA_DATA);
        
        String startHour = ta.getString(JSON_START_HOUR);
        String endHour = ta.getString(JSON_END_HOUR);
        
        
        JsonArray jsonTAArray = ta.getJsonArray(JSON_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            boolean u = jsonTA.getBoolean(JSON_TA_UNDERGRAD);
            
            
            TeachingAssistant ta2 = new TeachingAssistant(name, email, u);
            taData2.add(ta2);
        }
            
        JsonArray jsonOfficeHoursArray = ta.getJsonArray(JSON_OFFICE_HOURS);
            for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
            TimeSlot tS2 = new TimeSlot(day,time,name);
            timeSlotData2.add(tS2);
        }
            
        //Schedule
        JsonObject schedule = json.getJsonObject(JSON_SCHEDULE);
        
        JsonArray jsonScheduleArray = schedule.getJsonArray(JSON_SCHEDULE_DATA);
        for(int i = 0;i < jsonScheduleArray.size();i++){
            JsonObject jsonSchedule = jsonScheduleArray.getJsonObject(i);
            String date = jsonSchedule.getString(JSON_SCHEDULE_DATE);
            String type = jsonSchedule.getString(JSON_SCHEDULE_TYPE);
            String schedTitle = jsonSchedule.getString(JSON_SCHEDULE_TITLE);
            String time = jsonSchedule.getString(JSON_SCHEDULE_TIME);
            String topic = jsonSchedule.getString(JSON_SCHEDULE_TOPIC);
            String link = jsonSchedule.getString(JSON_SCHEDULE_LINK);
            String criteria = jsonSchedule.getString(JSON_SCHEDULE_CRITERIA);
            Schedule s2 = new Schedule(type,date,time,schedTitle,topic,link,criteria);
            scheduleData2.add(s2);
            
        }
            String startingMon = schedule.getString(JSON_START_DAY);
            String endingFri = schedule.getString(JSON_END_DAY);
            
        
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
            Recitation rec2 = new Recitation(section,instructor,dayTime,location,taOne,taTwo);
            recitationData2.add(rec2);
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
            Team team2 = new Team(teamName,teamColor,teamTextColor,teamLink);
            teamData2.add(team2);
        }
        
        JsonArray jsonStudentArray = projects.getJsonArray(JSON_STUDENT);
        for(int i = 0;i < jsonTeamArray.size();i++){
            JsonObject jsonStudent = jsonStudentArray.getJsonObject(i);
            String firstName = jsonStudent.getString(JSON_STUDENT_FIRST_NAME);
            String lastName = jsonStudent.getString(JSON_STUDENT_LAST_NAME);
            String teamName = jsonStudent.getString(JSON_STUDENT_TEAM_NAME);
            String role = jsonStudent.getString(JSON_ROLE);
            Student stud2 = new Student(firstName, lastName, teamName, role);
            studentData2.add(stud2);
        }
    }
    public  ArrayList<CourseDetails> loadCD() throws IOException{
        JsonObject json = loadJSONFile("SiteSaveTest.json");
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
            CourseDetails cD2 = new CourseDetails(subject,semester,number,year,title,iName,iHome,exportDir,siteTemplate,banner,leftF,rightF,stylesheet);
            courseDetailsData2.add(cD2);
            return courseDetailsData2;
    }
    private static JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }

    public ArrayList<TeachingAssistant> getTaData2() {
        return taData2;
    }

    public ArrayList<Student> getStudentData2() {
        return studentData2;
    }

    public ArrayList<Team> getTeamData2() {
        return teamData2;
    }

    public ArrayList<Recitation> getRecitationData2() {
        return recitationData2;
    }

    public ArrayList<CourseDetails> getCourseDetailsData2() {
        return courseDetailsData2;
    }

    public ArrayList<Schedule> getScheduleData2() {
        return scheduleData2;
    }

    public ArrayList<TimeSlot> getTimeSlotData2() {
        return timeSlotData2;
    }
    
    public ArrayList<TimeSlot> getTimeSlotData() {
        return timeSlotData;
    }

    public ArrayList<Schedule> getScheduleData() {
        return scheduleData;
    }

    public ArrayList<CourseDetails> getCourseDetailsData() {
        return courseDetailsData;
    }

    public ArrayList<Recitation> getRecitationData() {
        return recitationData;
    }

    public ArrayList<Team> getTeamData() {
        return teamData;
    }

    public ArrayList<Student> getStudentData() {
        return studentData;
    }

    public ArrayList<TeachingAssistant> getTaData() {
        return taData;
    }

    public ArrayList<SitePages> getSitePagesData2() {
        return sitePagesData2;
    }

    public ArrayList<SitePages> getSitePagesData() {
        return sitePagesData;
    }

    private BooleanProperty getUndergrad() {
        return undergrad;
    }
    
}
