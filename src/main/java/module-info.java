module dev.abykov.devops.intrapatcher {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires freemarker;

    exports dev.abykov.devops.intrapatcher;
    exports dev.abykov.devops.intrapatcher.core.domain to freemarker;

    opens templates;
}
