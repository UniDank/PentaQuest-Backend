package com.PentaProject.demoBackend;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.PentaProject.demoBackend.Model.ClassType;
import com.PentaProject.demoBackend.Model.Enemy;
import com.PentaProject.demoBackend.Model.Hero;
import com.PentaProject.demoBackend.Model.Party;
import com.PentaProject.demoBackend.Repositories.EnemyRepository;
import com.PentaProject.demoBackend.Repositories.PartyRepository;
import com.PentaProject.demoBackend.Services.EnemyService;
import com.PentaProject.demoBackend.Services.PartyService;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Demo Backend Application Integration Tests")
class DemoBackendApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EnemyRepository enemyRepository;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private EnemyService enemyService;

    @Autowired
    private PartyService partyService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
        // Clean up test data after each test
        cleanupTestEnemies();
    }

    @Nested
    @DisplayName("Enemy Repository Tests")
    class EnemyRepositoryTests {

        @Test
        @DisplayName("Should save enemy and verify data integrity")
        void shouldSaveEnemyAndVerifyIntegrity() {
            // Given
            Enemy expectedEnemy = enemyService.generateEnemy(
                    "TestEnemy", 0, 2, 3,
                    4, 3, 5, 1, ClassType.Tank);

            // When
            Enemy savedEnemy = enemyRepository.findByName(expectedEnemy.getName());

            // Then
            assertThat(savedEnemy).isNotNull();
            assertThat(savedEnemy.getName()).isEqualTo(expectedEnemy.getName());
        }

        @Test
        @DisplayName("Should delete enemy successfully")
        void shouldDeleteEnemySuccessfully() {

            Enemy enemy = enemyService.generateEnemy(
                    "TestEnemy", 0, 2, 3,
                    4, 3, 5, 1, ClassType.Tank);

            enemyRepository.deleteByName(enemy.getName());

            assertThat(enemyRepository.findByName(enemy.getName())).isNull();
        }
    }

    @Nested
    @DisplayName("Database Connection Tests")
    class DatabaseConnectionTests {

        @Test
        @DisplayName("Should successfully connect to party repository")
        void shouldConnectToPartyRepository() {
            // When & Then
            assertThatCode(() -> partyRepository.findAll())
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Should successfully connect to enemy repository")
        void shouldConnectToEnemyRepository() {
            // When & Then
            assertThatCode(() -> enemyRepository.findAll())
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("Party Controller Integration Tests")
    class PartyControllerTests {

        @Test
        @DisplayName("Should return OK when getting all parties")
        void shouldReturnOkWhenGettingAllParties() throws Exception {

            Party party = partyService.generateParty(9999,
                    List.of(new Hero("TestHero", 0, 1, 2, 3, 4, 5, 6, ClassType.Tank)),
                    List.of());

            partyService.insertParty(party);

            mockMvc.perform(get("/api/v1/party")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));

            partyService.deleteParty();
        }

        @Test
        @DisplayName("Should return OK when inserting new party")
        void shouldReturnOkWhenInsertingNewParty() throws Exception {
            // Given
            String partyPayload = createTestPartyPayload();

            // When & Then
            mockMvc.perform(post("/api/v1/party")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(partyPayload))
                    .andExpect(status().isOk())
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("Enemy Controller Integration Tests")
    class EnemyControllerTests {

        @Test
        @DisplayName("Should return OK when getting enemies by level")
        void shouldReturnOkWhenGettingEnemiesByLevel() throws Exception {
            // Given
            int testLevel = 2;

            // When & Then
            mockMvc.perform(get("/api/v1/{level}/enemies", testLevel)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("Error Handling Tests")
    class ErrorHandlingTests {

        @Test
        @DisplayName("Should return 404 for non-existent endpoints")
        void shouldReturn404ForNonExistentEndpoints() throws Exception {
            mockMvc.perform(get("/api/v1/nonexistent")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }
    }

    // Helper methods
    private void cleanupTestEnemies() {
        try {
            enemyRepository.deleteByName("TestEnemy");
        } catch (Exception e) {
            // Ignore cleanup errors
        }
    }

    private String createTestPartyPayload() {
        return """
                {
                  "id_stage": 0,
                  "members": [
                    {
                      "name": "string",
                      "attack": 0,
                      "defense": 0,
                      "health": 0,
                      "mana": 0,
                      "agility": 0,
                      "range": 0,
                      "category": "Archer",
                      "aps": 0
                    }
                  ],
                  "bag": [
                    {
                      "name": "string",
                      "type": "string",
                      "value": 0,
                      "quantity": 0
                    }
                  ]
                }
                """;
    }
}