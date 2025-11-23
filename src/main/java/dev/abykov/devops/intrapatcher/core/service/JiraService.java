package dev.abykov.devops.intrapatcher.core.service;

import dev.abykov.devops.intrapatcher.core.domain.JiraIssue;
import dev.abykov.devops.intrapatcher.core.infra.JiraClient;

import java.util.List;

public class JiraService {

    public List<JiraIssue> loadIssues(String version) {
        return JiraClient.getIssuesForVersion(version);
    }
}
