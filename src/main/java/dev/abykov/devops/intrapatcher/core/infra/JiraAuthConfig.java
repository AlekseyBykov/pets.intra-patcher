package dev.abykov.devops.intrapatcher.core.infra;

import java.nio.file.*;
import java.util.Base64;
import java.util.Properties;

public class JiraAuthConfig {

    private static final Properties props = new Properties();

    static {
        // 1) ENV > 2) ~/.patchcraft/jira.properties
        loadPropertiesIfExists();
    }

    private static void loadPropertiesIfExists() {
        try {
            Path path = Path.of(
                    System.getProperty("user.home"),
                    ".patchcraft",
                    "jira.properties"
            );

            if (Files.exists(path)) {
                props.load(Files.newBufferedReader(path));
            }
        } catch (Exception e) {
            // empty
        }
    }

    public static String getAuthHeader() {
        // 1) Priority: ENV
        String envUser = System.getenv("JIRA_USER");
        String envPass = System.getenv("JIRA_PASS");

        if (envUser != null && envPass != null) {
            return encode(envUser, envPass);
        }

        // 2) File ~/.patchcraft/jira.properties
        String fileUser = props.getProperty("jira.user");
        String filePass = props.getProperty("jira.pass");

        if (fileUser != null && filePass != null) {
            return encode(fileUser, filePass);
        }

        throw new IllegalStateException(
                "No ENV vars (JIRA_USER/JIRA_PASS) and no ~/.patchcraft/jira.properties file found."
        );
    }

    private static String encode(String user, String pass) {
        String raw = user + ":" + pass;
        return "Basic " + Base64.getEncoder().encodeToString(raw.getBytes());
    }
}
