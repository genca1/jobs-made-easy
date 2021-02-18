package tr.com.aktifbank.jobmadeeasy.repositories;

import org.springframework.data.repository.CrudRepository;
import tr.com.aktifbank.jobmadeeasy.model.Job;

public interface JobsRepository extends CrudRepository<Integer, Job> {
}
