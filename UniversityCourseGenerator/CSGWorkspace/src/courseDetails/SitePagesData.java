/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseDetails;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nick
 */
public class SitePagesData {
    ObservableList<SitePages> sitePages;
    public SitePagesData(){
        sitePages = FXCollections.observableArrayList();
        if(sitePages.isEmpty()){
            sitePages.add(new SitePages(new SimpleBooleanProperty(false),"Home","index.html","HomeBuilder.js"));
            sitePages.add(new SitePages(new SimpleBooleanProperty(false),"Syllabus","syllabus.html","SyllabusBuilder.js"));
            sitePages.add(new SitePages(new SimpleBooleanProperty(false),"Schedule","schedule.html","ScheduleBuilder.js"));
            sitePages.add(new SitePages(new SimpleBooleanProperty(false),"HWs","hws.html","HWsBuilder.js"));
            sitePages.add(new SitePages(new SimpleBooleanProperty(false),"Projects","projects.html","ProjectsBuilder.js"));
        }
    }
    public void addSitePage(BooleanProperty used,String navBar,String fileName,String script){
        sitePages.add(new SitePages(used,navBar,fileName,script));
    }
    public ObservableList<SitePages> getSitePages(){
        return sitePages;
    }
    public SitePages find(String navBar){
        for(int i = 0;i < sitePages.size();i++){
            if(sitePages.get(i).getNavBar().equals(navBar)){
                return sitePages.get(i);
            }
        }
        return null;
    }
}
