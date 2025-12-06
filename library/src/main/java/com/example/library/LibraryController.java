package com.example.library;
import com.example.library.model.Book;
import com.example.library.model.Member;
import com.example.library.model.BorrowingRecord;
import com.example.library.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LibraryController {
    private static final Logger logger= LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private LibraryService Services;

    //++++++++++++++++++++ Endpoints ++++++++++++++++++++++++
    //Get all books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getallBooks(){
        List<Book> listbooks=Services.getAllBooks();
        logger.info("The list of books returned"+listbooks);
        return new  ResponseEntity<>(listbooks,HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookId(@PathVariable Long id){
        Optional<Book> idBook=Services.getBookbyId(id);
        logger.info("The book returned"+idBook);
        return ResponseEntity.of(idBook);
    }
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Services.addBook(book);
        logger.info("The book was added");
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    @PostMapping("/books/updateBook")
    public ResponseEntity<Book>updateBook(Long id,@RequestBody Book updatingBook){
        if(Services.getBookbyId(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatingBook.setId(id);
        Services.updateBook(updatingBook);
        logger.info("The book is updated");
        return new ResponseEntity<>(updatingBook,HttpStatus.OK);
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        if(!Services.getBookbyId(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Services.deleteBook(id);
        logger.info("THe book has been deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //++++++++++++++++++++++++++++Member Contoroller+++++++++++++++++++++++++++++
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = Services.returnMember();
        logger.info("The members in the system " + members);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member = Services.getMemberById(id);
        logger.info("The member you retrieved "+member);
        return ResponseEntity.of(member);//we did not create a object for this class?
    }

    @PostMapping("/members")
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        Services.addMember(member);
        logger.info("The new member is added");
        return new ResponseEntity<Member>(member,HttpStatus.CREATED);//why did we return the same member we requested here
    }
}
