package afl;

import freemarker.template.Configuration;
import freemarker.template.Version;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;
import java.io.File;

public class TemplateConfiguration {
    private static Configuration config = new Configuration(Configuration.VERSION_2_3_25);

    public TemplateConfiguration() {}

    public static Configuration getConfig(String templatePath) {
        if(config == null) {
            config = new Configuration(Configuration.VERSION_2_3_25);
            try {
                config.setDirectoryForTemplateLoading(new File(templatePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            config.setDefaultEncoding("UTF-8");
            config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            config.setLogTemplateExceptions(false);
        }
        return config;
    }
}
