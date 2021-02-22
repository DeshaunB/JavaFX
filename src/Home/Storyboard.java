package Home;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

import static Home.Main.story;

public class Storyboard {
    public Vector<Image> scenes = new Vector<>();

    public Vector<Image> objects = new Vector<>();

    public Vector<Location> locations = new Vector<>();

    public Storyboard(){
        try {
            objects.add(new Image(new FileInputStream("C:\\Users\\De'Shaun\\Desktop\\MidTerm\\JavaFX\\58378.jpg")));
            objects.add(new Image(new FileInputStream("C:\\Users\\De'Shaun\\Desktop\\MidTerm\\JavaFX\\Black_Box.png")));
            objects.add(new Image(new FileInputStream("C:\\Users\\De'Shaun\\Desktop\\MidTerm\\JavaFX\\clipart-house-mansion-13.png")));
            objects.add(new Image(new FileInputStream("C:\\Users\\De'Shaun\\Desktop\\MidTerm\\JavaFX\\pngtree-interior-architecture-home-interior-architecture-interior-renovation-image_13364.png")));
            objects.add(new Image(new FileInputStream("C:\\Users\\De'Shaun\\Desktop\\MidTerm\\JavaFX\\stick.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void Reconstruct(Image adding, int index){

            Vector<Image> temp = new Vector<>(scenes);
            scenes.clear();
            temp.remove(index);
            for (int i = 0; i < temp.size(); i++) {
                if(i == index)
                    scenes.add(adding);
                scenes.add(temp.get(i));
            }


    }
    public Image snap(Node canvas, int type){
        if(canvas != null){
            Image i = canvas.snapshot(null,null);
            if(type == 1) {
                scenes.add(i);
                return i;
            }
            else if(type == 2) {
                objects.add(i);
                return i;
            }
            else if(type == 3){
                return i;
            }
            else
                return null;
        }
        return null;
    }


}
