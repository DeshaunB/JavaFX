package Home;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;


public class FIleHandling {
    public static boolean createFileChooser() throws IOException {
        return true;
    }
    public static String Save(String text,String fileType) throws IOException {
        String fileName = null;
        //Create A New Frame for the base (Will be used later to show the file chooser save dialog)
        JFrame frame = new JFrame();

        //Create the File Chooser & Set the title
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File As");

        //Record the user's button selection
        int userSelection = fileChooser.showSaveDialog(frame);

        //If the user pushed "Confirm" and not "Cancel", run this code"
        if (userSelection == JFileChooser.APPROVE_OPTION) {

            //Record the location/File the user wants to save to
            File fileToSave = fileChooser.getSelectedFile();

            //Set the file's name to be the name & location specified by the user.
            fileName = fileToSave.getAbsolutePath();
            if(fileType != "pdf") {
                //Create a new file
                File script = new File(fileName);
                if(script.exists())
                    script.delete();
                SaveScriptFile(text, fileName, false, true);
                //Create a writer to write the text of the file.
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(script, true)));
                writer.println(text);
                writer.close();
            }else{
                SaveScriptFile(text, fileName, true, true);
            }
            frame.setTitle(fileName);




        }
        return fileName;
    }
    public static void SaveScriptFile(String text, String filename, boolean isPDFToo, boolean addToProfile){

            //Set the file's name to be the name & location specified by the user.
            File directory = new File("Scripts" + File.separator + filename + File.separator);
            if(!directory.exists())
                directory.mkdirs();

            String fileName = isPDFToo ? filename : filename;

            //Create New File
            File script = new File(directory + File.separator + fileName + ".swsf");

            System.out.println("SCRIPT:" + script.getAbsolutePath());

            Document scriptdoc = Jsoup.parse(text);
            Elements scriptWords = scriptdoc.select("p");
            String[] scriptBody = new String[scriptWords.size()];

            String fileBody = "";


        int num = 0;
        scriptdoc.head().appendElement("meta").attr("name","title").attr("content", fileName);
        for (Image scene:
             Main.story.objects) {
            scriptdoc.head().appendElement("meta").attr("name","story").attr("content","Image Objects" + File.separator + "img" + num + ".png");
            num++;
        }
            if(scriptBody.length > 0) {
                for (int i = 0; i < scriptBody.length; i++) {
                    if(!scriptWords.isEmpty()) {
                        if (scriptWords.get(i).text().toUpperCase(Locale.ROOT).contains("INT.") || scriptWords.get(i).text().toUpperCase(Locale.ROOT).contains("EXT.")) {
                            if(!scriptWords.get(i).hasClass("scene"))
                                scriptWords.get(i).addClass("scene");
                            scriptBody[i] = scriptWords.get(i).text();
                        }
                        else if (scriptWords.get(i).hasAttr("style") && scriptWords.get(i).attr("style").equals("text-align: center;")) {
                            if(!scriptWords.get(i).hasClass("character"))
                                scriptWords.get(i).addClass("character");
                            scriptBody[i] = scriptWords.get(i).text();
                                if(scriptWords.get(i+1).text().contains("(") || scriptWords.get(i+1).text().contains(")")) {
                                    i++;
                                    if(!scriptWords.get(i).hasClass("scene"))
                                        scriptWords.get(i).addClass("parenthesis");
                                    scriptBody[i] = scriptWords.get(i).text();
                                }
                            if(!scriptWords.get(i+1).hasClass("dialogue"))
                                scriptWords.get(i+1).addClass("dialogue");
                            scriptBody[i+1] = scriptWords.get(i).text();
                            i++;
                        }
                        else if(scriptWords.get(i).text().isEmpty() || scriptWords.get(i).text().isBlank()){
                            scriptWords.get(i).removeAttr("class");
                            scriptWords.get(i).removeAttr("style");
                            scriptBody[i] = scriptWords.get(i).text();
                        }
                        else {
                            if(!scriptWords.get(i).hasClass("action"))
                                scriptWords.get(i).addClass("action");
                            scriptBody[i] = scriptWords.get(i).text();
                        }

                    }


                }
                fileBody = scriptdoc.html();
                if(isPDFToo) {

                    CreatePDFFile(directory.getName() + File.separator + fileName + ".pdf", fileBody);
                }


            }

            //Create a writer to write the text of the file.
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(new BufferedWriter(new FileWriter(script, true)));
                writer.println(fileBody);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(!Profile.getScripts().contains(fileName) && addToProfile)
                Profile.AddScriptToProfile(fileName);

            System.out.println("Method End: " + script.getAbsolutePath());
    }
    public static String getTextFromFile(String filename){
        File file = new File(filename);
        String result = "";
        if(file.exists()){
            try {
                Scanner reader = new Scanner(file);
                while(reader.hasNextLine()){
                    result += reader.nextLine() + "\n";
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public static String ReadScriptFile(File file, String textPaneText){
        Vector<String> contents = new Vector<>();
        int num = 0;

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                contents.add(reader.nextLine() + "\n");
                textPaneText += contents.get(num);

                num++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return textPaneText;
    }
    public static void CreatePDFFile(String fileName, String fileContent){
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        File file = new File(fileName);
        Document html = Jsoup.parse(fileContent);
        Elements elements = html.select("p");




        try {
            PDPageContentStream content = new PDPageContentStream(doc, page);


            int marginSize = 720;
            int fontSize = 12;
            for (int i = 0; i < elements.size(); i++) {
                content.beginText();
                if(marginSize <= 90 || marginSize <= 110 && elements.get(i).hasClass("character")){
                    content.close();
                    marginSize = 720;
                    page = new PDPage();
                    doc.addPage(page);
                    content = new PDPageContentStream(doc,page);
                    content.beginText();
                }

                PDFont font = PDType1Font.COURIER_BOLD;
                float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * 12;
                content.setFont(font, 12);
                content.newLineAtOffset(40, marginSize);
                marginSize += fontSize - 36;

                if(elements.get(i).hasClass("character")){
                    content.newLineAtOffset(230, -24);
                }else if(elements.get(i).hasClass("dialogue")){
                    content.newLineAtOffset(159, -24);
                }else{
                    content.newLineAtOffset(50, -24);
                }


                content.showText(elements.get(i).text());
                content.newLine();

                content.endText();
            }


            content.close();

            doc.save("Scripts" + File.separator + fileName);
            doc.close();
            System.out.println(doc.getDocumentInformation());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static float CenterPDFText(String text, int fontSize, PDFont font, PDPage page) throws IOException{
        float titleWidth = font.getStringWidth(text) / 1000 * fontSize;
        return (page.getMediaBox().getWidth() - titleWidth) / 2;
    }
    public static String GetTextFromPDF(File file) throws IOException {

        PDDocument doc = PDDocument.load(file);
        String yo = new PDFTextStripper().getText(doc);
        doc.close();
        return yo;
    }
    public static void DeleteScript(File script){
        if(script.exists()) {
            script.delete();
        }
        Profile.deleteScript(script.getName());
    }

}
