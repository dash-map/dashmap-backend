package dashmap.entity.image

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "s3Image")
class S3Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column()
    val url: String?
) {
}