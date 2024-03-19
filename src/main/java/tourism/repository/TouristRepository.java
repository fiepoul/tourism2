package tourism.repository;

import jdk.jfr.Registered;
import org.springframework.stereotype.Repository;
import tourism.model.AttractionTag;
import tourism.model.TouristAttraction;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Repository
public class TouristRepository {
    private List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository() {
        attractions.add(new TouristAttraction("flyvergrillen", "et spisested og udsigtspunkt over flyvebanen i Kastrup", "København", List.of(AttractionTag.MAD_DRIKKE, AttractionTag.HISTORISK, AttractionTag.UDSIGT)));
        attractions.add(new TouristAttraction("Cisternerne", "En tidligere vandreservoir under Frederiksberg høj som nu er en kunst udstillingsplads.", "København", List.of(AttractionTag.EVENTYRLIG, AttractionTag.KUNST, AttractionTag.KULTUR)));
        attractions.add(new TouristAttraction("Baby Zoo på Gavnoe Slot", "En lille zoologisk have, der fokuserer på babydyr.", "Sydsjælland", List.of(AttractionTag.FAMILIEVENLIG, AttractionTag.EVENTYRLIG)));
        attractions.add(new TouristAttraction("Raabjerg_Mile", "En vandrende sandklit nær Skagen.", "Nordjylland", List.of(AttractionTag.NATUR, AttractionTag.UDSIGT)));
        attractions.add(new TouristAttraction("Verdenskortet ved Klejtrup Soe", "En stor park med et stort kort over verden i skala 1:100.000.", "Midtjylland", List.of(AttractionTag.NATUR, AttractionTag.EVENTYRLIG)));
        attractions.add(new TouristAttraction("Mols_Bjerge", "Præhistoriske gravhøje med fantastisk udsigt.", "Midtjylland", List.of(AttractionTag.HISTORISK, AttractionTag.UDSIGT, AttractionTag.NATUR)));
    }

    public List<String> getLocations() {
        return List.of(
                "Nordsjælland",
                "Sydsjælland",
                "København",
                "Midtjylland",
                "Sønderjylland",
                "Fyn",
                "Bornholm",
                "Vestjylland",
                "Østjylland"
        );
    }

    //CRUD metoder
    public Optional<TouristAttraction> findByName(String name) {
        return attractions.stream().filter(attraction -> attraction.getName().equalsIgnoreCase(name)).findFirst();
    }

    public List<TouristAttraction> findAll() {
        return attractions;
    }

    public void add(TouristAttraction attraction) {
        attractions.add(attraction);
    }

    public void update(String name, TouristAttraction updatedAttraction) {
        for (TouristAttraction attraction: attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                attraction.setName(updatedAttraction.getName());
                attraction.setDescription(updatedAttraction.getDescription());
                attraction.setLocation(updatedAttraction.getLocation());
                attraction.setTags(updatedAttraction.getTags());
            }
        }
    }

    public void delete(String name) {
        findByName(name).ifPresent(attractions::remove);
    }



}
