package ada.tech.albumify.domain.dto;

import lombok.Data;

@Data
public class ArtistInfoResponse {
    private Artist artist;
    @Data
    public static class Artist {
        private Bio bio;

    }
    @Data
    public static class Bio {
        private String summary;

    }
}
