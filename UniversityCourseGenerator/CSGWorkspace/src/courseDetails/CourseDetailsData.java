/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseDetails;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import recitationData.Recitation;

/**
 *
 * @author Nick
 */
public class CourseDetailsData {
    CourseDetails courses;
    SitePagesData sitePages;
    public CourseDetailsData(){
        sitePages = new SitePagesData();
    }
    public void addCourse(String subject,String semester,String number,String year,String title,String iName,String iHome,String exportDir,String siteTemplate,String bannerImage,String leftFooterImage,String rightFooterImage,String stylesheet) {
        courses = new CourseDetails(subject,semester,number,year,title,iName,iHome,exportDir,siteTemplate,bannerImage,leftFooterImage,rightFooterImage,stylesheet);
        
    }

    public SitePagesData getSitePages() {
        return sitePages;
    }
    
    public CourseDetails getCourses() {
        return courses;
    }
}
