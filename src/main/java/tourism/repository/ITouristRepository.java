package tourism.repository;

import tourism.model.TouristAttraction;
import java.util.List;
import java.util.Optional;

public interface ITouristRepository {
    List<TouristAttraction> findAll();
    Optional<TouristAttraction> findByName(String name);
    void add(TouristAttraction attraction);
    void update(String name, TouristAttraction attraction);
    void delete(String name);
    List<String> findTagsForAttraction(String name);
    List<String> getLocations();
    List<String> getAllTags();
}
