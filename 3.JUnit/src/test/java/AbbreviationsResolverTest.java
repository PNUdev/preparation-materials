import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbbreviationsResolverTest {

    private AbbreviationsResolver abbreviationsResolver = new AbbreviationsResolver();

    @BeforeEach
    public void setUp() {
        System.out.println(LocalDateTime.now() + " -- test starter");
        abbreviationsResolver.createAbbreviation("United Arabic Emirates");
        abbreviationsResolver.createAbbreviation("United Kingdom");
        abbreviationsResolver.createAbbreviation("Unlucky Kostya");
        abbreviationsResolver.createAbbreviation("Soviet Union");
    }

    @Test
    public void createAbbreviationTest() {
        String name = "Korean National Republic";
        String expectedAbbreviation = "KNR";
        String actualAbbreviation = abbreviationsResolver.createAbbreviation(name);
        assertEquals(expectedAbbreviation, actualAbbreviation);
    }

    @Test
//    @Disabled
    public void createAbbreviationTest_exceptionFlow() {
        String incorrectName1 = "New, York";
        Exception exception1 = assertThrows(RuntimeException.class,
                () -> abbreviationsResolver.createAbbreviation(incorrectName1));
        assertEquals("Name can't contain any punctuation marks!", exception1.getMessage());

        String incorrectName2 = "Ukraine";
        Exception exception2 = assertThrows(RuntimeException.class,
                () -> abbreviationsResolver.createAbbreviation(incorrectName2));
        assertEquals("Name must contain at least 2 words!", exception2.getMessage());
    }

    @Test
    public void abbreviationExistsTest() {
        assertFalse(abbreviationsResolver.abbreviationExists("KNR"));
        assertTrue(abbreviationsResolver.abbreviationExists("UAE"));
    }

    @Test
    public void resolveAbbreviationTest() {
        String abbreviation1 = "UK";
        List<String> names1 = abbreviationsResolver.resolveAbbreviation(abbreviation1);
        assertEquals(2, names1.size());

        String abbreviation2 = "SU";
        List<String> names2 = abbreviationsResolver.resolveAbbreviation(abbreviation2);
        assertEquals(1, names2.size());

        String abbreviation3 = "IF";
        List<String> names3 = abbreviationsResolver.resolveAbbreviation(abbreviation3);
        assertEquals(0, names3.size());

    }

    @AfterEach
    public void afterTests() {
        System.out.println(LocalDateTime.now() + " -- test ended");
        abbreviationsResolver.cleanAbbreviations();
    }
}
