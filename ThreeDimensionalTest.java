import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;



public class ThreeDimensionalTest extends Application{
	
	private int time = 0;


	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	Group root = new Group();

    	Image diffuseMap = new Image(this.getClass().getResource("texture.jpg")
                        .toExternalForm());
        PhongMaterial mat = new PhongMaterial();
        mat.setDiffuseMap(diffuseMap);

        Sphere sphere = new Sphere(50);
        sphere.setMaterial(mat);

        root.getChildren().add(sphere);

        PointLight pointLight = new PointLight(Color.WHITE);
        pointLight.setTranslateX(0.0);
        pointLight.setTranslateY(1000.0);
        pointLight.setTranslateZ(-4000.0);

        root.getChildren().add(pointLight);



        PerspectiveCamera cam = new PerspectiveCamera(true);
        cam.setTranslateX(0);
        cam.setTranslateY(0);
        cam.setNearClip(0.1);
        cam.setFarClip(100000);
        cam.setTranslateZ(-1000);



    	final Scene scene = new Scene(root, 1000, 700, true, SceneAntialiasing.BALANCED);

    	scene.setCamera(cam);

    	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
                sphere.setRotate(time);
                cam.setTranslateZ(-1000+(time*5));
                time++;
            }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();



    	primaryStage.setScene(scene);
        primaryStage.show();

    }

}
