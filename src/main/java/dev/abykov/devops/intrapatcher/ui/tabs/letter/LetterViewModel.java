package dev.abykov.devops.intrapatcher.ui.tabs.letter;

import dev.abykov.devops.intrapatcher.core.domain.JiraIssue;
import javafx.application.Platform;
import javafx.beans.property.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LetterViewModel {

    private final StringProperty version = new SimpleStringProperty("");
    private final StringProperty patchUrl = new SimpleStringProperty("");
    private final StringProperty sqlUrl = new SimpleStringProperty("");

    private final StringProperty letterHtml = new SimpleStringProperty();
    private final BooleanProperty loading = new SimpleBooleanProperty(false);

    private final LetterService service;

    public LetterViewModel(LetterService service) {
        this.service = service;
    }

    public void generateLetter() {
        loading.set(true);

        CompletableFuture
                .supplyAsync(() -> {
                    List<JiraIssue> issues = service.loadIssues(version.get());
                    return service.generateLetter(
                            version.get(),
                            patchUrl.get(),
                            sqlUrl.get(),
                            issues
                    );
                })
                .thenAccept(html -> Platform.runLater(() -> {
                    letterHtml.set(html);
                    loading.set(false);
                }))
                .exceptionally(ex -> {
                    Platform.runLater(() -> {
                        letterHtml.set("<p style='color:red'>Error: " + ex.getMessage() + "</p>");
                        loading.set(false);
                    });
                    return null;
                });
    }

    public StringProperty versionProperty() {
        return version;
    }

    public StringProperty patchUrlProperty() {
        return patchUrl;
    }

    public StringProperty sqlUrlProperty() {
        return sqlUrl;
    }

    public StringProperty letterHtmlProperty() {
        return letterHtml;
    }

    public BooleanProperty loadingProperty() {
        return loading;
    }
}
