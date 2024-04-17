package ada.tech.albumify.mappers;

import ada.tech.albumify.domain.dto.AlbumDto;
import ada.tech.albumify.domain.entities.Album;

public class AlbumMapper {
    public static Album toEntity(AlbumDto dto){
        return Album
                .builder()
                .name(dto.getName())
                .artist(dto.getArtist())
                .year(dto.getYear())
                .build();
    }

    public static AlbumDto toDto(Album entity) {
        return new AlbumDto(
                entity.getName(),
                entity.getArtist(),
                entity.getYear(),
                entity.getId()
        );
    }

}
