package com.serverdemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	private BookRepository repo;
	
	@Autowired
	public IndexController(BookRepository repo) {
		this.repo = repo;
	}
	
	/*
	@RequestMapping(value = { "/books" }, method = RequestMethod.GET, headers="Accept=application/json")
	@CrossOrigin(origins="http://localhost:4200",maxAge = 3600)
	public List<Book> getAllBooks(){
		System.out.println("Hello");
		/*List<Book> paper =  new ArrayList<>();
		paper.add(new Book("John", "Java", "Open Source", 10));
		paper.add(new Book("Cena", "Docker", "Container", 700));
		paper.add(new Book("Frank", "Linux", "Freeware", 90));
		return paper;
		return this.repo.findAll();
	}
	 */
	
	@RequestMapping(value = { "/books" }, method = RequestMethod.GET)
	@CrossOrigin(origins="http://localhost:4200", maxAge = 3600)
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> book = this.repo.findAll();
		
		System.out.println("Hello");
		if(book.isEmpty()){
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Book>>(book, HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/books" }, method = RequestMethod.POST)
	@CrossOrigin(origins="http://localhost:4200", maxAge = 3600)
	public ResponseEntity<Void> addBook(@RequestBody Book book){
		try{
			System.out.println(book.toString());
			System.out.println("ID: " + book.getId() + "Title: " + book.getTitle() + "Author: " + book.getAuthor() + "Description: " + book.getDescription() + "Price: " + book.getPrice());
			repo.save(book);
		}catch(Exception ex){
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = { "/books/{bookId}" }, method = RequestMethod.DELETE)
	@CrossOrigin(origins="http://localhost:4200", maxAge = 3600)
	public ResponseEntity deleteBook (@PathVariable("bookId") long bookId){
		try{
			System.out.println("Delete Book with ID " + bookId);
			Book myBook = repo.findOne(new Long(bookId));
			if(myBook == null){
				System.out.println("Unable to delete. Book with id " + bookId + " not found");
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			repo.delete(myBook);
		}catch(Exception ex){
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	private Book findBook(int bookId){
		Book book = repo.findOne(new Long(bookId));
		return book;
	}
	
  	@RequestMapping(value = { "/books" }, method = RequestMethod.DELETE)
	@CrossOrigin(origins="http://localhost:4200", maxAge = 3600)
	public ResponseEntity<List<Book>> deleteBookRecords(){
		System.out.println("Delete Book");
		
		repo.deleteAll();
		return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
	}
  	
  	@RequestMapping(value = { "/books/{bookId}" }, method = RequestMethod.PUT)
  	@CrossOrigin(origins="http://localhost:4200", maxAge = 3600)
  	public ResponseEntity<List<Book>> updateBook(@PathVariable("bookId") long bookId, @RequestBody Book book){
  		try{
  			System.out.println(book.toString());
  			System.out.println("Update Book " + bookId + " Record.");
  			Book bookRecord = repo.findOne(new Long(bookId));
  		
  			if(bookRecord == null){
	  			System.out.println("Book ID " + bookId + " not found");
	  			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
  			}
  		
	  		bookRecord.setTitle(book.getTitle());
	  		bookRecord.setAuthor(book.getAuthor());
	  		bookRecord.setDescription(book.getDescription());
	  		bookRecord.setPrice(book.getPrice());
	  		
	  		repo.save(bookRecord);
  		
  		}catch(Exception ex){
  			return new ResponseEntity<List<Book>>(HttpStatus.BAD_REQUEST);
  		}
  		
  		return new ResponseEntity<List<Book>>(HttpStatus.OK);
  		
  	}
	
	
	/*
	@RequestMapping("/")
	@Transactional(readOnly = true)
	public ModelAndView index(){
		List<Book> books = this.repo.findAll();
			System.out.println("Book size ::" + books.size());
			ModelAndView mav = new ModelAndView("index");
			mav.addObject("books", books);
			
			return mav;
	}
	*/
	
	

}
