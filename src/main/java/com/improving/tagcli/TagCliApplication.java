package com.improving.tagcli;


import com.improving.tagcli.Models.Emote;
import com.improving.tagcli.database.DatabaseClient;
import com.improving.tagcli.database.OldSchoolDatabaseClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

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
//		databaseClient.insterIntoTable();
//		databaseClient.readWeaponsFromTable();

        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to [1] Add Data, [2] Read Data");
        int choice = input.nextInt();
        boolean loop = true;
        while(loop)
            if (choice == 1) {
                System.out.println("Would you like to [1] Add Emote, [2] Add Weapon");
                choice = input.nextInt();
                if (choice == 1) {
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Whatcha want yer prompt ta be, pardner?");
                    String prompt = input2.nextLine();
                    System.out.println("Alright now, whattakinda text do you want in there little feller?");
                    String text = input2.nextLine();
                    Emote emote = new Emote(prompt, text);

                    databaseClient.insertIntoTableEmote(emote);

                }
                if (choice == 2) {
                    databaseClient.insertIntoTableWeapon();
                }
            }
	}
}
