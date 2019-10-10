package com.improving.tagcli;


import com.improving.tagcli.database.DatabaseClient;
import com.improving.tagcli.database.OldSchoolDatabaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TagCliApplication implements CommandLineRunner {

	@Autowired
	private final DatabaseClient databaseClient;

	public TagCliApplication(DatabaseClient databaseClient) {
		this.databaseClient = databaseClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(TagCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		databaseClient.insterIntoTable();
		databaseClient.readWeaponsFromTable();
	}
}
