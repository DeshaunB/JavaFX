package Home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;

public class Profile {
    private static File profile;
    public static File fileLocation;
    private static String body;
    private static double userID;
    private static Vector<String> Scripts = new Vector<>();
    private static Vector<Script> ScriptList;
    private static String name;
    private static String email;



    public static String getName() { return name; }

    public static void setName(String name) { Profile.name = name; }

    public static String getEmail() { return email; }

    public static void setEmail(String email) { Profile.email = email; }

    public static File getFileLocation() { return fileLocation; }

    public static void setFileLocation(File fileLocation) { Profile.fileLocation = fileLocation; }


    public Profile(){
        userID = Integer.parseInt(new DecimalFormat("#").format(Math.random() * 100000));
        profile = null;
        Scripts = new Vector<String>();
    }
    private static void MkProfile(){
        profile = new File("swpfi-user" + ".swpi");
        body = "/ID#/SCRIPTWRITER PROFILE FOR USER NUMBER " + userID + "\n";
        fileLocation = new File("ScriptBackups");
        fileLocation.mkdir();
        System.out.println(userID);
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(profile, true)));
            writer.println(body);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void CreateProfile(){
        if(profile == null || !profile.exists()) {
            body = "";
            MkProfile();
        }else{
            profile.delete();
            body = "";
            MkProfile();
            for (String script:
                 Scripts) {
                AddScriptToProfile(script);
            }
        }
    }
    public static void AddScriptToProfile(String scriptName){
        if(!Scripts.contains(scriptName)) {
            Scripts.add(scriptName);
            body = "\n/S#/" + scriptName + "\n";
        }
    }
    public static void OpenProfile(){

        if (profile.exists()) {
            Scripts = new Vector<>();
            try {
                Scanner reader = new Scanner(profile);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    body += line;
                    if (line.contains("/ID#/")) {
                        if(userID != 0)
                            userID = Double.parseDouble(body.replaceAll("[^0-9]+", " ").substring(0,6));
                    }
                    if(line.contains("/S#/")){
                        File it = new File(line.substring(line.lastIndexOf("/") + 1));
                        if(it.exists())
                            Scripts.add(it.getName());
                    }
                    if(line.contains("/F#}")){
                        fileLocation = new File(line.substring(line.lastIndexOf("}") + 1));
                    }
                    if(line.contains("/NAME#/"))
                        name = line.substring(line.lastIndexOf("/") + 1);
                    if(line.contains("/EM#/"))
                        email = line.substring(line.lastIndexOf("/") + 1);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

    }
    public static String getScript(int index){return Scripts.get(index);}
    public static Vector<String> getScripts(){return Scripts;}
    private double GetID(){
        if (profile.exists() && body != null) {
            Scanner reader = null;
            try {
                reader = new Scanner(profile);
                while (reader.hasNextLine()) {
                    if (reader.nextLine().contains("/ID#/")) {
                        userID = Integer.parseInt(reader.nextLine().substring(reader.nextLine().lastIndexOf(' ' + 1), reader.nextLine().lastIndexOf('\n')));
                    }
                    reader.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        return userID;
    }
    public static boolean ProfileExists(){
        File testFile = new File("swpfi-user.swpi");
        if (testFile.exists()) {
            profile = testFile;
            try {
                Scanner reader = new Scanner(profile);
                while (reader.hasNextLine()) {
                    if(reader.nextLine().contains("/ID#/")){
                        return true;
                    }else{
                        return false;
                    }
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return false;
    }
    public static String OpenScript(String fileName){
        String text = "";
        File file = new File(fileName);
        if(file.exists()){
            //text = FIleHandling.ReadScriptFile(file, text);
            try {
                Scanner reader = new Scanner(file);
                while(reader.hasNextLine()){
                    text += reader.nextLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        return text;
    }

    public static Vector<Script> getLatestFiles(int count){
        Vector<Script> result = new Vector<>();
        if(Scripts != null && Scripts.size() > 0) {
            if (count < Scripts.size() - 1) {
                for (int i = Scripts.size() - 1; i > (Scripts.size() - 1) - count; i--) {
                    result.add(0, new Script(Scripts.get(i).substring(Scripts.get(i).lastIndexOf("//")+1), Scripts.get(i)));
                }
            } else if (count > Scripts.size()) {
                for (int i = 0; i < Scripts.size(); i++) {
                    result.add(0, new Script(Scripts.get(i).substring(Scripts.get(i).lastIndexOf("//")+1), Scripts.get(i)));
                }
            }
        }
        return result;
    }
    public static Vector<Script> getAllScripts(String textArea){
        Vector<Script> result = new Vector<>();
        if(Scripts != null) {
            for (int i = 0; i < Scripts.size(); i++) {
                result.add(0, new Script(Scripts.get(i).substring(Scripts.get(i).lastIndexOf("//")+1), Scripts.get(i)));
            }
        }
        return result;
    }
    public static void deleteScript(String fileName){
        System.out.println(Scripts.size());
        for (int i = 0; i <Scripts.size(); i++) {
                if(Scripts.get(i) == fileName){
                    Scripts.remove(i);
                }
            }

    }
}
