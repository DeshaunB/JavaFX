package Home;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.web.HTMLEditor;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.Timer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import static Home.Main.story;

public class Controller implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private Button viewTab;

    @FXML
    private Button newTab;

    @FXML
    private Button accSettings;

    @FXML
    private Button cornerX;

    @FXML
    private Button cornerMax;

    private ObservableList<Script> scripts;
    @FXML
    private TableColumn<Script, String> scriptNameColumn;

    @FXML
    private TableView<Script> table;

    @FXML
    private TableColumn<Script, String> scriptDateColumn;
    @FXML
    private VBox ScriptList;

    @FXML
    private TableColumn<Script, Button> scriptEditBtnColumn;
    @FXML
    private TableColumn<Script, Button> scriptExportBtnColumn;

    @FXML
    private TableColumn<Script, Button> deleteScriptBtnColumn;

    @FXML
    private AnchorPane editor;

    @FXML
    public SwingNode swingNode;

    @FXML
    private Button importBtn2;

    @FXML
    private Button saveBtn;

    @FXML
    private Button viewTab2;

    @FXML
    private Button exportBtn2;

    @FXML
    private TreeTableView<String> Scenes11;

    @FXML
    private TreeTableColumn<String, String> scenecol;

    @FXML
    private Button detailsBtn;

    @FXML
    private Button scriptinfosave;

    @FXML
    private TextField scriptTitleBox;

    @FXML
    private TextField scriptAuthorBox;

    @FXML
    private Button newScriptBtn1;

    @FXML
    private TabPane tabPane;

    @FXML
    private VBox scriptWriteBox;

    @FXML
    private HTMLEditor textPane1;

    @FXML
    private ImageView SkyImage;

    @FXML
    private ImageView LocationImage1;

    @FXML
    private ImageView CharacterSceneImage;

    @FXML
    private ImageView CharacterSceneImage1;

    @FXML
    private ImageView CharacterSceneImage2;
    @FXML
    private Label sceneTitle;
    @FXML
    private Label firstAction;

    @FXML
    private Label descTitle;


    @FXML
    private ImageView ObjectImage;

    @FXML
    private Button objectUpload;

    @FXML
    private Label objectFile;

    @FXML
    private ImageView locObjectImage;

    @FXML
    private ImageView logo;
    @FXML
    private Button locObjectUpload;
    @FXML
    private Button saveStory;

    @FXML
    private StackPane viewImages;

    @FXML
    private ImageView settingsImg;

    @FXML
    private ImageView minimizeImg;

    @FXML
    private ImageView xImg;

    @FXML
    private ImageView logo2;

    @FXML
    private Button saveBtn1;

    @FXML
    private ImageView scene1;
    @FXML
    private ImageView scene2;
    @FXML
    private ImageView scene3;
    @FXML
    private ImageView scene4;
    @FXML
    private ImageView scene5;
    @FXML
    private ImageView scene6;

    @FXML
    private Button saveStoryboard;

    @FXML
    private GridPane storyGrid;

    @FXML
    private Button back2script;

    @FXML
    private AnchorPane storyboardpane;

    @FXML
    private ImageView sceneprev1;

    @FXML
    private ImageView sceneprev2;
    @FXML
    private ImageView sceneprev3;
    @FXML
    private ImageView sceneprev4;
    @FXML
    private ImageView sceneprev5;
    @FXML
    private ImageView sceneprev6;

    @FXML
    private Button saveStory1;

    @FXML
    private GridPane storyGrid1;

    @FXML
    private Pane locoipane;
    @FXML
    private Pane skyoipane;
    @FXML
    Pane charoipane;

    @FXML
    private ImageView ObjectImage1;

    private String currentPage;
    private Vector<String> locations;
    private Vector<String> scenes;
    private Vector<String> characters;
    private Vector<String> dialog;
    private Vector<Scene> sceneVector;
    private String scriptTitle;


    public static String bodytext;
    public static boolean isMax = true;
    double xOffset = 0;
    double yOffset = 0;
    String workingFile = "";
    String head = "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            ".scene{text-transform:uppercase; text-align:left ;}\n" +
            ".action{text-align:left ;}\n" +
            ".character{text-transform:uppercase ; text-align:center ;}\n" +
            ".dialogue{text-align:center ;}\n" +
            ".parenthesis{text-align:center ;}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body contenteditable=\"true\">\n" +
            "</body\n" +
            "></html>";
    long start = System.currentTimeMillis();
    long time = 0;
    Scene currentScene;


    public void ClearImages(){
        SkyImage = new ImageView();
        LocationImage1 = new ImageView();
        CharacterSceneImage = new ImageView();
        CharacterSceneImage1 = new ImageView();
        CharacterSceneImage2 = new ImageView();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SetTable();
        if(textPane1 != null){
            if(!textPane1.getHtmlText().isEmpty()) {
                SetScenes();
                story = new Storyboard();
            }
            FileInputStream pic = null;
            try {

                url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/SuperScriptCircle.png?raw=true");
                logo2.setImage(SwingFXUtils.toFXImage(ImageIO.read(url), null));
            } catch (IOException e) {
                e.printStackTrace();
            }
            objectUpload.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    for (Scene scene:
                            sceneVector) {
                            FileChooser chooser = new FileChooser();
                            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                            chooser.getExtensionFilters().add(extFilter);
                            FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
                            chooser.getExtensionFilters().add(extFilter2);
                            chooser.setTitle("Select Image");
                            File chosen = chooser.showOpenDialog(importBtn2.getScene().getWindow());
                            try {
                                if(scene.getBgImage() == scene.getDayImage()) {
                                    scene.setDayImage(new Image(new FileInputStream(chosen.getAbsoluteFile())));
                                    scene.setBgImage(scene.getDayImage());
                                }
                                else if(scene.getBgImage() == scene.getNightImage()) {
                                    scene.setNightImage(new Image(new FileInputStream(chosen.getAbsoluteFile())));
                                    scene.setBgImage(scene.getNightImage());
                                }
                            } catch (FileNotFoundException fileNotFoundException) {
                                fileNotFoundException.printStackTrace();
                            }
                            SetScenes();
                            SkyImage.setImage(null);
                            Image im = scene.getBgImage();
                            SkyImage.setImage(im);
                            ObjectImage.setImage(im);
                            objectFile.setText(im.getUrl());
                            story.snap(skyoipane, 2);
                    }
                }
            });
            locObjectUpload.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    FileChooser chooser = new FileChooser();
                    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                    chooser.getExtensionFilters().add(extFilter);
                    chooser.setTitle("Select Image");
                    File chosen = chooser.showOpenDialog(importBtn2.getScene().getWindow());
                    Image im = null;
                    for (Scene scene:
                            sceneVector) {

                            SetScenes();
                            if(scene.getSceneHeading().contains("INT.")) {

                                LocationImage1.setImage(null);
                                try {
                                    story.objects.add(new Image(new FileInputStream(chosen.getAbsoluteFile())));
                                } catch (FileNotFoundException fileNotFoundException) {
                                    fileNotFoundException.printStackTrace();
                                }
                                scene.getLocation().setINT(story.objects.lastElement());
                                im = scene.getLocation().getINT();
                            }
                            else if(scene.getSceneHeading().contains("EXT.")) {

                                LocationImage1.setImage(null);
                                try {
                                    scene.getLocation().setEXT(new Image(new FileInputStream(chosen.getAbsoluteFile())));
                                } catch (FileNotFoundException fileNotFoundException) {
                                    fileNotFoundException.printStackTrace();
                                }

                                im = scene.getLocation().getEXT();
                                story.objects.add(im);

                            }}



                    LocationImage1.setImage(im);
                    locObjectImage.setImage(im);
                    }


                });
            saveStory.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    Image i = viewImages.snapshot(null, null);
                    try {
                        FileChooser chooser = new FileChooser();
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                        chooser.getExtensionFilters().add(extFilter);
                        chooser.setTitle("Select Image");
                        File chosen = chooser.showSaveDialog(saveStory.getScene().getWindow());
                        ImageIO.write(SwingFXUtils.fromFXImage(i, null), "png", new File(chosen.getAbsolutePath()));

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });


            saveStory1.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    Image i = story.snap(storyGrid1, 3);
                    try {
                        FileChooser chooser = new FileChooser();
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
                        chooser.getExtensionFilters().add(extFilter);
                        chooser.setTitle("Select Image");
                        File chosen = chooser.showSaveDialog(storyGrid1.getScene().getWindow());
                        ImageIO.write(SwingFXUtils.fromFXImage(i, null), "png", new File(chosen.getAbsolutePath()));

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });

            textPane1.setHtmlText(textPane1.getHtmlText() + bodytext);
            //scriptTitleBox.setText(scriptTitle);
            Scenes11.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    FileInputStream file = null;

                        //GET SELECTION
                        String item;
                        String selectedItem = Scenes11.getSelectionModel().getSelectedItem().getValue();
                    for (Scene scene:
                         sceneVector) {
                        if(scene.getSceneHeading().equals(selectedItem)) {
                            currentScene = scene;
                            break;
                        }

                    }
                    for(Character ch:
                            currentScene.getCharacters()){
                        if(ch.getCharacterName().equals(selectedItem)){
                            ObjectImage1.setImage(ch.getImageLocation());
                        }
                    }
                        if(selectedItem.contains("INT.") || selectedItem.contains("EXT.")) {
                            item = selectedItem;
                            sceneTitle.setText(item);
                            for (Scene scene:
                                 sceneVector) {

                                if(scene.getSceneHeading().equals(item) && scene.getBgImage() != null && currentScene.getSceneHeading().equals(item)) {
                                    SetScenes();
                                    //SKY
                                    SkyImage.setImage(null);
                                    LocationImage1.setImage(null);
                                    Image im = scene.getBgImage();
                                    SkyImage.setImage(im);
                                    ObjectImage.setImage(im);
                                    Image lim = null;
                                    //LOCATION
                                    if(scene.getSceneHeading().contains("INT")) {
                                        lim = scene.getLocation().getINT();
                                        story.objects.add(lim);

                                    }
                                    else if(scene.getSceneHeading().contains("EXT.")){
                                        lim = scene.getLocation().getEXT();
                                        story.objects.add(lim);
                                    }

                                    locObjectImage.setImage(lim);
                                    LocationImage1.setImage(lim);


                                    //CHARACTERS
                                    Image cim = null;
                                    if(scene.getCharacters().size() == 1){
                                        cim = scene.getCharacters().get(0).getImageLocation();
                                        CharacterSceneImage.setImage(cim);
                                    }else if(scene.getCharacters().size() == 2){
                                        for (int i = 0; i < scene.getCharacters().size(); i++) {
                                            cim = scene.getCharacters().get(i).getImageLocation();
                                            if(i == 0){
                                                CharacterSceneImage.setImage(cim);
                                            }else if(i == 1){
                                                CharacterSceneImage2.setImage(cim);
                                            }
                                        }
                                    }else if (scene.getCharacters().size() >= 3){
                                        for (int i = 0; i < scene.getCharacters().size(); i++) {
                                            cim = scene.getCharacters().get(i).getImageLocation();
                                            if(i == 0)
                                                CharacterSceneImage.setImage(cim);
                                            else if(i == 1)
                                                CharacterSceneImage1.setImage(cim);
                                            else if(i == 2)
                                                CharacterSceneImage2.setImage(cim);
                                            else{
                                                break;
                                            }
                                        }
                                    }



                                    currentScene.setSceneHeading(item);
                                    if(scene.getActions().contains(selectedItem))
                                        firstAction.setText(selectedItem);

                                    grabImage(viewImages);
                                    switch (story.scenes.size()){
                                        case 1:
                                            sceneprev1.setImage(story.scenes.get(0));
                                            break;
                                        case 2:
                                            sceneprev1.setImage(story.scenes.get(0));
                                            sceneprev2.setImage(story.scenes.get(1));
                                            break;
                                        case 3:
                                            sceneprev1.setImage(story.scenes.get(0));
                                            sceneprev2.setImage(story.scenes.get(1));
                                            sceneprev3.setImage(story.scenes.get(2));
                                            break;
                                        case 4:
                                            sceneprev1.setImage(story.scenes.get(0));
                                            sceneprev2.setImage(story.scenes.get(1));
                                            sceneprev3.setImage(story.scenes.get(2));
                                            sceneprev4.setImage(story.scenes.get(3));
                                            break;
                                        case 5:
                                            sceneprev1.setImage(story.scenes.get(0));
                                            sceneprev2.setImage(story.scenes.get(1));
                                            sceneprev3.setImage(story.scenes.get(2));
                                            sceneprev4.setImage(story.scenes.get(3));
                                            sceneprev5.setImage(story.scenes.get(4));
                                            break;
                                        case 6:
                                            sceneprev1.setImage(story.scenes.get(0));
                                            sceneprev2.setImage(story.scenes.get(1));
                                            sceneprev3.setImage(story.scenes.get(2));
                                            sceneprev4.setImage(story.scenes.get(3));
                                            sceneprev5.setImage(story.scenes.get(4));
                                            sceneprev6.setImage(story.scenes.get(5));
                                            break;
                                        default:
                                            break;
                                    }
                                }


                            }

                        }
                        int index = Scenes11.getSelectionModel().getSelectedIndex();


                }
            });

            textPane1.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {


                }

                private boolean isTabEvent(KeyEvent event)
                {
                    return event.getCode() == KeyCode.TAB;
                }
                private boolean isEnterEvent(KeyEvent event)
                {
                    return event.getCode() == KeyCode.ENTER;
                }


            });
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    //if(textPane1.getHtmlText().contains("INT.") || textPane1.getHtmlText().contains("EXT."))
                        //SetScenes();

                }
            }, 0, 1000);
        }else if(Main.page1){
            try {
                url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/SuperScriptCircle.png?raw=true");
                logo.setImage(SwingFXUtils.toFXImage(ImageIO.read(url), null));

                url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/settings.png?raw=true");
                settingsImg.setImage(SwingFXUtils.toFXImage(ImageIO.read(url), null));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(scene1 != null){

            back2script.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    currentPage = "Editor";
                    Main.page1 = false;


                    try {
                        AnchorPane pane = null;
                        pane = FXMLLoader.load(getClass().getResource("fxml/Editor2.fxml"));
                        storyboardpane.getChildren().setAll(pane);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            });
            switch (story.scenes.size()){
                case 1:
                    scene1.setImage(story.scenes.get(0));
                    break;
                case 2:
                    scene1.setImage(story.scenes.get(0));
                    scene2.setImage(story.scenes.get(1));
                    break;
                case 3:
                    scene1.setImage(story.scenes.get(0));
                    scene2.setImage(story.scenes.get(1));
                    scene3.setImage(story.scenes.get(2));
                    break;
                case 4:
                    scene1.setImage(story.scenes.get(0));
                    scene2.setImage(story.scenes.get(1));
                    scene3.setImage(story.scenes.get(2));
                    scene4.setImage(story.scenes.get(3));
                    break;
                case 5:
                    scene1.setImage(story.scenes.get(0));
                    scene2.setImage(story.scenes.get(1));
                    scene3.setImage(story.scenes.get(2));
                    scene4.setImage(story.scenes.get(3));
                    scene5.setImage(story.scenes.get(4));
                    break;
                case 6:
                    scene1.setImage(story.scenes.get(0));
                    scene2.setImage(story.scenes.get(1));
                    scene3.setImage(story.scenes.get(2));
                    scene4.setImage(story.scenes.get(3));
                    scene5.setImage(story.scenes.get(4));
                    scene6.setImage(story.scenes.get(5));
                    break;
                default:
                    break;
            }

        }



    }
    void grabImage(StackPane object){

        Image i = object.snapshot(null,null);
        story.scenes.add(i);
    }
     void playSound(String soundName){
        /*String musicFile;
        if(soundName == "button")
            musicFile = "button-16.mp3";
        else
            musicFile = "page-flip-03.mp3";
         Media sound = new Media(new File(musicFile).toURI().toString());
         MediaPlayer mediaPlayer = new MediaPlayer(sound);
         mediaPlayer.play();*/
    }
    public ObservableList<Script> getScripts(){

        scripts = FXCollections.observableArrayList();
        Vector<Script> script = Profile.getAllScripts(bodytext);
        for (Script file:
             script) {
            scripts.add(file);

            file.edit.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {

                    bodytext = Profile.OpenScript(file.getFileLocation() + ".swsf");
                    scriptTitle = file.getFileLocation();
                    Main.page1 = false;
                    AnchorPane pane = null;
                    try {
                        pane = FXMLLoader.load(getClass().getResource("fxml/Editor2.fxml"));
                        root.getChildren().setAll(pane);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    workingFile = file.getFileLocation();
                }
            });
            file.delete.setOnAction(e -> {
                File x = new File(file.getFileLocation());
                scripts = FXCollections.observableArrayList();
                table.getColumns().get(0).setVisible(false);
                table.getColumns().get(0).setVisible(true);
                FIleHandling.DeleteScript(x);
                Main.page1 = true;
                AnchorPane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                root.getChildren().setAll(pane);
            });
            file.export.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    if(scriptTitle == null){
                        TextInputDialog dialog = new TextInputDialog("ScriptName");
                        dialog.setTitle("No Script Title Given");
                        dialog.setHeaderText("Every Script Needs A Title :)");
                        dialog.setContentText("Please title your script: ");

                        // Traditional way to get the response value.
                        Optional<String> result = dialog.showAndWait();
                        if (result.isPresent()){
                            scriptTitle = result.get();
                        }
                    }
                    bodytext = FIleHandling.getTextFromFile(file.getFileLocation());
                    FIleHandling.SaveScriptFile(bodytext,".\\Scripts\\" + scriptTitle, true, false);
                }
            });
        }
        return scripts;
    }

    public ObservableList<Script> removeScript(int index){
        scripts.remove(index);
        Vector<Script> script = Profile.getAllScripts(bodytext);
        return scripts;
    }
    private void SetTable(){
        if(Main.page1 && Main.startPage == "Main") {
            scriptNameColumn.setCellValueFactory(new PropertyValueFactory<Script, String>("scriptTitle"));
            scriptEditBtnColumn.setCellValueFactory(new PropertyValueFactory<Script, Button>("edit"));
            scriptExportBtnColumn.setCellValueFactory(new PropertyValueFactory<Script, Button>("export"));
            deleteScriptBtnColumn.setCellValueFactory(new PropertyValueFactory<Script, Button>("delete"));
            table.setItems(getScripts());
        }
    }
    private void SetScenes() {

        sceneVector = ReadText.toSceneVec(textPane1.getHtmlText(), locations);

        TreeItem<String> root = new TreeItem<>("View Scenes");



        TreeItem<String> Character = null;
        TreeItem<String> Action = null;
        TreeItem<String> scene = null;
        for (int i = 0; i < sceneVector.size(); i++) {
            //SCENES
            Scene sc = sceneVector.get(i);
            scene = new TreeItem<>(sc.getSceneHeading());
            Character = new TreeItem<>("Characters");
            Action = new TreeItem<>("Actions");
            root.getChildren().add(scene);


            //CHARACTERS
            for (int j = 0; j < sceneVector.get(i).getCharacters().size(); j++) {

                Character ch = sceneVector.get(i).getCharacters().get(j);
                TreeItem<String> cha = new TreeItem<>(ch.getCharacterName());
                TreeItem<String> Dialogue = new TreeItem<>("Dialogue");
                Character.getChildren().add(cha);

                //DIALOGUE
                for (int k = 0; k < sceneVector.get(i).getCharacters().get(j).getDialogueLines().size(); k++) {
                    String dialogue = sceneVector.get(i).getCharacters().get(j).getDialogueLines().get(k);
                    TreeItem<String> dia = new TreeItem<>(dialogue);
                    Dialogue.getChildren().add(dia);
                }

                cha.getChildren().add(Dialogue);
            }

            //ACTIONS
            for (int j = 0; j < sceneVector.get(i).getCharacters().size(); j++) {
                if(j < sceneVector.get(i).getActions().size()) {
                    String action = sceneVector.get(i).getActions().get(j);
                    TreeItem<String> act = new TreeItem<>(action);
                    Action.getChildren().add(act);
                }
            }

            //LOCATION
            TreeItem<String> loc = new TreeItem<>(sc.getLocation().getName());
            TreeItem<String> Location = new TreeItem<>("Location");
            Location.getChildren().add(loc);

            scene.getChildren().add(Character);
            scene.getChildren().add(Action);
            scene.getChildren().add(Location);


        }

        scenecol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<String, String> stringStringCellDataFeatures) {
                return new SimpleStringProperty(stringStringCellDataFeatures.getValue().getValue());
            }
        });
        Scenes11.setRoot(root);
        Scenes11.setShowRoot(false);
    }
    private void SetLists() {
        SetScenes();

    }
    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if(event.getSource() == viewTab){
            //playSound("button");
            currentPage = "Main";
            Main.page1 = true;
            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
            root.getChildren().setAll(pane);

        }else if(event.getSource() == viewTab2){
            //playSound("button");
            currentPage = "Main";
            Main.page1 = true;
            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
            editor.getChildren().setAll(pane);

        }/*else if(event.getSource() == saveBtn1){
            playSound("button");
            currentPage = "StoryboardView";
            Main.page1 = false;

            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/StoryboardView.fxml"));
            editor.getChildren().setAll(pane);
        }*/
        else if(event.getSource() == newTab){

            //playSound("paper");
            currentPage = "Editor";
            story = new Storyboard();
            Main.page1 = false;
            bodytext = "";
            AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/Editor2.fxml"));
            root.getChildren().setAll(pane);

            if(Scenes11 != null) {

                //Scenes11.getItems().clear();


            }

        }
        else if(event.getSource() == objectUpload){


        }
        else if(event.getSource() == accSettings){

        }/*else if(event.getSource() == importBtn2){

            playSound("paper");
            FileChooser chooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");

            FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("SuperScript files (*.swsf)", "*.swsf");

            chooser.getExtensionFilters().add(extFilter);

            chooser.getExtensionFilters().add(extFilter2);

            chooser.setTitle("Import File");

            File chosen = chooser.showOpenDialog(importBtn2.getScene().getWindow());

            if(chosen.getName().contains("pdf"))
                FIleHandling.GetTextFromPDF(chosen);

            textPane1.setHtmlText(textPane1.getHtmlText() + CreateFile(chosen));

            workingFile = chosen.getName();

        }*/else if(event.getSource() == saveBtn){
            playSound("paper");
            scriptTitle = FIleHandling.Save(textPane1.getHtmlText(), "swsf");

        }else if(event.getSource() == exportBtn2){
            String fileName = scriptTitleBox.getText();
            Image i = story.snap(storyGrid1, 3);
            playSound("paper");
            FIleHandling.SaveScriptFile(textPane1.getHtmlText(), fileName, true,true);

            File folderDir = new File("Scripts\\" + fileName + "\\");
            if(!folderDir.exists())
                folderDir.mkdirs();
            ImageIO.write(SwingFXUtils.fromFXImage(i, null), "png", new File(folderDir.getAbsolutePath() + "\\" + fileName + "_storyboard.png"));
            File file = new File(folderDir.getAbsolutePath() + "\\" + "Image Objects");
            if(!file.exists())
                file.mkdir();

            int num = 0;
            for (Image object:
                    story.objects) {
                ImageIO.write(SwingFXUtils.fromFXImage(object, null), "png", new File(file + "\\" + "img" + num + ".png"));
                num++;
            }

            file = new File(folderDir.getAbsolutePath() + "\\" + "Individual Scenes");
            if(!file.exists())
                file.mkdir();

            num = 1;
            for(Image scene:
            story.scenes){
                ImageIO.write(SwingFXUtils.fromFXImage(scene, null), "png", new File(file + "\\" + "Scene" + num + ".png"));
                num++;
            }

            Desktop.getDesktop().open(file.getParentFile());

        }else if(event.getSource() == detailsBtn) {
            playSound("button");
            SetScenes();

        }else if(event.getSource() == scriptinfosave){
            playSound("button");
            scriptTitle = scriptTitleBox.getText();

        }else if(event.getSource() == newScriptBtn1){
            playSound("paper");
            textPane1.setHtmlText("");

            scriptTitleBox.setText("");

            scriptAuthorBox.setText("");

            story = new Storyboard();
            //Scenes11.getItems().clear();


        }else if(event.getSource() == cornerX){

            System.exit(0);

        }else if(event.getSource() == cornerMax){

            Stage stage = (Stage) root.getScene().getWindow();

            stage.setFullScreen(!isMax);

            isMax = !isMax;

        }else if(event.getSource() == importBtn2){
            playSound("paper");
            story = new Storyboard();
            DirectoryChooser chooser = new DirectoryChooser();

            chooser.setTitle("Import File");

            File defaultDirectory = new File("./");

            chooser.setInitialDirectory(defaultDirectory);

            File selectedDirectory = chooser.showDialog(importBtn2.getScene().getWindow());

            bodytext = Profile.OpenScript(selectedDirectory.getAbsolutePath() + "\\" + selectedDirectory.getName() + ".swsf");
            textPane1.setHtmlText(bodytext);

            Document scriptdoc = Jsoup.parse(bodytext);
            Elements scriptWords = scriptdoc.select("meta");

            scriptTitleBox.setText(scriptWords.get(0).attr("content"));

            scriptTitle = selectedDirectory.getName();
            for (File file:
                    Objects.requireNonNull(selectedDirectory.listFiles())) {
                if(file.getName().equals("Image Objects")){
                    for (File f:
                            Objects.requireNonNull(file.listFiles())) {
                        Image im = new Image(new FileInputStream(f));
                        story.objects.add(im);
                    }
                }else if(file.getName().equals("Individual Scenes")){
                    for (File f:
                            Objects.requireNonNull(file.listFiles())) {
                        Image im = new Image(new FileInputStream(f));
                        story.scenes.add(im);
                    }
                }

            }

            switch (story.scenes.size()){
                case 1:
                    sceneprev1.setImage(story.scenes.get(0));
                    break;
                case 2:
                    sceneprev1.setImage(story.scenes.get(0));
                    sceneprev2.setImage(story.scenes.get(1));
                    break;
                case 3:
                    sceneprev1.setImage(story.scenes.get(0));
                    sceneprev2.setImage(story.scenes.get(1));
                    sceneprev3.setImage(story.scenes.get(2));
                    break;
                case 4:
                    sceneprev1.setImage(story.scenes.get(0));
                    sceneprev2.setImage(story.scenes.get(1));
                    sceneprev3.setImage(story.scenes.get(2));
                    sceneprev4.setImage(story.scenes.get(3));
                    break;
                case 5:
                    sceneprev1.setImage(story.scenes.get(0));
                    sceneprev2.setImage(story.scenes.get(1));
                    sceneprev3.setImage(story.scenes.get(2));
                    sceneprev4.setImage(story.scenes.get(3));
                    sceneprev5.setImage(story.scenes.get(4));
                    break;
                default:
                    sceneprev1.setImage(story.scenes.get(0));
                    sceneprev2.setImage(story.scenes.get(1));
                    sceneprev3.setImage(story.scenes.get(2));
                    sceneprev4.setImage(story.scenes.get(3));
                    sceneprev5.setImage(story.scenes.get(4));
                    sceneprev6.setImage(story.scenes.get(5));
                    break;
            }

            workingFile = selectedDirectory.getAbsolutePath();
            SetScenes();
            SkyImage.setImage(sceneVector.get(0).getBgImage());
            if(sceneVector.get(0).getLocation().getName().contains("EXT."))
                LocationImage1.setImage(sceneVector.get(0).getLocation().getEXT());
            else
                LocationImage1.setImage(sceneVector.get(0).getLocation().getINT());

            switch(sceneVector.get(0).getCharacters().size()){
                case 1:
                    CharacterSceneImage.setImage(sceneVector.get(0).getCharacters().get(0).getImageLocation());
                    break;
                case 2:
                    CharacterSceneImage.setImage(sceneVector.get(0).getCharacters().get(0).getImageLocation());
                    CharacterSceneImage2.setImage(sceneVector.get(0).getCharacters().get(1).getImageLocation());
                    break;
                default:
                    CharacterSceneImage.setImage(sceneVector.get(0).getCharacters().get(0).getImageLocation());
                    CharacterSceneImage1.setImage(sceneVector.get(0).getCharacters().get(1).getImageLocation());
                    CharacterSceneImage2.setImage(sceneVector.get(0).getCharacters().get(2).getImageLocation());
                    break;
            }
            sceneTitle.setText(sceneVector.get(0).getSceneHeading());
            firstAction.setText(sceneVector.get(0).getActions().firstElement());

        }
    }
    private String CreateFile(File file){
        //Create a new file based on the directory selected & create a string for the text that will be saved.
        File scriptFile = file;
        String body = "";

        if(scriptFile.getName().contains(".pdf")) {

            PDDocument doc = null;
            PDFTextStripper stripper = null;
            try {
                doc = PDDocument.load(scriptFile);
                stripper = new PDFTextStripper();
                String[] text = new String[0];
                text = stripper.getText(doc).split("\n");


            for (int i = 0; i < text.length; i++) {
                    if(!text[i].isBlank()) {
                        if (text[i].contains("INT.") || text[i].contains("EXT.")) {
                            body += "<p class='scene'>" + text[i] + "</p>";
                            while (text[i + 1] != text[i + 1].toUpperCase(Locale.ROOT)) {
                                i++;
                                body += "<p class='action'>" + text[i] + "</p>";
                                if (text[i + 1] == text[i + 1].toUpperCase(Locale.ROOT)) {
                                    break;
                                }
                            }
                            continue;
                        }
                        if (text[i].equals(text[i].toUpperCase(Locale.ROOT)) && !text[i].contains("INT.")) {
                            body += "<p class='character'>" + text[i] + "</p>";
                            if(text[i+1].contains("(") || text[i+1].contains(")")) {
                                body += "<p class='parenthesis'>" + text[i+1] + "</p>";
                                i++;
                            }
                            body += "<p class='scene'>" + text[i+1] + "</p>";
                            i++;
                            continue;
                        }
                        if (text[i] != text[i].toUpperCase(Locale.ROOT)) {
                            body += "<p class='action'>" + text[i] + "</p>";
                            continue;
                        }
                    }

                doc.close();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
        {
            try {

                //Read the file line by line and add it to the body.
                Scanner reader = new Scanner(scriptFile);
                int loc = 0;
                while (reader.hasNextLine()) {
                    body += reader.nextLine();
                    body += "\n";
                }

                //Set the text of the TextArea in the app to be that of the text in the file.

                reader.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "File Not Found.");
                e.printStackTrace();
            }
        }
        return body;
    }
}
