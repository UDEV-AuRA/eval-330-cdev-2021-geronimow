package com.ipiecoles.java.eval.th330.controller;
import com.ipiecoles.java.eval.th330.model.Album;
import com.ipiecoles.java.eval.th330.repository.AlbumRepository;
import com.ipiecoles.java.eval.th330.service.AlbumService;
import com.ipiecoles.java.eval.th330.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    @RequestMapping(method = RequestMethod.GET, value = "/albums/{id}/delete")
    public RedirectView deleteAlbum (@PathVariable Long id){
        albumService.deleteAlbum(id);
        return new RedirectView("/artists/{id}");
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/albums/update",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Album updateAlbum(
            @RequestBody Album album
    ){
        return albumRepository.save(album);
    }

}
