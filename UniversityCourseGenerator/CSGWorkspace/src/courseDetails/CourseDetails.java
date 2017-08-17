/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseDetails;

import java.util.ArrayList;

/**
 *
 * @author Nick
 */
public class CourseDetails {
    String subject;
    String semester;
    String number;
    String year;
    String title;
    String iName;
    String iHome;
    String exportDir;
    String siteTemplate;
    ArrayList<String> toExport;
    String bannerImage;
    String leftFooterImage;
    String rightFooterImage;
    String stylesheet;
    public CourseDetails(String subject,String semester,String number,String year,String title,String iName,String iHome,String exportDir,String siteTemplate,String bannerImage,String leftFooterImage,String rightFooterImage,String stylesheet){
        this.subject = subject;
        this.semester = semester;
        this.number = number;
        this.year = year;
        this.title = title;
        this.iName = iName;
        this.iHome = iHome;
        this.exportDir = exportDir;
        this.siteTemplate = siteTemplate;
        this.bannerImage = bannerImage;
        this.leftFooterImage = leftFooterImage;
        this.rightFooterImage = rightFooterImage;
        this.stylesheet = stylesheet;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getiHome() {
        return iHome;
    }

    public void setiHome(String iHome) {
        this.iHome = iHome;
    }

    public String getExportDir() {
        return exportDir;
    }

    public void setExportDir(String exportDir) {
        this.exportDir = exportDir;
    }

    public String getSiteTemplate() {
        return siteTemplate;
    }

    public void setSiteTemplate(String siteTemplate) {
        this.siteTemplate = siteTemplate;
    }

    public ArrayList<String> getToExport() {
        return toExport;
    }

    public void setToExport(ArrayList<String> toExport) {
        this.toExport = toExport;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getLeftFooterImage() {
        return leftFooterImage;
    }

    public void setLeftFooterImage(String leftFooterImage) {
        this.leftFooterImage = leftFooterImage;
    }

    public String getRightFooterImage() {
        return rightFooterImage;
    }

    public void setRightFooterImage(String rightFooterImage) {
        this.rightFooterImage = rightFooterImage;
    }

    public String getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }
    
}
