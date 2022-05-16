package dashmap.web.response

import dashmap.entity.problem.Question

data class QuestResponse(
    val quest: String?,
    val problem: String?,
    val questions: List<Question>?
) {
}