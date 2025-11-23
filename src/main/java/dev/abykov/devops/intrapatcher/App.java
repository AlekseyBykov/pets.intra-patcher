package dev.abykov.devops.intrapatcher;

import dev.abykov.devops.intrapatcher.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("IntraPatcher — Internal DevOps Tool v" + AppInfo.version());

        Scene scene = new Scene(new MainWindow().createMainUI(), 900, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
