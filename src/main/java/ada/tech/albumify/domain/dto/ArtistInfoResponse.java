package ada.tech.albumify.domain.dto;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ArtistInfoResponse {
    private Artist artist;
    @Setter
    @Getter
    public static class Artist {
        private Bio bio;

    }
    @Setter
    @Getter
    public static class Bio {
        private String summary;

    }
}
