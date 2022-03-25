package baumer.one.rest.api.model;

import lombok.Data;

import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@Entity
public class CuriosityMars extends RepresentationModel<CuriosityMars> {
    @Id
    private int id;
    private int sol;
    private String imgSrc;
    private LocalDate earthDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="camera_id", nullable=false)
    private Camera camera;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="rover_id", nullable=false)
    private Rover rover;
}