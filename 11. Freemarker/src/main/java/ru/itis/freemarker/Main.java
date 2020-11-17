package ru.itis.freemarker;


import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 * group 11-905
 */

public class Main {
    public static void main(String[] args) throws IOException, TemplateException {
        String templatesRoot = "src/main/resources/";
        String outputRoot = "src/main/webapp/";

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("utf-8");
        configuration.setTemplateLoader(new FileTemplateLoader(new File(templatesRoot)));

        Map<String, Object> attrs = new HashMap<>();

        Template usersTemplate = configuration.getTemplate("users.ftlh");

        User user1 = new User("Ivan", "Ivanov");
        User user2 = new User("Anvar", "Khasanov");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        attrs.put("users", users);
        usersTemplate.process(attrs, new FileWriter(outputRoot + usersTemplate.getName()));


        attrs.clear();
        Template timeTemplate = configuration.getTemplate("time.ftlh");

        Date time = Calendar.getInstance().getTime();

        attrs.put("time", time);
        timeTemplate.process(attrs, new FileWriter(outputRoot + timeTemplate.getName()));
    }
}
