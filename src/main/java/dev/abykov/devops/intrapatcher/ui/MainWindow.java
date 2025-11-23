package dev.abykov.devops.intrapatcher.ui;

import dev.abykov.devops.intrapatcher.ui.tabs.letter.LetterTab;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainWindow {

    public Parent createMainUI() {
        TabPane pane = new TabPane();

        Tab tabLetter = new Tab("Letter", new LetterTab().getContent());
        tabLetter.setClosable(false);

        pane.getTabs().addAll(tabLetter);

        return pane;
    }
}
