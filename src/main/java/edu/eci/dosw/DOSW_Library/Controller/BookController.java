package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import edu.eci.dosw.DOSW_Library.Validator.BookValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@Tag(name = "Libros", description = "Gestión del inventario y catálogo de libros")
public class BookController {

    private final BookService bookService;
    private final BookValidator bookValidator;

    public BookController(BookService bookService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los libros")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar libro por ID")
    public Book getBookById(@PathVariable String id) {
        return bookService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo libro", description = "Valida la entrada antes de guardar")
    public Book addBook(@RequestBody Book book, @RequestParam int quantity) {
        bookValidator.validate(book);
        return bookService.save(book, quantity);
    }

    @PutMapping("/{id}/stock")
    @Operation(summary = "Actualizar stock de un libro")
    public void updateStock(@PathVariable String id, @RequestParam int quantity) {
        Book book = bookService.findById(id);
        if (book != null) {
            bookService.updateStock(book, quantity);
        } else {
            throw new RuntimeException("Libro no encontrado con ID: " + id);
        }
    }
}
