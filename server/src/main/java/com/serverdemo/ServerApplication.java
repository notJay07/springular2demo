package com.serverdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ServerApplication implements CommandLineRunner{
//public class ServerApplication{
	@Autowired
	private BookRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);    
	}
	
	
	@Override
	public void run(String... arg0) throws Exception{
		this.repo.deleteAll();
		
		this.repo.save(new Book("John", "Java", "Open Source", 10));
		this.repo.save(new Book("Cena", "Docker", "Container", 700));
		this.repo.save(new Book("Frank", "Linux", "Freeware", 90));
		this.repo.save(new Book("Danny", "Angular", "CLI", 89));
		
		for(Book book : this.repo.findAll()){
			System.out.println(book);
		}
	}
	

}

