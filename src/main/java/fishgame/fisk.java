package fishgame;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class fisk {

    double speed = 0.2;
    Random random = new Random();
    Ellipse fiskShadow;

    public fisk(double centerx, double centery) {
        // int bredde = random.nextInt(3, 15);
        // int høyde = random.nextInt(1, bredde-2);
        int bredde = 15;
        int høyde = 5;
        fiskShadow = new Ellipse(centerx, centery, bredde, høyde);
        fiskShadow.setOpacity(random.nextDouble(0.3, 1.0));
        fiskShadow.setFill(Color.DARKBLUE);
    }

    public Ellipse getFisk() {
        return fiskShadow;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return this.speed;
    }

    public double getCenterX() {
        return fiskShadow.getCenterX();
    }
    
    public double getCenterY() {
        return fiskShadow.getCenterY();
    }

    public void setCenterX(double centerx) {
        fiskShadow.setCenterX(centerx);
    }
    
    public void setCenterY(double centery) {
        fiskShadow.setCenterY(centery);
    }
}
