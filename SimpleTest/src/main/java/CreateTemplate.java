package afl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.core.ParseException;
import freemarker.cache.FileTemplateLoader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

public class CreateTemplate {
    Configuration config = null;
    Template temp = null;
    public CreateTemplate(String resourceFolder) throws TemplateNotFoundException{
        config = TemplateConfiguration.getConfig(resourceFolder+"/template");
        try {
            config.setTemplateLoader(new FileTemplateLoader(new File(resourceFolder+"/template")));
            temp = config.getTemplate("simpleTemplate.fthl");
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createOutput(WordCount words, String resourceFolder) {
        Writer out = new OutputStreamWriter(System.out);
        CreateDataModel dataModel = new CreateDataModel(words);
        Map<String, Object> root = dataModel.getRoot();
        if(temp == null){
            System.out.println("NULL");
        }
        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
