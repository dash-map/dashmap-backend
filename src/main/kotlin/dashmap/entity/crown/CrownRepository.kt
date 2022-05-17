package dashmap.entity.crown

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CrownRepository : CrudRepository<Crown, Long> {
}