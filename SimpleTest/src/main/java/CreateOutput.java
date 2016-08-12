package afl;

import freemarker.template.TemplateNotFoundException;

public class CreateOutput {
    public static void main(String[] args) {
        if(args.length <= 1) {
            System.out.println("ERROR YOU NEED TO SPECIFY ARGS");
            System.exit(1);
        }
        WordCount wordCt = new WordCount(args[0]);
        wordCt.readFile();
        try {
            CreateTemplate template = new CreateTemplate(args[1]);
            template.createOutput(wordCt, args[1]);
        } catch (TemplateNotFoundException e) {
            System.out.println(e);
        }
    }
}
