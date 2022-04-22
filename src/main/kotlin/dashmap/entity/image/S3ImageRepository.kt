package dashmap.entity.image

import org.springframework.data.repository.CrudRepository

interface S3ImageRepository : CrudRepository<S3Image, Long> {

}