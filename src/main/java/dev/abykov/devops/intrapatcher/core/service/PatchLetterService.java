package dev.abykov.devops.intrapatcher.core.service;

import dev.abykov.devops.intrapatcher.core.domain.JiraIssue;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatchLetterService {

    private final Configuration cfg;

    public PatchLetterService() {
        cfg = new Configuration(new Version("2.3.31"));
        cfg.setClassLoaderForTemplateLoading(
                getClass().getClassLoader(),
                ""
        );
        cfg.setDefaultEncoding("UTF-8");
    }

    public String generateHtml(
            String version,
            String patchUrl,
            String sqlUrl,
            List<JiraIssue> issues
    ) {
        try {
            Template tpl = cfg.getTemplate("templates/patch_letter.ftl");

            Map<String, Object> model = new HashMap<>();
            model.put("ver", version);
            model.put("patchUrl", patchUrl);
            model.put("sqlUrl", sqlUrl);
            model.put("issues", issues);

            StringWriter out = new StringWriter();
            tpl.process(model, out);

            return out.toString();

        } catch (IOException | TemplateException e) {
            throw new RuntimeException("Error while make letter", e);
        }
    }
}
