/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSG;
import CSG.Data.CSGData;
import CSG.File.CSGFile;
import CSG.Style.CSGStyle;
import CSG.Workspace.Workspace;
import djf.AppTemplate;
import java.util.Locale;
import static javafx.application.Application.launch;
/**
 *
 * @author Nick
 */
public class CSGApp extends AppTemplate{

    @Override
    public void buildAppComponentsHook() {
        dataComponent = new CSGData(this);
        
        workspaceComponent = new Workspace(this);
        fileComponent = new CSGFile(this);
        styleComponent = new CSGStyle(this);
        
    }
    public static void main(String [] args){
        Locale.setDefault(Locale.US);
	launch(args);
    }
}
