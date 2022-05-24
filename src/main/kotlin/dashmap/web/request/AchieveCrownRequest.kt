package dashmap.web.request

data class AchieveCrownRequest(
    val userId: Long,
    val field: String
) {
}