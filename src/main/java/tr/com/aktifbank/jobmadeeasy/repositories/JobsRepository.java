package tr.com.aktifbank.jobmadeeasy.repositories;

import org.springframework.data.repository.CrudRepository;
import tr.com.aktifbank.jobmadeeasy.model.Jobs;

public interface JobsRepository extends CrudRepository<Integer, Jobs> {
}
