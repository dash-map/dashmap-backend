package dashmap.auth.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import lombok.Builder

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class AccessTokenRequestDTO(
    val clientId: String,
    val clientSecret: String,
    val code: String
) {
}