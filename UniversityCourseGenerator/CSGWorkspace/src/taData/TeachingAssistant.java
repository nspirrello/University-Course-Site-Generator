package taData;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents a Teaching Assistant for the table of TAs.
 * 
 * @author Richard McKenna
 */
public class TeachingAssistant<E extends Comparable<E>> implements Comparable<E>  {
    // THE TABLE WILL STORE TA NAMES AND EMAILS
    private final StringProperty name;
    private final StringProperty email;
    private BooleanProperty undergrad = new SimpleBooleanProperty(false);

    /**
     * Constructor initializes both the TA name and email.
     */
    public TeachingAssistant(String initName, String initEmail, boolean undergrad) {
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);
        this.undergrad.setValue(undergrad);
        
    }

    TeachingAssistant(String initName, String initEmail) {
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);
    }
    
    public BooleanProperty undergradProperty() {
        return undergrad;
    }
    
    public void setUndergrad(boolean status){
        this.undergrad.set(status);
    }
    // ACCESSORS AND MUTATORS FOR THE PROPERTIES
    public boolean isUndergrad() {  
        return undergrad.get();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String initName) {
        name.set(initName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String initEmail) {
        email.set(initEmail);
    }

    @Override
    public int compareTo(E otherTA) {
        return getName().compareTo(((TeachingAssistant)otherTA).getName());
    }
    
    @Override
    public String toString() {
        return name.getValue();
    }
}