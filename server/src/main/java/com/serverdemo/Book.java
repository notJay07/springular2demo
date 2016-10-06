package com.serverdemo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String author;
	
	private String description;
	
	private int price;
	
	
	protected Book(){
		
	}
	
	public Book(String title, String author, String description, int price){
		this.title = title;
		this.author = author;
		this.description = description;
		this.price = price;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public int getPrice(){
		return price;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	@Override
	public String toString(){
		return String.format("Book[id=%s, title='%s',author='%s',description='%s',price='%s']",id,title, author, description, price);
	}
}
