package dashmap.entity.progress

import dashmap.entity.member.Member
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProgressRepository : CrudRepository<Progress, Long> {
    fun findByMember(member: Member): Progress
}