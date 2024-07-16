package ada.tech.albumify.controllers;


import ada.tech.albumify.domain.dto.AlbumDto;
import ada.tech.albumify.domain.dto.exceptions.AlbumNotFoundException;
import ada.tech.albumify.domain.dto.exceptions.AlreadyExistsException;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.service.IAlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albumify")

public class AlbumController {
    private final IAlbumService service;

    @Autowired
    public AlbumController(IAlbumService service){
        this.service = service;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Album> createAlbum(
            @RequestBody AlbumDto request,
            @PathVariable("userId") int userId
    )throws NotFoundException, AlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAlbum(request, userId));
    }
    @GetMapping
    public ResponseEntity<List<Album>> readAllAlbums(){
        return ResponseEntity.ok(service.readAllAlbums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> readAlbum(
            @PathVariable("id") int id
    )  throws AlbumNotFoundException {
        return ResponseEntity.ok(service.readAlbum(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(
            @PathVariable("id") int id,
            @RequestBody AlbumDto request
    ) throws AlbumNotFoundException {
        final Album album = service.updateAlbum(id, request);
        if (album == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(
            @PathVariable("id") int id
    ) throws AlbumNotFoundException {
        service.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

}
