package dashmap.web.response

data class QuestCountResponse(
    val feCount: Int,
    val beCount: Int,
    val aosCount: Int,
    val iosCount: Int,
    val aiCount: Int
) {
}