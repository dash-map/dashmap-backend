package dashmap.auth.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import lombok.ToString

@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AccessTokenResponseDTO(
    val accessToken: String,
    val tokenType: String,
    val scope: String
) {
}