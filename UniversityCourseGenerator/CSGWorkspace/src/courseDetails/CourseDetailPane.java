/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseDetails;
import CSG.CSGApp;
import CSG.Data.CSGData;
import CSG.File.CSGFile;
import CSG.Workspace.Workspace;
import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import properties_manager.PropertiesManager;

/**
 *
 * @author Nick
 */
public class CourseDetailPane extends Pane{
    VBox contentHolder;
    
    VBox courseInfo;
    HBox subjectContent;
    HBox semesterContent;
    HBox titleContent;
    HBox iNameContent;
    HBox iHomeContent;
    HBox exportContent;
    
    VBox siteTemplate;
    VBox content;
    VBox pageStyle;
    HBox bannerContent;
    HBox leftFooterContent;
    HBox rightFooterContent;
    HBox stylesheetContent;
    
    ScrollPane contentContainer;
    
    Label header1,subject,semester,title,instructorName,instructorHome,exportDir,exportToDir,number,year;
    ComboBox subjectBox,semesterBox,numberBox,yearBox;
    TextField titleField,iNameField,iHomeField;
    Button changeDir;
    
    Label header2,tempDes,tempSelected,pagesSelected;
    Button selectDir;
    TableView sitePages;
    TableColumn<SitePages, Boolean> used;
    TableColumn<SitePages,String> navbarTitle, fileName, script;
    
    Label header3, bannerImage, leftFImage, rightFImage, stylesheet, noteBottom;
    ImageView banner,leftFooter,rightFooter;
    ComboBox stylesheetBox;
    Button bannerChange,leftFooterChange,rightFooterChange;
    ArrayList<Node> boxes = new ArrayList();
    
    static File selectedDir;
    
    ObservableList<String> subjectList;
    ObservableList<String> numberList;
    ObservableList<String> semesterList;
    ObservableList<String> yearList;
    ObservableList<String> stylesheetList;
    static File exportDirectory;
    private File bannerFile;
    private File rightFootFile;
    private File leftFooterFile;
    
    public VBox getCourseInfoReal(){
        return courseInfo;
    }

    public Label getExportDir() {
        return exportDir;
    }
    
    public ComboBox getSubjectBox() {
        return subjectBox;
    }

    public ComboBox getSemesterBox() {
        return semesterBox;
    }

    public ComboBox getNumberBox() {
        return numberBox;
    }

    public ComboBox getYearBox() {
        return yearBox;
    }

    public TextField getTitleField() {
        return titleField;
    }

    public TextField getiNameField() {
        return iNameField;
    }

    public TextField getiHomeField() {
        return iHomeField;
    }

    public ImageView getBanner() {
        return banner;
    }

    public ImageView getLeftFooter() {
        return leftFooter;
    }

    public ImageView getRightFooter() {
        return rightFooter;
    }

    public ComboBox getStylesheetBox() {
        return stylesheetBox;
    }
    
    public void clearFields(){
        subjectBox.getSelectionModel().clearSelection();
        semesterBox.getSelectionModel().clearSelection();
        numberBox.getSelectionModel().clearSelection();
        yearBox.getSelectionModel().clearSelection();
        banner.setImage(null);
        leftFooter.setImage(null);
        rightFooter.setImage(null);
        stylesheetBox.getSelectionModel().clearSelection();
        titleField.clear();
        iNameField.clear();
        iHomeField.clear();
    }

    public Label getExportToDir() {
        return exportToDir;
    }

    public Label getTempSelected() {
        return tempSelected;
    }
    
    CSGApp app;
    public VBox getCourseInfo(){
        return contentHolder;
    }
    public CourseDetailPane(CSGApp app){
    this.app = app;
    PropertiesManager props = PropertiesManager.getPropertiesManager();    
    
    contentHolder = new VBox(40);
    contentHolder.setAlignment(Pos.CENTER);
     
    subjectList = FXCollections.observableArrayList();
    numberList = FXCollections.observableArrayList();
    semesterList = FXCollections.observableArrayList();
    yearList = FXCollections.observableArrayList();
    stylesheetList = FXCollections.observableArrayList();
    
    courseInfo = new VBox(20);
    subjectContent = new HBox(40);
    semesterContent = new HBox(40);
    titleContent = new HBox(40);
    iNameContent= new HBox(40);
    iHomeContent= new HBox(40);
    exportContent= new HBox(40);
    String header1Text = props.getProperty(CSG.CSGProp.COURSE_HEADER.toString());
    header1 = new Label(header1Text);
    String subjectText = props.getProperty(CSG.CSGProp.SUBJECT_TEXT.toString());
    subject = new Label(subjectText);
    String semesterText = props.getProperty(CSG.CSGProp.SEMESTER_TEXT.toString());
    semester = new Label(semesterText);
    String titleText = props.getProperty(CSG.CSGProp.TITLE_TEXT.toString());
    title = new Label(titleText);
    String iNameText = props.getProperty(CSG.CSGProp.INSTRUCTOR_NAME_TEXT.toString());
    instructorName = new Label(iNameText);
    String iHomeText = props.getProperty(CSG.CSGProp.INSTRUCTOR_HOME_TEXT.toString());
    instructorHome = new Label(iHomeText);
    String exportText = props.getProperty(CSG.CSGProp.EXPORT_DIR_TEXT.toString());
    exportDir = new Label(exportText);
    exportToDir = new Label();
    String numberText = props.getProperty(CSG.CSGProp.NUMBER_TEXT.toString());
    number = new Label(numberText);
    String yearText = props.getProperty(CSG.CSGProp.YEAR_TEXT.toString());
    subjectList = FXCollections.observableArrayList();
    semesterList = FXCollections.observableArrayList();
    numberList = FXCollections.observableArrayList();
    yearList = FXCollections.observableArrayList();
    stylesheetList = FXCollections.observableArrayList();
    year = new Label(yearText);
    subjectBox = new ComboBox();
    subjectBox.setItems(subjectList);
    semesterBox = new ComboBox();
    semesterBox.setItems(semesterList);
    numberBox = new ComboBox();
    numberBox.setItems(numberList);
    yearBox = new ComboBox();
    yearBox.setItems(yearList);
    
    subjectList.add("CSE");
    subjectList.add("ISE");
    subjectList.add("AMS");
    numberList.add("219");
    numberList.add("308");
    numberList.add("380");
    semesterList.add("Fall");
    semesterList.add("Spring");
    semesterList.add("Winter");
    semesterList.add("Summer");
    yearList.add("2017");
    yearList.add("2018");
    yearList.add("2019");
    
    titleField = new TextField();
    iNameField = new TextField();
    iHomeField = new TextField();
    String changeDirText = props.getProperty(CSG.CSGProp.DIR_BUTTON_TEXT.toString());
    changeDir = new Button(changeDirText);
    subjectContent.getChildren().addAll(subject,subjectBox,number,numberBox);
    semesterContent.getChildren().addAll(semester,semesterBox,year,yearBox);
    titleContent.getChildren().addAll(title,titleField);
    iNameContent.getChildren().addAll(instructorName,iNameField);
    iHomeContent.getChildren().addAll(instructorHome,iHomeField);
    exportContent.getChildren().addAll(exportDir,exportToDir,changeDir);
    courseInfo.getChildren().addAll(header1,subjectContent,semesterContent,titleContent,iNameContent,iHomeContent,exportContent);
    
    siteTemplate= new VBox(20);
    String header2Text = props.getProperty(CSG.CSGProp.SITE_HEADER.toString());
    header2 = new Label(header2Text);
    String tempDesText = props.getProperty(CSG.CSGProp.SITE_TEXT.toString());
    tempDes = new Label(tempDesText);
    tempSelected = new Label();
    String selectDirText = props.getProperty(CSG.CSGProp.SITE_TEMP_BUTTON.toString());
    selectDir = new Button(selectDirText);
    String pagesSelText = props.getProperty(CSG.CSGProp.SITE_PAGES_HEADER.toString());
    pagesSelected = new Label(pagesSelText);
    sitePages = new TableView();
    sitePages.setEditable(true);
    
    used = new TableColumn("Use");
    used.setCellValueFactory((TableColumn.CellDataFeatures<SitePages, Boolean> p) -> {
        return p.getValue().usedProperty();
    });  
    used.setCellFactory(CheckBoxTableCell.forTableColumn(used));
    used.setEditable(true);
    
    navbarTitle = new TableColumn("Navbar Title");
    navbarTitle.setCellValueFactory(new PropertyValueFactory<SitePages,String>("navBar"));
    fileName = new TableColumn("File Name");
    fileName.setCellValueFactory(new PropertyValueFactory<SitePages,String>("fileName"));
    script = new TableColumn("Script");
    script.setCellValueFactory(new PropertyValueFactory<SitePages,String>("script"));
    sitePages.getColumns().addAll(used,navbarTitle,fileName,script);
    CSGData data = (CSGData)app.getDataComponent();
    sitePages.setItems(data.getCourseData().getSitePages().getSitePages());
    
    siteTemplate.getChildren().addAll(header2,tempDes,tempSelected,selectDir,pagesSelected,sitePages);
    
    pageStyle= new VBox(20);
    bannerContent= new HBox(40);
    leftFooterContent= new HBox(40);
    rightFooterContent= new HBox(40);
    stylesheetContent= new HBox(40);
    String header3Text = props.getProperty(CSG.CSGProp.PAGE_STYLE_TEXT.toString());
    header3 = new Label(header3Text);
    String bannerImageText = props.getProperty(CSG.CSGProp.BANNER_IMAGE_TEXT.toString());
    bannerImage = new Label(bannerImageText);
    banner = new ImageView();
    String bannerChangeText = props.getProperty(CSG.CSGProp.BANNER_BUTTON_TEXT.toString());
    bannerChange = new Button(bannerChangeText);
    String leftFImageText = props.getProperty(CSG.CSGProp.LFOOTER_IMAGE_TEXT.toString());
    leftFImage = new Label(leftFImageText);
    leftFooter = new ImageView();
    String leftFChangeText = props.getProperty(CSG.CSGProp.LEFT_FOOT_BUTTON_TEXT.toString());
    leftFooterChange = new Button(leftFChangeText);
    String rightFImageText = props.getProperty(CSG.CSGProp.RFOOTER_IMAGE_TEXT.toString());
    rightFImage = new Label(rightFImageText);
    rightFooter = new ImageView();
    String rightFChangeText = props.getProperty(CSG.CSGProp.RIGHT_FOOT_BUTTON_TEXT.toString());
    rightFooterChange = new Button(rightFChangeText);
    String stylesheetText = props.getProperty(CSG.CSGProp.STYLESHEET_TEXT.toString());
    stylesheet = new Label(stylesheetText);
    stylesheetBox = new ComboBox(stylesheetList);
    String noteText = props.getProperty(CSG.CSGProp.NOTE_BOTTOM.toString());
    noteBottom = new Label(noteText);
    bannerContent.getChildren().addAll(bannerImage,banner,bannerChange);
    
    
   
    
    leftFooterContent.getChildren().addAll(leftFImage,leftFooter,leftFooterChange);
    rightFooterContent.getChildren().addAll(rightFImage,rightFooter,rightFooterChange);
    stylesheetContent.getChildren().addAll(stylesheet,stylesheetBox);
    pageStyle.getChildren().addAll(header3,bannerContent,leftFooterContent,rightFooterContent,stylesheetContent,noteBottom);
    content = new VBox();
    Workspace workspace = (Workspace)app.getWorkspaceComponent();

    HBox maybe = new HBox();
    contentContainer = new ScrollPane();
    contentContainer.setStyle("-fx-padding: 15");
    contentContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    contentContainer.setFitToWidth(true);
    contentHolder.getChildren().addAll(courseInfo,siteTemplate,pageStyle);
    maybe.getChildren().add(contentHolder);
    contentContainer.setContent(contentHolder);
    content.getChildren().addAll(contentContainer);
    
    stylesheetBox.setItems(stylesheetFill());
    
    
    
    selectDir.setOnAction(e -> {
        DirectoryChooser chooser = new DirectoryChooser();
        File defaultDirectory = new File("work");
        chooser.setInitialDirectory(defaultDirectory);
        selectedDir = chooser.showDialog(new Stage());
        if(selectedDir != null)
            tempSelected.setText(selectedDir.toString());
    });
    
    changeDir.setOnAction(e -> {
        DirectoryChooser chooser = new DirectoryChooser();
        exportDirectory = chooser.showDialog(new Stage());
        if(exportDirectory != null)
            exportToDir.setText(exportDirectory.toString());
    });
    
    bannerChange.setOnAction(e -> {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Png Files", "*.png"),
                new FileChooser.ExtensionFilter("JPEG Files", "*.jpeg"));
        bannerFile = chooser.showOpenDialog(new Stage());
        if(bannerFile != null){
            String pictureName = "";
            try {
                pictureName = bannerFile.toURI().toURL().toString();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            Image image = scale(new Image(pictureName),150,50,true);
            banner.setImage(image);
        }
    });
    
    rightFooterChange.setOnAction(e -> {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Png Files", "*.png"),
                new FileChooser.ExtensionFilter("JPEG Files", "*.jpeg"));
        rightFootFile = chooser.showOpenDialog(new Stage());
        if(rightFootFile != null){
            String pictureName = "";
            try {
                pictureName = rightFootFile.toURI().toURL().toString();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            Image image = scale(new Image(pictureName),150,50,true);
            rightFooter.setImage(image);
        }
    });
    
    leftFooterChange.setOnAction(e -> {
        
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Png Files", "*.png"),
                new FileChooser.ExtensionFilter("JPEG Files", "*.jpeg"));
        leftFooterFile = chooser.showOpenDialog(new Stage());
        if(leftFooterFile != null){
            String pictureName = "";
            try {
                pictureName = leftFooterFile.toURI().toURL().toString();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            Image image = scale(new Image(pictureName),150,50,true);
            leftFooter.setImage(image);
        }
    });
    
    }
    public Image scale(Image source, int targetWidth, int targetHeight, boolean preserveRatio) {
    ImageView imageView = new ImageView(source);
    imageView.setPreserveRatio(preserveRatio);
    imageView.setFitWidth(targetWidth);
    imageView.setFitHeight(targetHeight);
    return imageView.snapshot(null, null);
}
    
    public VBox getSiteTemplate() {
        return siteTemplate;
    }
    
    public ObservableList<String> stylesheetFill(){
        File directory = new File("work/css");
        File[] stylesheets =  directory.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".css");
            }
        });
        ObservableList cssFiles = FXCollections.observableArrayList();
        for(int i = 0;i < stylesheets.length;i++){
            String fileName = stylesheets[i].toString();
            String choppedFileName = fileName.substring(9);
            cssFiles.add(choppedFileName);
        }
        return cssFiles;
    }
    
    public VBox getContentHolder() {
        return contentHolder;
    }

    public HBox getSubjectContent() {
        return subjectContent;
    }

    public HBox getSemesterContent() {
        return semesterContent;
    }

    public HBox getTitleContent() {
        return titleContent;
    }

    public HBox getiNameContent() {
        return iNameContent;
    }

    public HBox getiHomeContent() {
        return iHomeContent;
    }

    public HBox getExportContent() {
        return exportContent;
    }

    public VBox getPageStyle() {
        return pageStyle;
    }

    public HBox getBannerContent() {
        return bannerContent;
    }

    public HBox getLeftFooterContent() {
        return leftFooterContent;
    }

    public HBox getRightFooterContent() {
        return rightFooterContent;
    }

    public HBox getStylesheetContent() {
        return stylesheetContent;
    }

    public ScrollPane getContentContainer() {
        return contentContainer;
    }
    public VBox getContent(){
        return content;
    }
    public void setImage(ImageView iv){
        
    }
    public void setCourseInfo(){
        CSGData data = (CSGData)app.getDataComponent();
        
        subjectList.add(data.getCourseData().getCourses().getSubject());
        subjectBox.setValue(data.getCourseData().getCourses().getSubject());
        semesterList.add(data.getCourseData().getCourses().getSemester());
        semesterBox.setValue(data.getCourseData().getCourses().getSemester());
        numberList.add(data.getCourseData().getCourses().getNumber());
        numberBox.setValue(data.getCourseData().getCourses().getNumber());
        yearList.add(data.getCourseData().getCourses().getYear());
        yearBox.setValue(data.getCourseData().getCourses().getYear());
        titleField.setText(data.getCourseData().getCourses().getTitle());
        iNameField.setText(data.getCourseData().getCourses().getiName());
        iHomeField.setText(data.getCourseData().getCourses().getiHome());
        exportToDir.setText(data.getCourseData().getCourses().getExportDir());
        tempSelected.setText(data.getCourseData().getCourses().getSiteTemplate());
        
        Image bannerImg = new Image("file:"+data.getCourseData().getCourses().getBannerImage());
        this.banner.setImage(bannerImg);
        Image leftFImg = new Image("file:"+data.getCourseData().getCourses().getLeftFooterImage());
        this.leftFooter.setImage(leftFImg);
        this.rightFooter.setImage(new Image("file:"+data.getCourseData().getCourses().getLeftFooterImage()));
        
        
        
        stylesheetList.add(data.getCourseData().getCourses().getStylesheet());
        stylesheetBox.setValue(data.getCourseData().getCourses().getStylesheet());
    }
}
