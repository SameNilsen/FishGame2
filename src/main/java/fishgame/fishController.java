package fishgame;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

public class fishController implements Initializable {

    @FXML
    Button button;

    @FXML
    AnchorPane anchorPane;

    @FXML
    AnchorPane greatAnchorPane;

    @FXML
    HBox hboks;

    @FXML
    ScrollPane  scrollPane;

    @FXML
    Label mousePos;

    private ImageView imageview = new ImageView();
    private Image image = new Image(getClass().getResourceAsStream("res/base22.png"));

    private ImageView fisherman = new ImageView();
    private Image imageFisherman = new Image(getClass().getResourceAsStream("res/fisherman1.png"));

    Rotate rotation = new Rotate();
    Scale scale = new Scale();
    Scale fishermanScale = new Scale();
    
    private double totalTime = 0;
    private long startTime = System.nanoTime();
    Circle dupp = new Circle(915, 200, 2, Color.ORANGE);
    int duppAngle = 30;
    int duppSpeed = 50;
    Line lina = new Line();
    Circle circle = new Circle(2);

    boolean isZoom = false;
    Media sound = new Media(new File("src/main/java/fishgame/res/throw.mp3").toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    Rectangle powerRect = new Rectangle(50, 23, Color.RED);
    Rectangle backgroundRect = new Rectangle(150, 200, Color.LIGHTBLUE);
    Rectangle outlineRect = new Rectangle(120, 25, Color.TRANSPARENT);
    Label powerLabel = new Label("Power");
    StackPane stackPane = new StackPane();

    Rectangle angleRect = new Rectangle(50, 23, Color.RED);
    Rectangle angleBackgroundRect = new Rectangle(150, 200, Color.LIGHTBLUE);
    Rectangle angleOutlineRect = new Rectangle(120, 25, Color.TRANSPARENT);
    Label angleLabel = new Label("Angle");
    StackPane angleStackPane = new StackPane();

    List<Circle> pointsList = new ArrayList<Circle>();
    boolean indicatorShow = false;

    // List<Ellipse> fishShadowList = new ArrayList<Ellipse>();
    List<fisk> fishShadowList = new ArrayList<fisk>();
    double a;
    double b;
    fisk fishOnHook = null;
    boolean duppUte = false;
    boolean fishPull = false;
    boolean fishDragUp = false;

    GridPane fishDisplayPane = new GridPane();
    Rectangle fishDisplayRect = new Rectangle(200, 200, Color.LIGHTBLUE);
    ScrollPane fishDisplayScroll = new ScrollPane();
    boolean fishDisplayShow = false;
    int availibleSlot = 1;

    private ImageView yellowFish = new ImageView();
    private Image yellowFishImage = new Image(getClass().getResourceAsStream("res/yellowFish.png"));
    Rotate fishRotation = new Rotate();

    Circle testDot = new Circle(2);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(23124321);

        // AnchorPane anchorpane = new AnchorPane();
        // anchorpane.
        // greatAnchorPane.getChildren().addAll(new Group(anchorPane));

        Image img = new Image(getClass().getResourceAsStream("res/bakgrunn3.png"));
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true, true, false, false));
        // BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(0, 0, false, false, true, false));
        Background bGround = new Background(bImg);
        anchorPane.setBackground(bGround);
        // anchorPane.setStyle("-fx-background-color: #3f48cc");
        // anchorPane.setStyle("-fx-background-image: url(bakgrunn1.png)");

        // hboks.setStyle("-fx-background-color: #4FD1EB");
        // hboks.setOpacity(0.4);

        // powerRect.setStyle("-fx-fill: red; -fx-stroke: black; -fx-stroke-width: 5;");
        // HBox.setMargin(powerRect, new Insets(0, 0, 0, 50));
        // hboks.getChildren().add(powerRect);
        // Line line = new Line(0, 0, 0, 60);
        // line.setFill(Color.BLACK);
        // hboks.getChildren().add(line);

        greatAnchorPane.getChildren().add(stackPane);
        stackPane.setLayoutX(300);
        stackPane.setLayoutY(300);
        stackPane.getChildren().add(powerRect);
        stackPane.getChildren().add(backgroundRect);
        stackPane.getChildren().add(powerLabel);
        stackPane.getChildren().add(outlineRect);
        outlineRect.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
        StackPane.setAlignment(powerLabel, Pos.BOTTOM_CENTER);
        backgroundRect.setOpacity(0.3);
        powerRect.toFront();

        greatAnchorPane.getChildren().add(angleStackPane);
        angleStackPane.setLayoutX(20);
        angleStackPane.setLayoutY(300);
        angleStackPane.getChildren().add(angleRect);
        angleStackPane.getChildren().add(angleBackgroundRect);
        angleStackPane.getChildren().add(angleLabel);
        angleStackPane.getChildren().add(angleOutlineRect);
        angleOutlineRect.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
        StackPane.setAlignment(angleLabel, Pos.BOTTOM_CENTER);
        angleBackgroundRect.setOpacity(0.3);
        angleRect.toFront();
        
        fisherman.setImage(imageFisherman);
        anchorPane.getChildren().add(fisherman);
        fisherman.setPreserveRatio(true);
        fisherman.setFitHeight(100);

        imageview.setImage(image);
        anchorPane.getChildren().add(imageview);
        imageview.setPreserveRatio(true);
        imageview.toFront();
        // imageview.setFitWidth(100);
        imageview.setFitHeight(70);
        // System.out.println("..." + anchorPane.getWidth());
        imageview.setX(anchorPane.getWidth() - anchorPane.getWidth()/15);
        imageview.setY(anchorPane.getHeight()/2.8);


        // Circle circle = new Circle(2);
        circle.setFill(Color.RED);
        circle.setCenterX(imageview.getX() + 30);
        circle.setCenterY(imageview.getY() + imageview.getFitHeight());
        anchorPane.getChildren().add(circle);
        
        rotation.setPivotX(circle.getCenterX());//Set the Pivot's X to be the same location as the Circle's X. This is only used to help you see the Pivot's point
        rotation.setPivotY(circle.getCenterY());//Set the Pivot's Y to be the same location as the Circle's Y. This is only used to help you see the Pivot's point
        imageview.getTransforms().add(rotation);

        scale.setPivotX(985);
        scale.setPivotY(300);
        anchorPane.getTransforms().add(scale);

        anchorPane.getChildren().add(dupp);
        dupp.setCenterX(930);
        dupp.setCenterY(190);
        anchorPane.getChildren().add(lina);
        lina.setStartX(930);
        lina.setStartY(190);
        lina.setEndX(930);
        lina.setEndY(190);
        alwaysOnTimer.start();
        fishShadowTimer.start();

        greatAnchorPane.getChildren().add(fishDisplayScroll);
        fishDisplayScroll.setContent(fishDisplayPane);
        // fishDisplayPane.getChildren().add(fishDisplayRect);
        fishDisplayPane.setStyle("-fx-background-color: #4FD1EB");
        fishDisplayScroll.setMaxHeight(300);
        fishDisplayScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        fishDisplayScroll.setVbarPolicy(ScrollBarPolicy.NEVER);
        // fishDisplayScroll.setMaxWidth(300);
        // fishDisplayPane.setHgap(10);
        // fishDisplayPane.setVgap(10);

        yellowFish.setImage(yellowFishImage);
        yellowFish.setPreserveRatio(true);
        yellowFish.setFitHeight(15);
        yellowFish.getTransforms().add(fishRotation);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                Label l = new Label(String.valueOf(1+(j*3)+i));
                Rectangle r = new Rectangle(100, 50, Color.TRANSPARENT);
                r.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
                fishDisplayPane.add(l, i, j);
                fishDisplayPane.add(r, i, j);
                GridPane.setHalignment(l, HPos.CENTER);
            }
            
        }

        fishDisplayScroll.setVisible(false);

        anchorPane.getChildren().add(testDot);

        // AnchorPane.setRightAnchor(anchorPane, 10.0);

        anchorPane.widthProperty().addListener((obs, oldVal, newVal) -> {   //  Heller animationTimer?
            // System.out.println(anchorPane.getWidth());
            // imageview.setX(anchorPane.getWidth() - anchorPane.getWidth()/10);
            // imageview.setY(anchorPane.getHeight()/2);
            // System.out.println(circle.getCenterX() + ", :) " + circle.getCenterY());
            // rotation.setPivotX(circle.getCenterX());//Set the Pivot's X to be the same location as the Circle's X. This is only used to help you see the Pivot's point
            // rotation.setPivotY(circle.getCenterY());//Set the Pivot's Y to be the same location as the Circle's Y. This is only used to help you see the Pivot's point
            // imageview.getTransforms().add(rotation);
            anchorPane.setScaleX(1);
            anchorPane.setScaleY(1);
            System.out.println(1 + " " + anchorPane.getTranslateX());
            anchorPane.setTranslateX(0);
            System.out.println(1 + " " + anchorPane.getTranslateX());
       });
        // Stage thisStage = (Stage) anchorPane.getScene().getWindow();
        // thisStage.isFullScreen();
    }

    AnimationTimer alwaysOnTimer = new AnimationTimer()
    {    
            public void handle(long currentNanoTime)
            {   
                hboks.toFront();
                imageview.setX(anchorPane.getWidth() - anchorPane.getWidth()/15);
                imageview.setY(anchorPane.getHeight()/3.2);
                fisherman.setX(imageview.getX()-45);
                fisherman.setY(imageview.getY()+10);
                // System.out.println(imageview.getFitWidth() + " " + imageview.getFitHeight());
                circle.setCenterX(imageview.getX() + 30);
                circle.setCenterY(imageview.getY() + imageview.getFitHeight());
                lina.setStartX(imageview.getX()+10);
                lina.setStartY(imageview.getY()+10);
                lina.setEndX(dupp.getCenterX());
                lina.setEndY(dupp.getCenterY());
                rotation.setPivotX(circle.getCenterX());
                rotation.setPivotY(circle.getCenterY());
                scale.setPivotX(anchorPane.getWidth());
                scale.setPivotY(anchorPane.getHeight()/3);
                a = anchorPane.getWidth()*0.425;
                b = anchorPane.getHeight()*0.375;
                fishRotation.setPivotX(dupp.getCenterX());
                fishRotation.setPivotY(dupp.getCenterY());
            }  
    };

    AnimationTimer duppTimer = new AnimationTimer()
    {    
            public void handle(long currentNanoTime)
            {   
                // System.out.println(totalTime);
                if (totalTime < 10){
                    totalTime = (currentNanoTime - startTime)*Math.pow(10, -9)*4;
                    double duppX = imageview.getX() + (duppSpeed * Math.cos(Math.toRadians(duppAngle)) * totalTime * -1);
                    double duppY = imageview.getY()+((duppSpeed * Math.sin(Math.toRadians(duppAngle)) * totalTime) - (0.5 * 9.81 * Math.pow(totalTime, 2)))*-1;
                    dupp.setCenterX(duppX);
                    dupp.setCenterY(duppY);
                    lina.setEndX(dupp.getCenterX());
                    lina.setEndY(dupp.getCenterY());
                }
                else{
                    duppUte = true;
                    duppTimer.stop();
                } 
                // System.out.println(totalTime);
                // if (imageview.getY() < 500)
                // imageview.setY(imageview.getY() + (0.5 * 9.8 * Math.pow(totalTime, 2)));
            }  
    };

    AnimationTimer zoomInTimer = new AnimationTimer()
    {    
            public void handle(long currentNanoTime)
            {   
                double scaleFactor = 0.01;
                anchorPane.setScaleX(anchorPane.getScaleX()+scaleFactor);
                anchorPane.setScaleY(anchorPane.getScaleY()+scaleFactor);
                // System.out.println(anchorPane.getLayoutX());
                anchorPane.setTranslateX(anchorPane.getTranslateX()-anchorPane.getWidth()*0.001);
                // anchorPane.setLayoutX(-50);
                
            }  
    };

    AnimationTimer zoomOutTimer = new AnimationTimer()
    {    
            public void handle(long currentNanoTime)
            {   
                double scaleFactor = 0.01;
                if (anchorPane.getScaleX() >= 1){
                    anchorPane.setScaleX(anchorPane.getScaleX()-scaleFactor);
                    anchorPane.setScaleY(anchorPane.getScaleY()-scaleFactor);
                    // System.out.println("oo" + anchorPane.getLayoutX());
                    // anchorPane.setLayoutX(anchorPane.getLayoutX()+5);
                    anchorPane.setTranslateX(anchorPane.getTranslateX()+5);
                }
            }  
    };

    AnimationTimer powerBarTimer = new AnimationTimer()
    {    
        boolean increasePower = true;
        int power = 50;
        double change = 1;
            public void handle(long currentNanoTime)
            {   
                if (increasePower){
                    powerRect.setWidth(powerRect.getWidth()+change);
                    power++;
                }
                else if (increasePower == false){
                    powerRect.setWidth(powerRect.getWidth()-change);
                    power--;
                }
                if (powerRect.getWidth() > 118){
                    increasePower = false;
                }
                else if (powerRect.getWidth() < 5){
                    increasePower = true;
                }
                powerLabel.setText(String.valueOf(power));
                duppSpeed = power;
                if (indicatorShow){
                    anchorPane.getChildren().removeAll(pointsList);
                    indicator();
                }
            }  
    };

    AnimationTimer angleBarTimer = new AnimationTimer()
    {    
        boolean increaseAngle = true;
        int angle = 50;
        double change = 1;
            public void handle(long currentNanoTime)
            {   
                if (increaseAngle){
                    angleRect.setWidth(angleRect.getWidth()+change);
                    angle++;
                }
                else if (increaseAngle == false){
                    angleRect.setWidth(angleRect.getWidth()-change);
                    angle--;
                }
                if (angleRect.getWidth() > 118){
                    increaseAngle = false;
                }
                else if (angleRect.getWidth() < 5){
                    increaseAngle = true;
                }
                angleLabel.setText(String.valueOf(angle));
                duppAngle = angle;
                if (indicatorShow){
                    anchorPane.getChildren().removeAll(pointsList);
                    indicator();
                }
            }  
    };
    
    AnimationTimer indicatorTimer = new AnimationTimer()
    {    
        public void handle(long currentNanoTime)
        {   
                int speed = duppSpeed;
                int angle = duppAngle;
                for (int i = 0; i < 11; i++) {
                    double X = imageview.getX() + (speed * Math.cos(Math.toRadians(angle)) * i * -1);
                    double Y = imageview.getY()+((speed * Math.sin(Math.toRadians(angle)) * i) - (0.5 * 9.81 * Math.pow(i, 2)))*-1;
                    Circle newCircle = new Circle(X, Y, 2);
                    anchorPane.getChildren().add(newCircle);            
                }
            }  
    };

    AnimationTimer fishShadowTimer = new AnimationTimer()
    {    
        Random random = new Random();
        int pullTime = 0;
        // double speed = 0.2;
        public void handle(long currentNanoTime)
        {   
            totalTime = (currentNanoTime - startTime)*Math.pow(10, -9)*4;
            for (fisk fish : fishShadowList) {
                double fishX = fish.getCenterX() - anchorPane.getWidth()/2;
                double yTop = anchorPane.getHeight()/2-30 + (b/a) * Math.sqrt(Math.pow(a, 2) - Math.pow(fishX, 2))*-1;
                double yBottom = anchorPane.getHeight()/2-30 + (b/a) * Math.sqrt(Math.pow(a, 2) - Math.pow(fishX, 2));
                
                // Circle cT = new Circle(fishX+anchorPane.getWidth()/2, yTop, 1);
                // Circle cB = new Circle(fishX+anchorPane.getWidth()/2, yBottom, 1);
                // anchorPane.getChildren().add(cT);
                // anchorPane.getChildren().add(cB); 

                if (fish.getCenterY() < yTop || fish.getCenterY() > yBottom || fish.getCenterX() > anchorPane.getWidth()/2 + a || fish.getCenterX() < anchorPane.getWidth()/2 - a){
                    // fish.setRotate(fish.getRotate()-180);
                    // fish.getFisk().setFill(Color.RED);
                    if (fish != fishOnHook)
                    fish.getFisk().setVisible(false);
                    else{
                        fish.getFisk().setRotate(fish.getFisk().getRotate()-180);
                    }
                    // System.out.println("SNU");
                }
                else {
                    // fish.getFisk().setFill(Color.DARKBLUE);
                    fish.getFisk().setVisible(true);
                }
                if (fish.getCenterX() > anchorPane.getWidth()-10 || fish.getCenterX() < 10){
                    fish.getFisk().setRotate(fish.getFisk().getRotate()-180);
                }
                if (fish.getCenterY() > anchorPane.getHeight() -10 || fish.getCenterY() < 10){
                    fish.getFisk().setRotate(fish.getFisk().getRotate()-180);
                }
                if (random.nextInt(1000) < 100){
                    fish.getFisk().setRotate(fish.getFisk().getRotate()+random.nextInt(8)-4);
                }
                if (random.nextInt(1000) < 10){
                    // speed += random.nextInt(1) - 2;
                    double change = random.nextDouble(0.5) - 0.25;
                    if (fish.getSpeed() + change > 0 && fish.getSpeed() + change < 0.6){
                        // speed += change;
                        fish.setSpeed(fish.getSpeed() + change);
                    }
                    // System.out.println(fish.getSpeed()); 
                }
                if (fish != fishOnHook){
                    fish.setCenterX(fish.getCenterX()+ fish.getSpeed() * Math.cos(Math.toRadians(fish.getFisk().getRotate())));
                    fish.setCenterY(fish.getCenterY()+ fish.getSpeed() * Math.sin(Math.toRadians(fish.getFisk().getRotate())));
                }
                else{
                    // fishOnHook.setCenterX(dupp.getCenterX());
                    // fishOnHook.setCenterY(dupp.getCenterY());
                    dupp.setCenterX(fishOnHook.getCenterX());
                    dupp.setCenterY(fishOnHook.getCenterY());
                    if (random.nextInt(1000) < 10 && fishPull == false){
                        fishPull = true;
                        fishOnHook.setSpeed(1.5);
                        fishOnHook.getFisk().setRotate(180);
                        pullTime = 0;
                        System.out.println("GOGOGO");
                    }
                    if (fishPull){
                        fishOnHook.setCenterX(fishOnHook.getCenterX()+ fishOnHook.getSpeed() * Math.cos(Math.toRadians(fishOnHook.getFisk().getRotate())));
                        fishOnHook.setCenterY(fishOnHook.getCenterY()+ fishOnHook.getSpeed() * Math.sin(Math.toRadians(fishOnHook.getFisk().getRotate())));
                        pullTime += 1;
                        if (pullTime > 400){
                            System.out.println("CHILL");
                            fishPull = false;
                        }
                    }
                }

                if (Math.abs(fish.getCenterX()-dupp.getCenterX()) <= 10 && Math.abs(fish.getCenterY()-dupp.getCenterY()) <= 10 && fishOnHook == null && duppUte == true){
                    System.out.println("NAPP");
                    fish.setCenterX(dupp.getCenterX());
                    fish.setCenterY(dupp.getCenterY());
                    // fishShadowList.remove(fish);
                    fishOnHook = fish;
                    fishPull = true;
                    fishOnHook.getFisk().setRotate(180);
                    fish.getFisk().setVisible(true);
                    break;
                }
            }
        }  
    };

    AnimationTimer reelInTimer = new AnimationTimer()
    {    
        Random random = new Random();
        double endX;
        double endY;
        public void handle(long currentNanoTime)
        {   
            if (fishDragUp == false){
                endX = imageview.getX() - 10;
                endY = imageview.getY() + 90;
            }
            // System.out.println(endX + ", " + endY);
            if (Math.abs(endX-dupp.getCenterX()) <= 20 && Math.abs(endY-dupp.getCenterY()) <= 20){
                System.out.println("DRA OPP");
                endX = imageview.getX() + 10;
                endY = imageview.getY() + 10;
                fishDragUp = true;
                if (fishOnHook != null){
                    fishShadowList.remove(fishOnHook);
                    anchorPane.getChildren().add(yellowFish);
                    yellowFish.setY(fishOnHook.getCenterY());
                    yellowFish.setY(fishOnHook.getCenterY());
                    fishRotation.setAngle(90);
                    fishOnHook.getFisk().setVisible(false);
                }
            }
            if (Math.abs(endX-dupp.getCenterX()) <= 10 && Math.abs(endY-dupp.getCenterY()) <= 10 && fishDragUp == true){
                anchorPane.getChildren().remove(fishOnHook.getFisk());
                int row = (int) Math.ceil((availibleSlot/3.0)-1.0);
                int column = (int) availibleSlot - (row*3) - 1;
                fishDisplayPane.add(fishOnHook.getFisk(), column, row);
                availibleSlot++;
                fishDragUp = false;
                fishOnHook = null;
            }
            testDot.setCenterX(endX);
            testDot.setCenterY(endY);
            dupp.setCenterX(dupp.getCenterX() + (endX-dupp.getCenterX())/100);
            dupp.setCenterY(dupp.getCenterY() + (endY-dupp.getCenterY())/100);
            lina.setEndX(dupp.getCenterX());
            lina.setEndY(dupp.getCenterY());
            if (fishOnHook != null && fishDragUp == false){
                // double angle = Math.atan(Math.abs(endY-fishOnHook.getCenterY())/Math.abs(endX-fishOnHook.getCenterX()));
                double angle = Math.atan((fishOnHook.getCenterY()-endY)/(endX-fishOnHook.getCenterX()));
                angle = Math.toDegrees(angle) * -1;
                // System.out.println(angle);
                fishOnHook.setCenterX(fishOnHook.getCenterX()+ (fishOnHook.getSpeed()+0.5) * Math.cos(Math.toRadians(angle)));
                fishOnHook.setCenterY(fishOnHook.getCenterY()+ (fishOnHook.getSpeed()+0.5) * Math.sin(Math.toRadians(angle)));
            }
            if (fishDragUp && fishOnHook != null){
                fishOnHook.setCenterX(dupp.getCenterX());
                fishOnHook.setCenterY(dupp.getCenterY());
                yellowFish.setX(fishOnHook.getCenterX() + yellowFish.getFitHeight()/2);
                yellowFish.setY(fishOnHook.getCenterY());
                fishRotation.setAngle(90);

                // fishRotation.setAngle(fishRotation.getAngle() + random.nextInt(-1, 1));
            }
        }  
    };

    public void indicator() {
        int speed = duppSpeed;
        int angle = duppAngle;
        pointsList.clear();
        for (int i = 0; i < 11; i++) {
            double X = imageview.getX() + (speed * Math.cos(Math.toRadians(angle)) * i * -1);
            double Y = imageview.getY()+((speed * Math.sin(Math.toRadians(angle)) * i) - (0.5 * 9.81 * Math.pow(i, 2)))*-1;
            Circle newCircle = new Circle(X, Y, 2);
            anchorPane.getChildren().add(newCircle);
            pointsList.add(newCircle);            
        }
    }

    @FXML
    private void handleButton() {
        // button.setLayoutX(button.getLayoutX()+30);
        // System.out.println("HEI");
        // TranslateTransition tt = new TranslateTransition(Duration.millis(750), anchorPane);
        // tt.setToX( (true)?-anchorPane.getWidth()/2:(-1 * 1000 / 2));
        // tt.setToY( (true)?0.0:(-1 * 500/2 ));

        // ScaleTransition st = new ScaleTransition(Duration.millis(750), anchorPane);
        // st.setToX( (true)?1.5:0.0 );
        // st.setToY( (true)?1.5:0.0 );

        // ParallelTransition pt = new ParallelTransition(tt, st);
        // pt.play();
        if (fishDisplayScroll.isVisible() == false){
            fishDisplayScroll.setVisible(true);
            fishDisplayScroll.setLayoutX(anchorPane.getWidth()/2 - fishDisplayRect.getWidth()/2);
            fishDisplayScroll.setLayoutY(anchorPane.getHeight()/10);
            fishDisplayShow = true;
        }
        else{
            fishDisplayScroll.setVisible(false);
            fishDisplayShow = false;
        }
    }

    @FXML
    private void handleKeyPressed(KeyEvent keyEvent) {
        
            if (keyEvent.getCode() == KeyCode.R){
                mediaPlayer.stop();
                // System.out.println("RRRR");
                
                // imageview.setRotate(imageview.getRotate()+1);
                rotation.setAngle(rotation.getAngle() + 1);
            }
            if (keyEvent.getCode() == KeyCode.T){
                if (indicatorShow){
                    indicatorShow = false;
                    anchorPane.getChildren().removeAll(pointsList);
                }
                else indicatorShow = true;
            }
            if (keyEvent.getCode() == KeyCode.E){
                reelInTimer.start();
            }
            if (keyEvent.getCode() == KeyCode.A){
                anchorPane.setTranslateX(anchorPane.getTranslateX()-5);
                System.out.println(anchorPane.getTranslateX());
            }
            if (keyEvent.getCode() == KeyCode.D){
                anchorPane.setTranslateX(anchorPane.getTranslateX()+5);
                System.out.println(anchorPane.getTranslateX());
            }
            if (keyEvent.getCode() == KeyCode.W){
                anchorPane.setTranslateY(anchorPane.getTranslateY()-5);
                System.out.println(anchorPane.getTranslateY());
            }
            if (keyEvent.getCode() == KeyCode.S){
                anchorPane.setTranslateY(anchorPane.getTranslateY()+5);
                System.out.println(anchorPane.getTranslateY());
            }
    }

    @FXML
    private void handleKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.R){
            mediaPlayer.play();
            rotation.setAngle(0);
            startTime = System.nanoTime();
            totalTime = 0;
            duppTimer.start();
            duppUte = false;
            // mediaPlayer.stop();
        }
        if (keyEvent.getCode() == KeyCode.V){
            fishShadowTimer.stop();
        }
        if (keyEvent.getCode() == KeyCode.Q){
            fishShadowTimer.start();
        }
        if (keyEvent.getCode() == KeyCode.E){
            reelInTimer.stop();
        }
        if (keyEvent.getCode() == KeyCode.H){
            powerBarTimer.stop();
        }
        if (keyEvent.getCode() == KeyCode.Y){
            powerBarTimer.start();
        }
        if (keyEvent.getCode() == KeyCode.J){
            angleBarTimer.stop();
        }
        if (keyEvent.getCode() == KeyCode.U){
            angleBarTimer.start();
        }
        if (keyEvent.getCode() == KeyCode.M){
            // double a = 425.0;
            a = anchorPane.getWidth()*0.425;
            // double b = 225.0;
            b = anchorPane.getHeight()*0.375;
            System.out.println(a + " , " + b);
            int x = (int) a;
            Double y1;
            Double y2;
            for (int i = -x; i <= x; i++) {
                y1 = (b/a) * Math.sqrt(Math.pow(a, 2) - Math.pow(i, 2));
                y2 = (b/a) * Math.sqrt(Math.pow(a, 2) - Math.pow(i, 2)) *-1;
                // System.out.println("x= " + i + " , y= " + y + " , " + Math.sqrt(Math.pow(a, 2) - Math.pow(i, 2)));
                Circle cir1 = new Circle(anchorPane.getWidth()/2 + i, anchorPane.getHeight()/2-30 + y1, 1);
                Circle cir2 = new Circle(anchorPane.getWidth()/2 + i, anchorPane.getHeight()/2-30 + y2, 1);
                anchorPane.getChildren().add(cir1);
                anchorPane.getChildren().add(cir2);
            }
        }
        if (keyEvent.getCode() == KeyCode.N){
            System.out.println("----------");
            System.out.println(anchorPane.getChildren());
            System.out.println("----------");
        }
        if (keyEvent.getCode() == KeyCode.B){
            // Ellipse shadow = new Ellipse(400, 200, 15, 5);
            // shadow.setFill(Color.DARKBLUE);
            Random random = new Random();
            // shadow.setOpacity(random.nextDouble(0.3, 1.0));
            // anchorPane.getChildren().add(shadow);
            // fishShadowList.add(shadow);

            fisk nyFisk = new fisk(random.nextDouble(50, anchorPane.getWidth()-50), random.nextDouble(50, anchorPane.getHeight()-50));
            fishShadowList.add(nyFisk);
            anchorPane.getChildren().add(nyFisk.getFisk());

            a = anchorPane.getWidth()*0.425;
            b = anchorPane.getHeight()*0.375;
        }
        if (keyEvent.getCode() == KeyCode.O){
            if (isZoom == false){
                zoomInTimer.start();
                isZoom = true;
            }
            else if (isZoom == true){
                zoomOutTimer.stop();
                zoomInTimer.stop();
                isZoom = false;
            }
        }
        if (keyEvent.getCode() == KeyCode.L){
            if (isZoom == false){
                zoomOutTimer.start();
                isZoom = true;
            }
            else if (isZoom == true){
                zoomInTimer.stop();
                zoomOutTimer.stop();
                isZoom = false;
            }
        }
    }

    @FXML
    private void handleScroll(ScrollEvent se) {
        // System.out.println(se.getDeltaY());
        if (fishDisplayShow == false){
            if (se.getDeltaY() > 0){
                scale.setX(scale.getX() + 0.1);
                scale.setY(scale.getY() + 0.1);
            }
            else if (se.getDeltaY() < 0){
                // System.out.println(scale.getX() + ", " + scale.getY());
                if (scale.getX() > 1.0){
                    scale.setX(scale.getX() - 0.1);
                    scale.setY(scale.getY() - 0.1);
                }
            }
        }
    }

    @FXML
    private void moveScreen(MouseEvent e) {
        mousePos.setText("x=" + String.valueOf(Math.round(e.getX())) + " : y=" + String.valueOf(Math.round(e.getY())));
    }

}