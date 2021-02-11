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
    private static String body;
    private static double userID;
    private static Vector<String> Scripts = new Vector<>();
    private static Vector<Script> ScriptList;
    private static String name;
    private static String email;
    private static String fileLocation;



    public static String getName() { return name; }

    public static void setName(String name) { Profile.name = name; }

    public static String getEmail() { return email; }

    public static void setEmail(String email) { Profile.email = email; }

    public static String getFileLocation() { return fileLocation; }

    public static void setFileLocation(String fileLocation) { Profile.fileLocation = fileLocation; }


    public Profile(){
        userID = Integer.parseInt(new DecimalFormat("#").format(Math.random() * 100000));
        profile = null;
        Scripts = new Vector<String>();
    }
    public static void CreateProfile(){
        if(profile == null || !profile.exists()) {
            body = "";
            profile = new File("swpfi-user" + ".swpi");
            body = "/ID#/SCRIPTWRITER PROFILE FOR USER NUMBER " + userID + "\n";
            System.out.println(userID);
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(profile, true)));
                writer.println(body);
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            profile.delete();
            body = "";
            profile = new File("swpfi-user" + ".swpi");
            body = "/ID#/SCRIPTWRITER PROFILE FOR USER NUMBER " + userID + "\n";
            System.out.println(userID);
            try {
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(profile, true)));
                writer.println(body);
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
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
                        Scripts.add(line.substring(line.lastIndexOf("/") + 1));
                    }
                    if(line.contains("/F#}")){
                        fileLocation = line.substring(line.lastIndexOf("}") + 1);
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

    public static String getLatestFiles(int count, boolean single){
        String result = "";
        if(Scripts.size() > 0) {
            if (count < Scripts.size() - 1 && !single) {
                for (int i = Scripts.size() - 1; i > (Scripts.size() - 1) - count; i--) {
                    result += Scripts.get(i) + "\n";
                }
            } else if (count > Scripts.size() && !single) {
                for (int i = Scripts.size() - 1; i > 0; i--) {
                    result += Scripts.get(i) + "\n";
                }
            } else if (count < Scripts.size() - 1 && single) {
                return Scripts.get(Scripts.size() - 1 - count);
            } else {
                return Scripts.get(Scripts.size() - 1);
            }
        }else
            return "EMPTY SLOT";
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
