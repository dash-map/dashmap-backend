package dashmap.auth.dto

data class OAuthUserResponseDTO(
    val login: String,
    val avatar_url: String?,
    val email: String?
) {
}