package ru.kpfu.itis.javalab.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */
@Component
public class FreemarkerMailsGenerator implements MailsGenerator {

    @Autowired
    private FreeMarkerConfig freemarkerConfig;

    @Override
    public String getMailForConfirm(String serverUrl, String confirmCode) {
        Map<String, String> attrs = new HashMap<>();
        attrs.put("confirm_code", confirmCode);
        attrs.put("server_url", serverUrl);

        StringWriter stringWriter = new StringWriter();

        Configuration configuration = freemarkerConfig.getConfiguration();

        Template confirmTemplate;
        try {
            confirmTemplate = configuration.getTemplate("confirm_message.ftlh");
            confirmTemplate.process(attrs, stringWriter);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }

        return stringWriter.toString();
    }
}
