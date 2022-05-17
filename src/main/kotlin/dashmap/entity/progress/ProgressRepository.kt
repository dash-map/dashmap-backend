package dashmap.entity.progress

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProgressRepository : CrudRepository<Progress, Long> {
}