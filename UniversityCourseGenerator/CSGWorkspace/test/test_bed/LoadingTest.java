/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_bed;

import CSG.test_bed.TestSave;
import static CSG.test_bed.TestSave.test_load;
import courseDetails.CourseDetails;
import courseDetails.SitePages;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class LoadingTest {
    
    public LoadingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void test_loadCheck() throws IOException{
        TestSave ts = new TestSave();
        CourseDetails[] cArray = ts.getCourseDetailsData().toArray(new CourseDetails[ts.getCourseDetailsData().size()]);
        CourseDetails[] cArray2 =  ts.getCourseDetailsData2().toArray(new CourseDetails[ts.getCourseDetailsData2().size()]);
        Recitation[] rArray = ts.getRecitationData().toArray(new Recitation[ts.getRecitationData().size()]);
        Recitation[] rArray2 = ts.getRecitationData2().toArray(new Recitation[ts.getRecitationData2().size()]);
        Schedule[] sArray = ts.getScheduleData().toArray(new Schedule[ts.getScheduleData().size()]);
        Schedule[] sArray2 = ts.getScheduleData2().toArray(new Schedule[ts.getScheduleData2().size()]);
        SitePages[] spArray = ts.getSitePagesData().toArray(new SitePages[ts.getSitePagesData().size()]);
        SitePages[] spArray2 = ts.getSitePagesData2().toArray(new SitePages[ts.getSitePagesData2().size()]);
        Student[] studArray = ts.getStudentData().toArray(new Student[ts.getStudentData().size()]);
        Student[] studArray2 = ts.getStudentData2().toArray(new Student[ts.getStudentData2().size()]);
        TeachingAssistant[] taArray = ts.getTaData().toArray(new TeachingAssistant[ts.getTaData().size()]);
        TeachingAssistant[] taArray2 = ts.getTaData2().toArray(new TeachingAssistant[ts.getTaData2().size()]);
        Team[] tArray = ts.getTeamData().toArray(new Team[ts.getTeamData().size()]);
        Team[] tArray2 = ts.getTeamData2().toArray(new Team[ts.getTeamData2().size()]);
        TimeSlot[] tsArray = ts.getTimeSlotData().toArray(new TimeSlot[ts.getTimeSlotData().size()]);
        TimeSlot[] tsArray2 = ts.getTimeSlotData2().toArray(new TimeSlot[ts.getTimeSlotData2().size()]);
        
        CourseDetails cd = cArray[0];
        CourseDetails cd2 = cArray2[0];
        assertEquals(cd.getTitle(), cd2.getTitle());
        assertEquals(cd.getBannerImage(), cd2.getBannerImage());
        assertEquals(cd.getExportDir(), cd2.getExportDir());
        assertEquals(cd.getLeftFooterImage(), cd2.getLeftFooterImage());
        assertEquals(cd.getNumber(), cd2.getNumber());
        assertEquals(cd.getRightFooterImage(), cd2.getRightFooterImage());
        assertEquals(cd.getSemester(), cd2.getSemester());
        assertEquals(cd.getSiteTemplate(), cd2.getSiteTemplate());
        assertEquals(cd.getStylesheet(), cd2.getStylesheet());
        assertEquals(cd.getSubject(), cd2.getSubject());
        assertEquals(cd.getTitle() ,cd2.getTitle());
        assertEquals(cd.getYear(), cd2.getYear());
        assertEquals(cd.getiHome(), cd2.getiHome());
        assertEquals(cd.getiName(), cd2.getiName());
        
        Recitation r = rArray[0];
        Recitation r2 = rArray2[0];
        assertEquals(r.getDayTime(), r2.getDayTime());
        assertEquals(r.getInstructor(), r2.getInstructor());
        assertEquals(r.getLocation(), r2.getLocation());
        assertEquals(r.getSection(), r2.getSection());
        assertEquals(r.getTaOne(), r2.getTaOne());
        assertEquals(r.getTaTwo(), r2.getTaTwo());
        
        Schedule s = sArray[0];
        Schedule s2 = sArray2[0];
        assertEquals(s.getCriteria(), s2.getCriteria());
        assertEquals(s.getDate(), s2.getDate());
        assertEquals(s.getDate(), s2.getDate());
        assertEquals(s.getLink(), s2.getLink());
        assertEquals(s.getTime(), s2.getTime());
        assertEquals(s.getTitle(), s2.getTitle());
        assertEquals(s.getTopic(), s2.getTopic());
        assertEquals(s.getType(), s2.getType());
        
        SitePages sp = spArray[0];
        SitePages sp2 = spArray2[0];
        assertEquals(sp.getFileName(), sp2.getFileName());
        assertEquals(sp.getNavBar(), sp2.getNavBar());
        assertEquals(sp.getScript(), sp2.getScript());
        
        Student stud = studArray[0];
        Student stud2 = studArray2[0];
        assertEquals(stud.getFirstName(), stud2.getFirstName());
        assertEquals(stud.getLastName(), stud2.getLastName());
        assertEquals(stud.getRole(), stud2.getRole());
        assertEquals(stud.getTeam(), stud2.getTeam());
        
        TeachingAssistant ta = taArray[0];
        TeachingAssistant ta2 = taArray2[0];
        assertEquals(ta.getEmail(), ta2.getEmail());
        assertEquals(ta.getName(), ta2.getName());
        
        Team t = tArray[0];
        Team t2 = tArray2[0];
        assertEquals(t.getColorHex(), t2.getColorHex());
        assertEquals(t.getLink(), t2.getLink());
        assertEquals(t.getName(), t2.getName());
        assertEquals(t.getTextColorHex(), t2.getTextColorHex());
        
        TimeSlot tsl = tsArray[0];
        TimeSlot tsl2 = tsArray2[0];
        assertEquals(tsl.getDay(), tsl2.getDay());
        assertEquals(tsl.getName(), tsl2.getName());
        assertEquals(tsl.getTime(), tsl2.getTime());
        
        
        
        
    }
}
