package Home;


import javafx.scene.control.ListView;
import javafx.scene.web.HTMLEditor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Locale;
import java.util.Vector;

public class ReadText {
    public static Vector<String> FindScenes(String body, Vector<String> scenes, Vector<Scene> Scenes){
        scenes = new Vector<>();
        Scenes = new Vector<>();
        Document scriptdoc = Jsoup.parse(body);
        Elements scriptWords = scriptdoc.select("p");
        Vector<String> scriptBody = new Vector<>();
        for (Element line:
             scriptWords) {
            scriptBody.add(line.html() + "\n");
        }
        int num = 0;
        for (int i = 0; i < scriptWords.size(); i++) {
            Element line = scriptWords.get(i);
            if(line.hasClass("scene") || line.text().contains("INT.") || line.text().contains("EXT.")){
                String dabody = "";
                scenes.add("Scene " + (num+1) + ": " + line.text().toUpperCase());
                num++;

            }

        }
        return scenes;
    }

    public static void FindAllLocations(Vector<String> scenes, Vector<String> locations, ListView<String> Locations) {
        int num = 0;
        Locations.getItems().add("LOCATIONS:");
        for (int i = 0; i < scenes.size(); i++) {
            if (!locations.contains(scenes.get(i).substring(14)) && !locations.contains(scenes.get(i).substring(14))) {
                if(scenes.get(i).substring(14).charAt(0) == ' ')
                    locations.add(scenes.get(i).substring(scenes.get(i).lastIndexOf("T."+1)));
                else
                    locations.add(scenes.get(i).substring(14));
                Locations.getItems().add(locations.get(num));
                num++;
            }
        }

    }
    public static Vector<String> FindAllCharacters(String body){
        Vector<String> scenes = new Vector<>();
        Document scriptdoc = Jsoup.parse(body);
        Elements scriptWords = scriptdoc.select("p");
        int num = 0;
        for (Element line: scriptWords) {
            if(line.hasClass("character") || ((line.hasAttr("style") && line.text().equals(line.text().toUpperCase())) && !line.text().contains("INT.") || !line.text().contains("EXT."))){
                scenes.add(line.text().toUpperCase());
                num++;
            }
        }
        return scenes;
    }
    public static Vector<String> FindAction(String body){
        Vector<String> scenes = new Vector<>();
        Document scriptdoc = Jsoup.parse(body);
        Elements scriptWords = scriptdoc.select("p");
        int num = 0;
        for (Element line: scriptWords) {
            if(line.hasClass("action") || (!line.hasAttr("style") && !line.text().equals(line.text().toUpperCase()) && !line.hasClass("dialogue"))){
                scenes.add(line.text().toUpperCase());
                num++;
            }
        }

        return scenes;
    }
    public static Vector<Scene> toSceneVec(String text, Vector<String> locations){
        Vector<Scene> Scenes = new Vector<>();
        Document doc = Jsoup.parse(text);
        Elements lines = doc.select("p");
        int s = -1;
        int c = -1;
        int id = 0;
        for (int i = 0; i < lines.size(); i++) {
            Element line = lines.get(i);
            String upper = line.text().toUpperCase();
            if(line.text().toUpperCase().contains("INT.") || line.text().toUpperCase().contains("EXT.")) {
                Scenes.add(new Scene(line.text().toUpperCase(), id));
                s++;
                c = -1;
                if(!Main.story.locations.isEmpty()){
                    Location loc = null;
                    for (Location location:
                         Main.story.locations) {
                        if(line.text().contains(location.getName()))
                            loc = location;
                    }
                    if(loc == null)
                        Main.story.locations.add(new Location(line.text().substring(5)));
                }else{
                    Main.story.locations.add(new Location(line.text().substring(5)));
                }
                continue;
            }
            if(line.hasClass("action") || ((!line.text().equals(upper)) || (line.attr("style").equals("text-align: left;")))) {
                Scenes.get(s).getActions().add(line.text());
                continue;
            }
            else if(((line.attr("style").equals("text-align: center;") || line.hasClass("character") || line.hasClass("dialogue")))) {

                if(line.text().equals(upper)) {
                    Scenes.get(s).getCharacters().add(new Character(line.text()));
                    c++;
                    i++;
                    if(i == lines.size())
                        break;
                    line = lines.get(i);

                    Scenes.get(s).getCharacters().get(c).getDialogueLines().add(line.text());

                }
            }
        }
        return Scenes;
    }

}

