package afl;

import java.util.HashMap;
import java.util.Map;

public class CreateDataModel {
    Map<String, Object> root = new HashMap<>();

    public CreateDataModel(WordCount wordCount) {
        root.put("name", wordCount.getName());
        root.put("words", wordCount.getWords());
    }

    public Map<String, Object> getRoot() {
        return root;
    }
}
