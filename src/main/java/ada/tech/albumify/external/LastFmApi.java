package ada.tech.albumify.external;

import ada.tech.albumify.domain.dto.ArtistInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "LastFm", url = "https://ws.audioscrobbler.com/2.0")
public interface LastFmApi {
    @GetMapping
    ArtistInfoResponse getArtistInfo(
            @RequestParam("method") String method,
            @RequestParam("artist") String artist,
            @RequestParam("api_key") String apiKey,
            @RequestParam("format") String format
    );
}
