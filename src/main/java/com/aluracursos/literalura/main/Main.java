package com.aluracursos.literalura.main;

import com.aluracursos.literalura.DTO.AuthorDTO;
import com.aluracursos.literalura.DTO.BookDTO;
import com.aluracursos.literalura.models.Author;
import com.aluracursos.literalura.service.ApiService;
import com.aluracursos.literalura.service.BookService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;


@Component
public class Main {

    //It allows you to read input from various sources like the keyboard, files, or even strings.
    Scanner scanner = new Scanner(System.in);


    private final BookService bookService;

    private final ApiService apiService;

    public Main(BookService bookService, ApiService apiService) {
        this.bookService = bookService;
        this.apiService = apiService;
    }



    public void menu() {


            //This variable allows the user to interact with the menu of options
            int option = -1;

            String menu = """
                    ******* Welcome to LITERALURA ********
                    1.- Search for a book by title       *
                    2.- List registered books            *
                    3.- List registered authors          *
                    4.- List authors alive during period *
                    5.- Top 5 books                      *
                    6.- Search books by language         *
                    7.- Search author by name            *
                                     
                                        
                    0. Exit;
                    """;

            while (true) {

                System.out.println(menu);
                option = scanner.nextInt();
                scanner.nextLine();


                switch (option) {

                    case 0:
                        System.out.println("Thank you for using Literalura. :)");
                        System.exit(0);
                    case 1:
                        searchBookByTitle();
                        break;
                    case 2:
                        registeredBooks();
                        break;
                    case 3:
                        registeredAuthors();
                        break;
                    case 4:
                        findAuthorsAliveDuringPeriod();
                        break;
                    case 5:
                        top5Books();
                        break;
                    case 6:
                        findBooksByLanguage();
                        break;
                    case 7:
                        searchAuthorByName();
                        break;
                    default:
                        System.out.println("invalid option");

                }


            }

    }



    public void searchBookByTitle(){

        String book;
        System.out.println("Enter the name of the book you wish to search for");
        book = scanner.nextLine();

        List<BookDTO> bookDTOS = apiService.fetchBooksByTitleOrAuthor(book);

        if (bookDTOS.isEmpty()){
            System.out.println("Book not found");

        }

        bookDTOS.forEach(System.out::println);



    }

    //
    public void registeredBooks(){

        List<BookDTO> bookDTOS = bookService.getAllBooks();
        bookDTOS.forEach(System.out::println);
    }


    public void registeredAuthors(){
        List<AuthorDTO> authorDTOS = bookService.getAllAuthors();
        authorDTOS.forEach(System.out::println);
    }

    public void findAuthorsAliveDuringPeriod(){

        int start, end;
        System.out.println("Enter the range or period from which you wish to search for author(s)");

        System.out.println("Enter start year: ");
        start = scanner.nextInt();

        System.out.println("Enter end year");
        end = scanner.nextInt();

        try{
            List<AuthorDTO> authorDTOS = bookService.findAuthorsAliveDuringPeriod(start, end);
            for (AuthorDTO authorDTO : authorDTOS) System.out.println(authorDTO);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }


    }

    public void findBooksByLanguage(){

        String language;
        String availableLanguages = """
               Choose from the language options of the book you wish to search for
                
                en - English
                es - Spanish
                fr - French
                it - Italian
                pt - Portuguese
                """;

        System.out.println(availableLanguages);
        language = scanner.nextLine();

        try {
            List<BookDTO> bookDTOS = bookService.findBooksByLanguage(language);
            bookDTOS.forEach(System.out::println);
        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }

    }


    public void top5Books(){
        List<BookDTO> bookDTOS = bookService.top5Books();
        bookDTOS.forEach(System.out::println);
    }



    public void searchAuthorByName(){

        String name;
        System.out.println("Enter the name of the author you want to search for: ");
        name = scanner.nextLine();

        try{
            AuthorDTO authorDTO = bookService.findAuthorByName(name);
            System.out.println(authorDTO);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }

}


