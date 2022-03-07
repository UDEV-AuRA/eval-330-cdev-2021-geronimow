package com.ipiecoles.java.eval.th330.controller;

import com.ipiecoles.java.eval.th330.model.Artist;
import com.ipiecoles.java.eval.th330.repository.ArtistRepository;
import com.ipiecoles.java.eval.th330.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller

@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/{id}")
    public ModelAndView searchById(@PathVariable Long id) {
        ModelAndView model = new ModelAndView("detail");
        model.addObject("artist", artistService.findById(id));
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/artists", params = "name")
    public ModelAndView searchByName(@RequestParam String name) {
        ModelAndView model = new ModelAndView("detail");
        model.addObject("artist", artistService.findByName(name));
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete")
    public RedirectView deleteArtist (@PathVariable Long id){
        artistService.deleteArtist(id);
        return new RedirectView("/artists?page=0&size=10&sortProperty=name&sortDirection=ASC");
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{id}/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)

    public RedirectView createArtist(Artist artist){
        if(artist.getId() == null){
            artist = artistService.creerArtiste(artist);
        }
        else {
            artist = artistService.updateArtiste(artist.getId(), artist);
        }
        return new RedirectView("/artists/" + artist.getId());
    }


    @GetMapping("")
    public ModelAndView listArtists(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String sortProperty,
            @RequestParam String sortDirection
    ){
        Page<Artist> artists  = artistService.findAllArtists(page, size, sortProperty, sortDirection);
        ModelAndView model = new ModelAndView("list");
        model.addObject("start", page * size + 1);
        model.addObject("end", page * size + artists.getNumberOfElements());
        model.addObject("artists", artists);
        return model;
    }

}
