package Home;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import static Home.Main.story;

public class Storyboard {
    public Vector<Image> scenes = new Vector<>();

    public Vector<Image> objects = new Vector<>();

    public Vector<Location> locations = new Vector<>();

    public Storyboard(){
        try {
            //0 - DEFAULT DAY SKY
            URL url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/58378.jpg?raw=true");
            objects.add(SwingFXUtils.toFXImage(ImageIO.read(url), null));

            //1 - DEFAULT NIGHT SKY
            url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/Black_Box.png?raw=true");
            objects.add(SwingFXUtils.toFXImage(ImageIO.read(url), null));

            //2 - DEFAULT OUTSIDE BUILDING
            url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/clipart-house-mansion-13.png?raw=true");
            objects.add(SwingFXUtils.toFXImage(ImageIO.read(url), null));

            //3 - DEFAULT INSIDE HOUSE
            url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/pngtree-interior-architecture-home-interior-architecture-interior-renovation-image_13364.png?raw=true");
            objects.add(SwingFXUtils.toFXImage(ImageIO.read(url), null));

            //4 - DEFAULT CHARACTER
            url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/stick.png?raw=true");
            objects.add(SwingFXUtils.toFXImage(ImageIO.read(url), null));

            //5 - DEFAULT OUTSIDE OFFICE
            url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/271-2718498_building-png-office-building-clipart-png-transparent-png.png?raw=true");
            objects.add(SwingFXUtils.toFXImage(ImageIO.read(url), null));

            //6 - DEFAULT INSIDE OFFICE
            url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/be0cff7ae8fab70dfe0c8ecf546136cf.png?raw=true");
            objects.add(SwingFXUtils.toFXImage(ImageIO.read(url), null));

            //7 - DEFAULT OUTSIDE CITY
            url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/unnamed%20(2).png?raw=true");
            objects.add(SwingFXUtils.toFXImage(ImageIO.read(url), null));

            //8 - DEFAULT INSIDE CITY
            url = new URL("https://github.com/DeshaunB/JavaFX/blob/master/112712783-stock-vector-cartoon-empty-subway-train-card-poster-vector.png?raw=true");
            objects.add(SwingFXUtils.toFXImage(ImageIO.read(url), null));

        } catch (FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
