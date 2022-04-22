package dashmap.config

import dashmap.auth.JwtAuthInterceptor
import dashmap.auth.UserIdArgumentResolver
import io.netty.resolver.DefaultAddressResolverGroup
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import reactor.netty.http.client.HttpClient

@Configuration
@EnableAutoConfiguration(exclude = [ContextInstanceDataAutoConfiguration::class])
class WebConfig(
    val jwtAuthInterceptor: JwtAuthInterceptor,
    val userIdArgumentResolver: UserIdArgumentResolver
) : WebMvcConfigurer {

    @Bean
    fun httpClient(): HttpClient {
        return HttpClient.create()
            .resolver(DefaultAddressResolverGroup.INSTANCE)
    }

    @Bean
    fun webClient(httpClient: HttpClient): WebClient {
        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("*")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(jwtAuthInterceptor)
            .addPathPatterns("/api/**")
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userIdArgumentResolver)
    }
}