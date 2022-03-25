package baumer.one.rest.api;

import baumer.one.rest.api.model.CuriosityMars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "curiosity")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class CuriosityMarsController {

    @Autowired
    private CuriosityMarsService curiosityMarsService;

    @GetMapping(value = "/{curiosityMarsId}", produces = "application/json")
    public EntityModel<CuriosityMars> getCustomerById(@PathVariable final int curiosityMarsId) {
        return EntityModel.of(curiosityMarsService.getCuriosityMarsIdDetail(curiosityMarsId),
                linkTo(methodOn(CuriosityMarsController.class).getCustomerById(curiosityMarsId)).withSelfRel(),
                linkTo(methodOn(CuriosityMarsController.class).listAll()).withRel("curiosityMars"));
    }

    @GetMapping
    public CollectionModel<EntityModel<CuriosityMars>> listAll() {
        List<EntityModel<CuriosityMars>> list = curiosityMarsService.listAll().stream()
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(CuriosityMarsController.class).getCustomerById(employee.getId())).withSelfRel(),
                        linkTo(methodOn(CuriosityMarsController.class).listAll()).withRel("listAll")))
                .collect(Collectors.toList());

        return CollectionModel.of(list, linkTo(methodOn(CuriosityMarsController.class).listAll()).withSelfRel());
    }
}
