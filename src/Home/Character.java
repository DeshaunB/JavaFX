package Home;

import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Character {
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public FileInputStream getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(FileInputStream imageLocation) {
        this.imageLocation = imageLocation;
    }

    private String characterName;

    public Vector<String> getDialogueLines() {
        return dialogueLines;
    }

    public void setDialogueLines(Vector<String> dialogueLines) {
        this.dialogueLines = dialogueLines;
    }

    private Vector<String> dialogueLines;
    private FileInputStream imageLocation;
    private String description;
    public Character(String characterName){
        this.characterName = characterName;
        dialogueLines = new Vector<>();
        try {
            this.imageLocation = new FileInputStream("stick.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Character talkingTo(){return this;}
    public void Draw(ImageView view){

    }

}
