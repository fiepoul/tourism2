package tourism.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tourism.model.TouristAttraction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TouristRepository implements ITouristRepository {
    private final DBManager dbManager;
    private static final String INSERT_ATTRACTION = "INSERT INTO attractions (name, description) VALUES (?, ?);";
    private static final String SELECT_ALL_ATTRACTIONS = "SELECT * FROM attractions;";
    private static final String SELECT_ATTRACTION_BY_NAME = "SELECT * FROM attractions WHERE name = ?;";
    private static final String UPDATE_ATTRACTION = "UPDATE attractions SET name = ?, description = ? WHERE name = ?;";
    private static final String DELETE_ATTRACTION = "DELETE FROM attractions WHERE name = ?;";

    private static final String SELECT_TAGS_FOR_ATTRACTION =
            "SELECT t.name FROM tags t " +
                    "INNER JOIN attraction_tags at ON t.id = at.tag_id " +
                    "INNER JOIN attractions a ON at.attraction_id = a.id " +
                    "WHERE a.name = ?;";

    @Autowired
    public TouristRepository(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    protected Connection getConnection() throws SQLException {
        return DBManager.getInstance().getConnection();
    }

    public List<String> findTagsForAttraction(String attractionName) {
        List<String> tags = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TAGS_FOR_ATTRACTION)) {
            preparedStatement.setString(1, attractionName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tags.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }

    public void add(TouristAttraction attraction) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ATTRACTION)) {
            preparedStatement.setString(1, attraction.getName());
            preparedStatement.setString(2, attraction.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TouristAttraction> findAll() {
        List<TouristAttraction> attractions = new ArrayList<>();
        String query = "SELECT a.name, a.description, l.name AS locationName FROM attractions a JOIN locations l ON a.location_id = l.id;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String locationName = rs.getString("locationName");
                List<String> tags = findTagsForAttraction(name);
                attractions.add(new TouristAttraction(name, description, locationName, tags)); // Antag at TouristAttraction konstruktøren er opdateret til at håndtere locationName og en liste af tags
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attractions;
    }

    public Optional<TouristAttraction> findByName(String name) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ATTRACTION_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String attractionName = rs.getString("name");
                String description = rs.getString("description");
                // Bemærk: Eksemplet henter ikke location_id eller tags
                return Optional.of(new TouristAttraction(attractionName, description, null, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<String> getAllTags() {
        List<String> tags = new ArrayList<>();
        String sql = "SELECT name FROM tags;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                tags.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }

    public List<String> getLocations() {
        List<String> locations = new ArrayList<>();
        String query = "SELECT name FROM locations;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                locations.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public void update(String name, TouristAttraction updatedAttraction) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ATTRACTION)) {
            preparedStatement.setString(1, updatedAttraction.getName());
            preparedStatement.setString(2, updatedAttraction.getDescription());
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String name) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ATTRACTION)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
