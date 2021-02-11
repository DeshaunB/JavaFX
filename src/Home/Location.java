package Home;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static Home.Main.story;

public class Location {
    public Image getINT() {
        return INT;
    }

    public void setINT(Image INT) {
        this.INT = INT;
    }

    public Image getEXT() {
        return EXT;
    }

    public void setEXT(Image EXT) {
        this.EXT = EXT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private Image INT;
    private Image EXT;
    public Location(String name){
        this.name = name;
        INT = story.objects.get(3);
        EXT = story.objects.get(2);
    }
}
