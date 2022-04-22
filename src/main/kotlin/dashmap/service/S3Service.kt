package dashmap.service

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

@Component
class S3Service(
    val amazonS3Client: AmazonS3Client
) {
    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    fun upload(multipartFile: MultipartFile, dirName: String): String {
        val uploadFile =
            convert(multipartFile) ?: throw IllegalArgumentException("error: MultipartFile -> File Convert Fail")

        return upload(uploadFile, dirName)
    }

    private fun upload(uploadFile: File, dirName: String): String {
        val fileName = "$dirName/" + UUID.randomUUID() + uploadFile.name
        val uploadImageUrl = putS3(uploadFile, fileName)
        removeNewFile(uploadFile)
        return uploadImageUrl
    }

    private fun putS3(uploadFile: File, fileName: String): String {
        amazonS3Client.putObject(
            PutObjectRequest(
                bucket,
                fileName,
                uploadFile
            ).withCannedAcl(CannedAccessControlList.PublicRead)
        )
        return amazonS3Client.getUrl(bucket, fileName).toString()
    }

    private fun convert(file: MultipartFile): File? {
        val convertFile = File(System.getProperty("user.dir") + "/" + file.originalFilename)
        if (convertFile.createNewFile()) {
            val fos = FileOutputStream(convertFile)
            fos.write(file.bytes)

            return convertFile
        }

        return null;
    }

    private fun removeNewFile(targetFile: File) {
        if (targetFile.delete()) {
            return
        }
    }
}