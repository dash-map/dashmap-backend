package dashmap.auth.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import lombok.Builder

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class AccessTokenRequestDTO(
    private val clientId: String,
    private val clientSecret: String,
    private val code: String
) {
    init {
        
    }
}