package dashmap.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonS3Config {

    @Bean
    fun amazonS3Client(
        @Value("\${cloud.aws.credentials.access-key}") accessKey: String?,
        @Value("\${cloud.aws.credentials.secret-key}") secretKey: String?,
        @Value("\${cloud.aws.region.static}") region: String?,
    ): AmazonS3Client {
        val awsCreds: BasicAWSCredentials = BasicAWSCredentials(accessKey, secretKey)

        return AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(awsCreds))
            .build() as AmazonS3Client
    }
}