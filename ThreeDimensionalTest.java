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
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class ThreeDimensionalTest extends Application{
	
	private int time = 0;

	boolean mouseDown = true;


	double xAxis, yAxis, storedX, storedY;

	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	Group root = new Group();
    	Group meshes = new Group();
    	meshes.setTranslateX(0.0);
        meshes.setTranslateY(0.0);

    	Image diffuseMap = new Image(this.getClass().getResource("texture.jpg")
                        .toExternalForm());
        PhongMaterial mat = new PhongMaterial();
        mat.setDiffuseMap(diffuseMap);

        Sphere sphere = new Sphere(50);
        sphere.setMaterial(mat);

        //root.getChildren().add(sphere);

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
	//Translate trans = new Translate(0, 0, 0.0);

try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(this.getClass().getResource("plane.fxml"));
           Group mesh = fxmlLoader.load();
        //     mesh.setScaleX(1.0);
            //mesh.setScaleY(1.0);
          // mesh.setScaleZ(1.0);
//           mesh.setMaterial(mat);
meshes.getTransforms().add(new Rotate(0, -10, 0, 0, Rotate.Y_AXIS));
           //mesh.getTransforms().addAll(trans);
           meshes.getChildren().add(mesh);


        }
        catch (IOException e) {
            e.printStackTrace();
        }

           root.getChildren().add(meshes);



    	final Scene scene = new Scene(root, 1000, 700, true, SceneAntialiasing.BALANCED);

    	scene.setCamera(cam);


    	scene.setOnMousePressed(event -> {
    		storedX = event.getSceneX();
            storedY = event.getSceneY();
    	});

    	scene.setOnMouseDragged(event -> {
            xAxis = event.getSceneX() - storedX;
            yAxis = event.getSceneY() - storedY;
            mouseDown = true;
        });

        scene.setOnMouseReleased(event -> {
            mouseDown = false;
        });



    	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
    			
    			if(mouseDown){

    			//root.setRotationAxis(new Point3D(0,0.1,0));
                root.setRotate(time);
                
                }

                System.out.println(yAxis);            
                time++;
            }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();



    	primaryStage.setScene(scene);
        primaryStage.show();

    }

}
