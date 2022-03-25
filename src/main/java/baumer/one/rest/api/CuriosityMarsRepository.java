package baumer.one.rest.api;

import baumer.one.rest.api.model.CuriosityMars;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuriosityMarsRepository extends CrudRepository<CuriosityMars,Integer> {}