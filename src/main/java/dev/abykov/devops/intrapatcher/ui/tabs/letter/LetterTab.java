package dev.abykov.devops.intrapatcher.ui.tabs.letter;

import dev.abykov.devops.intrapatcher.core.service.JiraService;
import dev.abykov.devops.intrapatcher.core.service.PatchLetterService;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class LetterTab {

    public Parent getContent() {
        LetterService letterService =
                new LetterService(new JiraService(), new PatchLetterService());

        LetterViewModel vm = new LetterViewModel(letterService);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        TextField versionField = createTextField("1.2.3", vm.versionProperty());
        TextField patchUrlField = createTextField("ftp://...", vm.patchUrlProperty());
        TextField sqlUrlField = createTextField("ftp://...", vm.sqlUrlProperty());

        Button generateBtn = new Button("Make");
        generateBtn.disableProperty().bind(vm.loadingProperty());

        ProgressIndicator loader = new ProgressIndicator();
        loader.setPrefSize(24, 24);
        loader.visibleProperty().bind(vm.loadingProperty());

        HBox actions = new HBox(10, generateBtn, loader);

        WebView webView = new WebView();
        webView.setPrefHeight(450);
        VBox.setVgrow(webView, Priority.ALWAYS);

        vm.letterHtmlProperty().addListener((obs, o, html) ->
                webView.getEngine().loadContent(html, "text/html")
        );

        generateBtn.setOnAction(e -> vm.generateLetter());

        Label appLabel = new Label("PatchCraft");
        appLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #666;");

        root.getChildren().addAll(
                label("Version:"), versionField,
                label("patch url:"), patchUrlField,
                label("sql url:"), sqlUrlField,
                actions,
                label("Letter:"),
                webView,
                appLabel
        );

        return root;
    }

    private TextField createTextField(String prompt, StringProperty prop) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.textProperty().bindBidirectional(prop);
        return tf;
    }

    private Label label(String text) {
        return new Label(text);
    }
}
