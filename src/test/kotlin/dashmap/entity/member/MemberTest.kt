package dashmap.entity.member

import dashmap.auth.dto.OAuthUserResponseDTO
import dashmap.entity.crown.Crown
import dashmap.entity.crown.CrownRepository
import dashmap.entity.progress.Progress
import dashmap.entity.progress.ProgressRepository
import org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import javax.transaction.Transactional

@SpringBootTest
@Transactional
internal class MemberTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var crownRepository: CrownRepository

    @Autowired
    private lateinit var progressRepository: ProgressRepository

    @Test
    fun memberLazyLoading() {
        val response = OAuthUserResponseDTO("손윤석", "asdasdawdawd", "dbstjr5517@naver.com")
        val newMember = Member.of(response)
        memberRepository.save(newMember)
        crownRepository.save(Crown.of(newMember))
        progressRepository.save(Progress.of(newMember))
        val member: Member = newMember.name?.let { memberRepository.findByName(it) } as Member
        val crown = crownRepository.findByMember(member)
        val progress = progressRepository.findByMember(member)

        assertThat(member)
            .isNotNull
            .isEqualTo(newMember)
    }
}