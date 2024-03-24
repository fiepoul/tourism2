package tourism.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tourism.model.TouristAttraction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TouristServiceIntegrationTest {

    @Autowired
    private TouristService touristService;

    @Test
    void testGetAllAttractions() {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        assertNotNull(attractions, "Liste af attraktioner bør ikke være null");
        assertFalse(attractions.isEmpty(), "Liste af attraktioner bør ikke være tom");
    }
}