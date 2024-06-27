package ada.tech.albumify.domain.mappers;

import ada.tech.albumify.domain.dto.AlbumDto;
import ada.tech.albumify.domain.dto.SummaryDto;
import ada.tech.albumify.domain.entities.Album;

public class AlbumMapper {
    public static Album toEntity(AlbumDto dto){
        return Album
                .builder()
                .name(dto.getName())
                .artist(dto.getArtist())
                .summary(dto.getSummary())
                .build();
    }

    public static AlbumDto toDto(Album entity, SummaryDto summary) {
        return new AlbumDto(
                entity.getId(),
                entity.getName(),
                entity.getArtist(),
                summary.getSummary()
        );
    }

}
