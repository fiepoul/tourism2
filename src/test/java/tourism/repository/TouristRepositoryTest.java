package tourism.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tourism.model.TouristAttraction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TouristRepositoryTest {
    private final TouristRepository repository;

    TouristRepositoryTest(TouristRepository repository) {
        this.repository = repository;
    }

    @Test
    void findByNameExistingAttraction() {
        String attractionName = "flyvergrillen";
        assertTrue(repository.findByName(attractionName).isPresent(), "Attraction should be found by name");
    }

    @Test
    void findByNameNonExistingAttraction() {
        String nonExistingAttractionName = "nonExisting";
        assertFalse(repository.findByName(nonExistingAttractionName).isPresent(), "Non-existing attraction should not be found");
    }

    @Test
    void addNewAttraction() {
        int initialSize = repository.findAll().size();
        repository.add(new TouristAttraction("TestAttraction", "Description", "Location", List.of()));
        assertEquals(initialSize + 1, repository.findAll().size(), "Attraction list size should increase by 1");
    }

    @Test
    void deleteExistingAttraction() {
        String attractionName = "flyvergrillen";
        assertTrue(repository.findByName(attractionName).isPresent(), "Attraction should exist before deletion");
        repository.delete(attractionName);
        assertFalse(repository.findByName(attractionName).isPresent(), "Attraction should not exist after deletion");
    }
}