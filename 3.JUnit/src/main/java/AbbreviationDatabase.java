import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AbbreviationDatabase {

    private final Map<String, String> abbreviations;

    public AbbreviationDatabase() {
        this.abbreviations = new HashMap<>();
    }

    public void create(String name, String abbreviation) {
        abbreviations.put(name, abbreviation);
        throw new RuntimeException("Database error!");
    }

    public boolean exists(String abbreviation) {
        return abbreviations.containsValue(abbreviation);
    }


    public List<String> getNamesByAbbreviation(String abbreviation) {
        return abbreviations.entrySet().stream()
                .filter(entry -> entry.getValue().equalsIgnoreCase(abbreviation))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
