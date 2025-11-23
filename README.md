# IntraPatcher

**Lightweight Desktop Tool for Semi-Automated Release Notes & Patch Communication**

IntraPatcher is a small JavaFX-powered utility designed to streamline internal DevOps workflows. It is not a "magic pipeline orchestrator", but a focused tool that automates the repetitive manual steps involved in preparing 
patch release letters.

## Features
**1. Fetch issues by version**

Given a version number, the application retrieves related tasks from an issue-tracking system (via REST API) 
and generates a clean, structured output.

**2. Generate styled HTML release letter**

A Freemarker HTML template is used to render:
- mail-client-friendly layout (including correct OWA/Outlook styling),
- clickable issue links,
- inline styles for safe copy-paste into webmail,
- optional signature block.

**3. Live HTML preview via JavaFX WebView**

The generated letter isn’t shown as raw text — it is rendered in a browser component, allowing:
- pixel-accurate preview before sending,
- clean copying of the rendered HTML block.

**4. External configuration file**

Credentials and configuration values are not stored in the repository.

User-specific configuration lives in:
```bash
~/.intrapatcher/config.properties
```
Example:
```bash
jira.user=...
jira.token=...
jira.baseUrl=...
```

## **Tech stack**

- Java 21
- JavaFX 21 (controls + web)
- Freemarker 2.3.x — templating
- Jackson — JSON parsing
- Java HttpClient — REST communication
- MVVM architecture via JavaFX bindings
- Maven
