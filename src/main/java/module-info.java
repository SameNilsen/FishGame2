module fishapp {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;
    opens fishgame to javafx.fxml;
    exports fishgame;
}