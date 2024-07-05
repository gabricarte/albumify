package ada.tech.albumify.controllers;

import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.service.IAlbumUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albumify/user")
public class AlbumUserController {

    private final IAlbumUserService service;
    @GetMapping("/{userId}")
    public ResponseEntity<List<Album>> readAlbumsByUserId(
            @PathVariable("userId") int userId
    ) {
        return ResponseEntity.ok(service.readAlbumsByUserId(userId));
    }
    @DeleteMapping("/{userId}/albums/{albumId}")
    public ResponseEntity<Void> deleteAlbumFromUser(
            @PathVariable("userId") int userId,
            @PathVariable("albumId") int albumId
    ) throws NotFoundException {
        service.deleteAlbumFromUser(userId, albumId);
        return ResponseEntity.ok().build();
    }
}
