package baumer.one.rest.api.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Camera extends RepresentationModel<Camera> {
    @Id
    private int id;
    private String name;
    private int roverId;
    private String fullName;
}