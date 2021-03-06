package dashmap.web.response

import dashmap.entity.question.Question

data class QuestResponse(
    val quest: String?,
    val problem: String?,
    val questions: List<Question>,
    val answer: Long?
) {
}