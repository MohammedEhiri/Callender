package med.ehiri.content_calender.controller;

import med.ehiri.content_calender.model.Content;
import med.ehiri.content_calender.repository.ContentCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository contentRepo;

    public ContentController(ContentCollectionRepository contentCollectionRepository) {
        this.contentRepo = contentCollectionRepository;
    }


    @GetMapping("")
    public List<Content> getALLContents() {
        return contentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Content getContentById(@PathVariable Integer id) {
        return contentRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found"));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createContent(@RequestBody Content content) {
        contentRepo.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id) {
        if (!contentRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found");
        }
        contentRepo.save(content);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!contentRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found");
        }
        contentRepo.delete(id);
    }
}
