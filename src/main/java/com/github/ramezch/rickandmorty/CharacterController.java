package com.github.ramezch.rickandmorty;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class CharacterController {
    private final CharacterService service;

    @GetMapping
    public List<Character> getCharacters(@RequestParam(required = false) String status) {
        if (status == null || status.isEmpty())
            return service.getCharacters();
        return service.getFilteredCharacters(status);
    }


    @GetMapping("{id}")
    public Character getCharacter(@PathVariable String id) {
        return service.getCharacter(id);
    }
}
