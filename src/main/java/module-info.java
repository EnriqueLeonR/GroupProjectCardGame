module com.example.groupprojectcardgame {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens com.example.groupprojectcardgame to javafx.fxml;
    exports com.example.groupprojectcardgame;
}