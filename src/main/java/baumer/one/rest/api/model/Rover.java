package baumer.one.rest.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Rover extends RepresentationModel<Rover> {
    @Id
    private int id;
    private String name;
    private LocalDate landingDate;
    private LocalDate launchDate;
    private String status;
}