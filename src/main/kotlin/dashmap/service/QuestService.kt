package dashmap.service

import dashmap.entity.member.Member
import dashmap.entity.member.MemberRepository
import dashmap.entity.quest.QuestRepository
import dashmap.web.request.QuestRequest
import dashmap.web.response.QuestResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuestService(
    val questRepository: QuestRepository,
    val memberRepository: MemberRepository
) {
    fun findQuestById(request: QuestRequest): QuestResponse {
        val quest = questRepository.findByFieldAndId(request.field, request.quest)
        quest?.let {
            val member = memberRepository.findByIdOrNull(request.userId)
            if (member != null) {
                increaseFieldCount(member, request.field)
            }
            return QuestResponse(it.name, it.problem, it.questions)
        } ?: throw Exception("${request.field}'s ${request.quest} Problem is not Exist")
    }

    private fun increaseFieldCount(member: Member, field: String) {
        val pro = member.progress
        when (field) {
            "fe" -> pro.feCount + 1
            "be" -> pro.beCount + 1
            "aos" -> pro.aosCount + 1
            "ios" -> pro.iosCount + 1
            "ai" -> pro.aiCount + 1
        }
    }
}