/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseDetails;

import javafx.beans.property.BooleanProperty;

/**
 *
 * @author Nick
 */
public class SitePages {
    String navBar;
    BooleanProperty used;
    String fileName;
    String script;
    public SitePages(BooleanProperty used,String navBar,String fileName,String script){
        this.navBar = navBar;
        this.used = used;
        this.fileName = fileName;
        this.script = script;
    }

    public String getNavBar() {
        return navBar;
    }

    public void setNavBar(String navBar) {
        this.navBar = navBar;
    }

    public BooleanProperty usedProperty() {
        return used;
    }
    
    public boolean isUsed(){
        return used.get();
    }

    public void setUsed(boolean used) {
        this.used.set(used);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
    
}
