package Home;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Script {
    public String scriptTitle;
    public String fileLocation;
    public Button edit;
    public Button export;
    public Button delete;
    private Storyboard story;
    private String body;

    public Script(String title, String location){
        scriptTitle = title;

        fileLocation = location;

        edit = new Button("Edit");
        export = new Button("Export");
        delete = new Button("Delete");
        body = "";

        HandleButtons();
    }
    public void HandleButtons(){
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Profile.OpenScript(fileLocation);
                Main.page1 = false;
                AnchorPane pane = null;
                try {
                    Main.page1 = false;
                    pane = FXMLLoader.load(getClass().getResource("fxml/Editor.fxml"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
    public String getScriptTitle() {
        return scriptTitle;
    }

    public void setScriptTitle(String scriptTitle) {
        this.scriptTitle = scriptTitle;
    }

    public String getFileLocation() {return fileLocation;}

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public Button getExport() {
        return export;
    }

    public void setExport(Button export) {
        this.export = export;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
