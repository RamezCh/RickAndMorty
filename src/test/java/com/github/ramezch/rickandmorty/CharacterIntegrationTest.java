package com.github.ramezch.rickandmorty;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class CharacterIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MockRestServiceServer mockServer;

    @Test
    void getCharacters() throws Exception {
        mockServer.expect(requestTo("https://rickandmortyapi.com/api/character"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                                {
                                    "info": {
                                        "count": 32,
                                        "pages": 42
                                    },
                                    "results": [
                                        {
                                            "id": 1,
                                            "name": "Rick Sanchez",
                                            "status": "Alive",
                                            "species": "Human"
                                        }
                                    ]
                                }
                                """,
                        MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                             {
                                 "id": 1,
                                 "name": "Rick Sanchez",
                                 "status": "Alive",
                                 "species": "Human"
                             }
                        ]
                        """));
    }

    @Test
    void getCharacter() throws Exception {
        int characterId = 1;
        mockServer.expect(requestTo("https://rickandmortyapi.com/api/character/" + characterId))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                                {
                                    "id": 1,
                                    "name": "Rick Sanchez",
                                    "status": "Alive",
                                    "species": "Human"
                                }
                                """,
                        MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters/" + characterId))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "name": "Rick Sanchez",
                            "status": "Alive",
                            "species": "Human"
                        }
                        """));
    }

    @Test
    void getFilteredCharacters() throws Exception {
        String status = "Alive";

        mockServer.expect(requestTo("https://rickandmortyapi.com/api/character/?status=" + status))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                            {
                                "info": {
                                    "count": 32,
                                    "pages": 42
                                },
                                "results": [
                                    {
                                        "id": 1,
                                        "name": "Rick Sanchez",
                                        "status": "Alive",
                                        "species": "Human"
                                    }
                                ]
                            }
                            """,
                        MediaType.APPLICATION_JSON));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters?status=" + status))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [
                         {
                             "id": 1,
                             "name": "Rick Sanchez",
                             "status": "Alive",
                             "species": "Human"
                         }
                    ]
                    """));
    }
}