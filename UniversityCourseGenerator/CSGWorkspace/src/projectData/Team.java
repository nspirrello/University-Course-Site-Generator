/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectData;

/**
 *
 * @author Nick
 */
public class Team{
    String name;
    String colorHex;
    String textColorHex;
    String link;
    public Team(String name,String colorHex,String textColorHex,String link){
        this.name = name;
        this.colorHex = colorHex;
        this.textColorHex = textColorHex;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getTextColorHex() {
        return textColorHex;
    }

    public void setTextColorHex(String textColorHex) {
        this.textColorHex = textColorHex;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
}
