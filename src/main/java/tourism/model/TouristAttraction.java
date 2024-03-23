package tourism.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TouristAttraction {
    private String name;
    private String description;
    private String location;
    private List<String> tags;

    public TouristAttraction(String name, String description, String location, List<String> tags) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.tags = tags;
    }

    public TouristAttraction() {
        this.name = "";
        this.description = "";
        this.location = "";
        this.tags = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
