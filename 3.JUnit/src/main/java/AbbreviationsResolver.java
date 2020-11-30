import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbbreviationsResolver {

    private final Map<String, String> abbreviations;

    public AbbreviationsResolver() {
        this.abbreviations = new HashMap<String, String>();
    }

    public String createAbbreviation(String name) {
        if (name.matches("^[a-zA-Z ].*\\p{Punct}+[a-zA-Z ].*$")) {
            throw new RuntimeException("Name can't contain any punctuation marks!");
        }
        String[] words = name.split(" ");
        if (words.length <= 1) {
            throw new RuntimeException("Name must contain at least 2 words!");
        }
        StringBuilder abbreviation = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            abbreviation.append(words[i].toUpperCase().charAt(0));
        }
        abbreviations.put(name, abbreviation.toString());
        return abbreviation.toString();
    }

    public boolean abbreviationExists(String abbreviation) {
        if (!abbreviation.toUpperCase().equals(abbreviation)) {
            throw new RuntimeException("Abbreviation should be in upper case");
        }
        return abbreviations.containsValue(abbreviation);
    }

    public List<String> resolveAbbreviation(String abbreviation) {
        List<String> matchNames = new ArrayList<String>();
        for (Map.Entry<String, String> entry : abbreviations.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(abbreviation)) {
                matchNames.add(entry.getKey());
            }
        }
        return matchNames;

        /** Analog of code above using Stream API
         return abbreviations.entrySet().stream()
         .filter(entry -> entry.getValue().equalsIgnoreCase(abbreviation))
         .map(entry -> entry.getKey())
         .collect(Collectors.toList());
         */
    }

    public void cleanAbbreviations() {
        abbreviations.clear();
    }
}
