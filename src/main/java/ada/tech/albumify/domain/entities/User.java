package ada.tech.albumify.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "\"username\"", unique = true)
    private String username;

    @Column(name = "\"e-mail\"", unique = true)
    private String email;

    @Column(name = "\"password\"")
    private String password;

    @Lob
    @Column(name = "\"profile_image\"")
    private byte[] profileImage;
}
