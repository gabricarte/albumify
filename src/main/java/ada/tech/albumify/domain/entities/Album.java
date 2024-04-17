package ada.tech.albumify.domain.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="albums")

public class Album {
    private String name;
    private String artist;
    @Column(name = "\"year\"")
    private int year;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
