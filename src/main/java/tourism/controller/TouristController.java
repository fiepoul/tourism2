package tourism.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private final TouristService service;

    @Autowired
    public TouristController(TouristService service) {
        this.service = service;
    }


    // CRUD endpoints
    @GetMapping
    public String getAttractions(Model model) {
        List<TouristAttraction> attractions = service.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList"; //thymeleaf skabelonen
    }

    @GetMapping("/{name}/tags")
    public String showTags(@PathVariable("name") String name, Model model) {
        List<String> tags = service.getTagsForAttraction(name);
        model.addAttribute("tags", tags);
        model.addAttribute("attractionName", name);
        return "tags"; //thymeleaf skabelonen
    }

    @GetMapping("/{name}")
    public String getAttractionByName(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.getAttractionByName(name);
        model.addAttribute("attraction", attraction);
        return "attraction-detail";
    }

    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.getAttractionByName(name);
        if (attraction != null) {
            model.addAttribute("attraction", attraction);
            model.addAttribute("allTags", service.getAllTags());
            model.addAttribute("allLocations", service.getLocations());
            return "updateAttraction";
        } else {
            return "redirect:/attractions";
        }
    }

    @PostMapping("/{name}/update")
    public String updateAttraction(@PathVariable String name, @ModelAttribute TouristAttraction attraction, @RequestParam List<String> tags) {
        attraction.setTags(tags);
        service.updateAttraction(name, attraction);
        return "redirect:/attractions";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction, @RequestParam(required = false) List<String> tags) {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        attraction.setTags(tags);
        service.addAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/add")
    public String showAddAttractionForm(Model model) {
        model.addAttribute("attraction", new TouristAttraction()); // tom instans for form
        model.addAttribute("allLocations", service.getLocations()); //todo: hvad går galt her? måske genstart
        List<String> allTags = service.getAllTags(); // Sender alle tags til modellen
        model.addAttribute("allTags", allTags);
        return "add-attraction";
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteAttration(@PathVariable String name) {
        service.deleteAttraction(name);
        return ResponseEntity.ok(name + " er slettet");
    }
}
