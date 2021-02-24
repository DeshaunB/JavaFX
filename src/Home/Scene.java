package Home;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Scene {
    public Vector<String> getActions() {
        return actions;
    }

    public void setActions(Vector<String> actions) {
        this.actions = actions;
    }

    public Vector<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Vector<Character> characters) {
        this.characters = characters;
    }

    public String getSceneHeading() {
        return sceneHeading;
    }

    public void setSceneHeading(String sceneHeading) {
        this.sceneHeading = sceneHeading;
    }

    private Vector<String> actions;
    private Vector<Character> characters;
    private String sceneHeading;

    public int sceneID;

    public void setDayImage(Image dayImage) {
        this.dayImage = dayImage;
    }

    public void setNightImage(Image nightImage) {
        this.nightImage = nightImage;
    }



    public void setLocationImage(FileInputStream locationImage) {
        this.locationImage = locationImage;
    }

    private FileInputStream locationImage;
    private Image dayImage;
    private Image nightImage;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    private Location location;

    public Image getBgImage() {
        return bgImage;
    }

    public void setBgImage(Image bgImage) {
        this.bgImage = bgImage;
    }


    public Image getDayImage() {
        return dayImage;
    }

    public Image getNightImage() {
        return nightImage;
    }

    private Image bgImage;

    public Scene(String sceneHeading, int id) {
        sceneID = id;
        actions = new Vector<>();
        characters = new Vector<>();
        this.sceneHeading = sceneHeading;
        dayImage = Main.story.objects.get(0);
        nightImage = Main.story.objects.get(1);

        if(sceneHeading.contains("- DAY")){
            bgImage = dayImage;
        }else{
            bgImage = nightImage;
        }

        location = new Location(sceneHeading.substring(5));

    }

    public boolean equals(Scene s){
        this.actions.clear();
        this.actions.addAll(s.getActions());
        this.characters.clear();
        this.characters.addAll(s.getCharacters());
        this.dayImage = s.getDayImage();
        this.nightImage = s.getNightImage();
        this.setBgImage(s.getBgImage());
        this.location = s.getLocation();
        this.sceneHeading.equals(s.getSceneHeading());
        return true;
    }

}
