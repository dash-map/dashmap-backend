package dashmap.web.request

data class QuestRequest(
    val userId: Long,
    val field: String,
    val quest: Long
) {
}