package ru.vtb.Last.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.vtb.Last.dto.PremiereDto;
import ru.vtb.Last.entity.PremiereEntity;
import ru.vtb.Last.mapper.PremiereTransformer;
import ru.vtb.Last.services.PremiereRepoService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/premieres")
public class PremiereController {

    private final PremiereRepoService premiereRepoService;
    private final PremiereTransformer premiereTransformer;

    @Autowired
    public PremiereController(PremiereRepoService premiereRepoService, PremiereTransformer premiereTransformer) {
        this.premiereRepoService = premiereRepoService;
        this.premiereTransformer = premiereTransformer;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping
    public Collection<PremiereDto> findAllPremieres() {
        final Collection<PremiereEntity> allPremieres = premiereRepoService.findAll();
        final List<PremiereDto> result = new ArrayList<>(allPremieres.size());

        for (PremiereEntity p : allPremieres) {
            result.add(premiereTransformer.toPremiereDto(p));
        }
        return result;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/{name}")
    public PremiereDto findPremiereByName(@PathVariable("name") String name) {
        return premiereTransformer.toPremiereDto(premiereRepoService.findByName(name));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PremiereDto addPremiere(@RequestBody PremiereDto premiereDto) {
        return premiereTransformer.toPremiereDto(premiereRepoService.addPremiere(premiereTransformer.toPremiere(premiereDto)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{name}")
    public void deletePremiere(@PathVariable("name") String name) {
        premiereRepoService.deletePremiere(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PremiereDto changePremiere(@RequestBody PremiereDto premiereDto) {
        return premiereTransformer.toPremiereDto(premiereRepoService.changePremiere(premiereTransformer.toPremiere(premiereDto)));
    }


    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/buytikets")
    public PremiereDto buyTickets(@RequestParam String premiereName, @RequestParam Integer numTikets) {
        if (premiereRepoService.buyTickets(premiereName, numTikets)) {
            return premiereTransformer.toPremiereDto(premiereRepoService.findByName(premiereName));
        }
        return null;
    }


}
