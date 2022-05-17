package dashmap.entity.quest

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestRepository : JpaRepository<Quest, Long> {
    fun findByFieldAndId(field: String, id: Long): Quest?
}