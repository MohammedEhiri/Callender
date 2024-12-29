package med.ehiri.content_calender.repository;

import jakarta.annotation.PostConstruct;
import med.ehiri.content_calender.model.Content;
import med.ehiri.content_calender.model.Status;
import med.ehiri.content_calender.model.Type;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class ContentCollectionRepository {

    private final List<Content> contents = new ArrayList<>();

    public ContentCollectionRepository() {}

    public List<Content> findAll() { return contents; }

    public Optional<Content> findById(Integer id) {
        return contents.stream().filter(content -> content.id().equals(id)).findFirst();
    }


    public void save(Content content) {
        contents.removeIf(content1 -> content1.id().equals(content.id()));
        contents.add(content);
    }

    @PostConstruct
    private void init() {
        Content c = new Content( 1,
                "content 1",
                "Content added bby init method",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );

        contents.add(c);
    }

    public boolean existsById(Integer id) {
        return contents.stream().filter(content -> content.id().equals(id)).count() == 1;
    }

    public void delete(Integer id) {
        contents.removeIf(content -> content.id().equals(id));
    }
}
