package tourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TouristService {
    private final TouristRepository repository;

    @Autowired
    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    //CRUD metoder der kalder repository metoder
    public List<TouristAttraction> getAllAttractions() {
        return repository.findAll();
    }

    public TouristAttraction getAttractionByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public void addAttraction(TouristAttraction attraction) {
        repository.add(attraction);
    }

    public void updateAttraction(String name, TouristAttraction attraction) {
        repository.update(name, attraction);
    }

    public void deleteAttraction(String name) {
        repository.delete(name);
    }

    public List<String> getTagsForAttraction(String name) {
        return repository.findTagsForAttraction(name);
    }

    public List<String> getLocations() {
        return repository.getLocations();
    }

    public List<String> getAllTags() {
        return repository.getAllTags();
    }


}
