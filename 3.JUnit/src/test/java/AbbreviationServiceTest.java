import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AbbreviationServiceTest {

    @Mock
    private AbbreviationDatabase databaseMock;

    @InjectMocks
    private AbbreviationService abbreviationService;

    @BeforeEach
    public void setUp() {
        System.out.println(LocalDateTime.now() + " -- test starter");
        abbreviationService.createAbbreviation("United Arabic Emirates");
        abbreviationService.createAbbreviation("United Kingdom");
        abbreviationService.createAbbreviation("Unlucky Kostya");
        abbreviationService.createAbbreviation("Soviet Union");
    }

    @Test
    public void createAbbreviationTest() {
        String name = "Korean National Republic";
        String expectedAbbreviation = "KNR";
        String actualAbbreviation = abbreviationService.createAbbreviation(name);
        assertEquals(expectedAbbreviation, actualAbbreviation);
        Mockito.verify(databaseMock, Mockito.times(1)).create(name, expectedAbbreviation);
    }

    @Test
//    @Disabled
    public void createAbbreviationTest_exceptionFlow() {
        String incorrectName1 = "New, York";
        Exception exception1 = assertThrows(RuntimeException.class,
                () -> abbreviationService.createAbbreviation(incorrectName1));
        assertEquals("Name can't contain any punctuation marks!", exception1.getMessage());

        String incorrectName2 = "Ukraine";
        Exception exception2 = assertThrows(RuntimeException.class,
                () -> abbreviationService.createAbbreviation(incorrectName2));
        assertEquals("Name must contain at least 2 words!", exception2.getMessage());
    }

    @Test
    public void abbreviationExistsTest() {
        assertFalse(abbreviationService.abbreviationExists("KNR"));
        Mockito.when(databaseMock.exists("UAE")).thenReturn(true);
        assertTrue(abbreviationService.abbreviationExists("UAE"));
    }

    @Test
    public void resolveAbbreviationTest() {
        String abbreviation1 = "UK";
        Mockito
                .when(databaseMock.getNamesByAbbreviation(abbreviation1))
                .thenReturn(Arrays.asList("United Kingdom", "Unlucky Kosya"));
        List<String> names1 = abbreviationService.resolveAbbreviation(abbreviation1);
        assertEquals(2, names1.size());

        String abbreviation2 = "SU";
        Mockito
                .when(databaseMock.getNamesByAbbreviation(abbreviation2))
                .thenReturn(Arrays.asList("Soviet Union"));
        List<String> names2 = abbreviationService.resolveAbbreviation(abbreviation2);
        assertEquals(1, names2.size());

        String abbreviation3 = "IF";
        Mockito
                .when(databaseMock.getNamesByAbbreviation(abbreviation3))
                .thenReturn(Collections.emptyList());
        List<String> names3 = abbreviationService.resolveAbbreviation(abbreviation3);
        assertEquals(0, names3.size());

    }
}
