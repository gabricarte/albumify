package ada.tech.albumify.controllers;


import ada.tech.albumify.domain.dto.AlbumDto;
import ada.tech.albumify.domain.dto.exceptions.NotFoundException;
import ada.tech.albumify.domain.entities.Album;
import ada.tech.albumify.service.IAlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")

public class AlbumController {
    private final IAlbumService service;

    @Autowired
    public AlbumController(IAlbumService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AlbumDto> createAlbum(
            @RequestBody AlbumDto request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAlbum(request));
    }
    @GetMapping
    public ResponseEntity<List<AlbumDto>> readAllAlbums(){
        return ResponseEntity.ok(service.readAllAlbums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> readAlbum(
            @PathVariable("id") int id
    )  throws NotFoundException {
        return ResponseEntity.ok(service.readAlbum(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumDto> updateAlbum(
            @PathVariable("id") int id,
            @RequestBody AlbumDto request
    ) throws NotFoundException {
        final AlbumDto album = service.updateAlbum(id, request);
        if (album == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(
            @PathVariable("id") int id
    ) throws NotFoundException {
        service.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

}
