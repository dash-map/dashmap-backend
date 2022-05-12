package dashmap.entity.roadmap

import javax.persistence.*

@Entity
@Table(name = "roadmap")
class RoadMap(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String?,
    val url: String?
) {
}