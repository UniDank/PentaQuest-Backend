package com.PentaProject.demoBackend;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.PentaProject.demoBackend.Model.Ability;
import com.PentaProject.demoBackend.Model.ClassType;
import com.PentaProject.demoBackend.Model.Enemy;
import com.PentaProject.demoBackend.Repositories.AbilityRepository;
import com.PentaProject.demoBackend.Repositories.EnemyRepository;
import com.PentaProject.demoBackend.Repositories.PartyRepository;
import com.PentaProject.demoBackend.Repositories.SaveRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
@EnableMongoRepositories
@OpenAPIDefinition(info = @Info(title = "PentaQuest Backend", version = "0.0.1", description = "API for the game"), servers = {
		@Server(url = "http://localhost:8080", description = "Local server")
})
public class DemoBackendApplication {
	private EnemyRepository meleeEnemyRepository;
	private AbilityRepository abilityRepository;
	private PartyRepository partyRepository;
	private SaveRepository saveRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoBackendApplication.class, args);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> builder
				.serializationInclusion(JsonInclude.Include.NON_NULL)
				.serializers();
	}

	@Bean
	public Resource enemyResource() {
		return new ClassPathResource("valuestage.json");
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			abilityRepository.deleteAll();
			abilityRepository.saveAll(Stream
					.of(new Ability("fireball", 20, ""), new Ability("lavacast", 60, "")).toList());
			meleeEnemyRepository.deleteAll();
			meleeEnemyRepository.saveAll(
					Stream.of(
							new Enemy("Bidoof", 40, 20, 5, 15, 2, 2, 10, ClassType.Thief),
							new Enemy("Ekans", 25, 5, 10, 5, 2, 1, 4, ClassType.Tank),
							new Enemy("Starly", 20, 25, 5, 10, 1, 3, 2, ClassType.Archer),
							new Enemy("Carnivine", 23, 25, 15, 15, 1, 4, 60, ClassType.Mage),
							new Enemy("Beedrill", 23, 25, 15, 15, 1, 2, 60, ClassType.Melee),
							new Enemy("Glitch", 23, 25, 15, 15, 1, 1, 60, ClassType.Tank),
							new Enemy("Admin", 99, 99, 99, 99, 99, 99, 99, ClassType.Unknown),
							new Enemy("Regitare", 23, 25, 15, 15, 1, 4, 60, ClassType.Mage)).toList());
			partyRepository.deleteAll();
			// List<Hero> _default = List.of(
			// new Hero("Marcurion",20,4,5,20,24,90),
			// new Hero("Danblos",20,4,1,4,52,3),
			// new Hero("Agoraco",1,1,1,1,1,1),
			// new Hero("Gioxon",10,10,10,10,10,10),
			// new Hero("Claphos",99,99,99,99,99,99)
			// );
			// partyRepository.save(
			// new Party(0, _default, List.of(
			// new Item("potion","potion-red",20,5),
			// new Item("sbrutto", "potion-blue",90,9),
			// new Item("sbiscio","potion-green",82,90),
			// new Item("lira magica del cagasburro", "lyre",1,1)
			// ))
			// );
			saveRepository.deleteAll();
		};
	}

}
