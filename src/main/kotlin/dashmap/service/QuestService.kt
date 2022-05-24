package dashmap.service

import dashmap.entity.crown.CrownRepository
import dashmap.entity.member.Member
import dashmap.entity.member.MemberRepository
import dashmap.entity.problem.QuestionRepository
import dashmap.entity.progress.ProgressRepository
import dashmap.entity.quest.QuestRepository
import dashmap.web.request.QuestRequest
import dashmap.web.response.QuestCountResponse
import dashmap.web.response.QuestResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class QuestService(
    val questRepository: QuestRepository,
    val questionRepository: QuestionRepository,
    val memberRepository: MemberRepository,
    val crownRepository: CrownRepository,
    val progressRepository: ProgressRepository
) {
    @Transactional
    fun findQuestById(request: QuestRequest): QuestResponse {
        val quest = questRepository.findByFieldAndId(request.field, request.quest)
        quest?.let {
            val member = memberRepository.findByIdOrNull(request.userId)
            if (member != null && request.quest > 1) {
                increaseFieldCount(member, request.field, request.quest)
            }
            return QuestResponse(it.name, it.problem, questionRepository.findAllByQuest(quest), quest.answer)
        } ?: throw Exception("${request.field}'s ${request.quest} Problem is not Exist")
    }

    fun findQuestCountByField(): QuestCountResponse {
        return QuestCountResponse(
            questRepository.findAllByField("fe")!!.size,
            questRepository.findAllByField("be")!!.size,
            questRepository.findAllByField("aos")!!.size,
            questRepository.findAllByField("ios")!!.size,
            questRepository.findAllByField("ai")!!.size
        )
    }

    private fun increaseFieldCount(member: Member, field: String, quest: Long) {
        val pro = progressRepository.findByMember(member)
        val changeValue = (quest - 1).toInt()
        when (field) {
            "fe" -> pro.feCount = changeValue
            "be" -> pro.beCount = changeValue
            "aos" -> pro.aosCount = changeValue
            "ios" -> pro.iosCount = changeValue
            "ai" -> pro.aiCount = changeValue
        }
    }

    private fun achieveCrown(member: Member, field: String) {
        val crown = crownRepository.findByMember(member)
        when (field) {
            "fe" -> crown.isFeClear = true
            "be" -> crown.isBeClear = true
            "aos" -> crown.isAosClear = true
            "ios" -> crown.isIosClear = true
            "ai" -> crown.isAiClear = true
        }
    }
}