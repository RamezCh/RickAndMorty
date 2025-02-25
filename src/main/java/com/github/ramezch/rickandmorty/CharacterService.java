package com.github.ramezch.rickandmorty;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CharacterService {

    private final RestClient restClient;

    public CharacterService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public List<Character> getCharacters() {
       CharacterResponse response = restClient.get().uri("/character").retrieve().body(CharacterResponse.class);
       assert response != null;
       return response.results();
    }

    public Character getCharacter(String id) {
        CharacterResponse response = restClient.get().uri("/character/" + id).retrieve().body(CharacterResponse.class);
        assert response != null;
        return new Character(response.id(), response.name(), response.species(), response.status());
    }

    public List<Character> getFilteredCharacters(String status) {
        CharacterResponse response = restClient.get().uri("/character/?status=" + status).retrieve().body(CharacterResponse.class);
        assert response != null;
        return response.results();
    }
}
