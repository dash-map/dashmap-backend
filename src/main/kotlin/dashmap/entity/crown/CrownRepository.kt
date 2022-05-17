package dashmap.entity.crown

import dashmap.entity.member.Member
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CrownRepository : CrudRepository<Crown, Long> {
    fun findByMember(member: Member): Crown
}