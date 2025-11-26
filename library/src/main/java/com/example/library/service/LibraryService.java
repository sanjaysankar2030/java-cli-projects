package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.Member;
import com.example.library.model.BorrowingRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<BorrowingRecord> borrowingRecords = new ArrayList<>();
    public List<Book> getAllBooks(){
        return books;
    }
    public Optional<Book> getBookbyId(Long id){
        return books.stream().filter(book->book.getId().equals(id)).findFirst();
    }
    //Add book
    public void addBook(Book book){
        books.add(book);
    }
    //Delete book
    public void deleteBook(Long id){
        books.removeIf(book->book.getId().equals(id));
    }
    //Update book
    public void updateBook(Book updatebook){
        for(int i=0;i<books.size();i++){
            Book book=books.get(i);
            if(book.getId().equals(updatebook.getId())){
                books.set(i,updatebook);
                break;
            }

        }
    }
    // Members
    // Return all members
    public List<Member> returnMember(){
        return members;
    }
    public Optional<Member> getMemberById(Long id){
        return members.stream().filter(member -> member.getId().equals(id)).findFirst();
    }
    public void addMember(Member member){
        members.add(member);
    }
    public void deleteMember(Long id){
        members.removeIf(member->member.getId().equals(id));
    }
    public void updateMember(Member updateMember){
        for(int i=0;i< members.size();i++){
            Member member=members.get(i);
            if(member.getId().equals(updateMember.getId())){
                members.set(i,updateMember);
                break;
            }
        }
    }

}