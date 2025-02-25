package com.github.ramezch.rickandmorty;

import java.util.List;

public record CharacterResponse(List<Character> results, int id, String name, String species, String status) {
}
