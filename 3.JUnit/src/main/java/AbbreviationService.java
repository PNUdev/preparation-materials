import java.util.List;

public class AbbreviationService {

    private AbbreviationDatabase database;

    public AbbreviationService(AbbreviationDatabase database) {
        this.database = database;
    }

    public AbbreviationService(AbbreviationDatabase database, AbbreviationDatabase database1) {
        this.database = database;
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
        database.create(name, abbreviation.toString());
        return abbreviation.toString();
    }

    public boolean abbreviationExists(String abbreviation) {
        if (!abbreviation.toUpperCase().equals(abbreviation)) {
            throw new RuntimeException("Abbreviation should be in upper case");
        }
        return database.exists(abbreviation);
    }

    public List<String> resolveAbbreviation(String abbreviation) {
        return database.getNamesByAbbreviation(abbreviation);

        /** Analog of code above using Stream API
         return abbreviations.entrySet().stream()
         .filter(entry -> entry.getValue().equalsIgnoreCase(abbreviation))
         .map(entry -> entry.getKey())
         .collect(Collectors.toList());
         */
    }
}
