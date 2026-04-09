package edu.eci.dosw.DOSW_Library.Controller;

import edu.eci.dosw.DOSW_Library.Modelo.Book;
import edu.eci.dosw.DOSW_Library.Persistence.Mapper.BookMapper;
import edu.eci.dosw.DOSW_Library.Service.BookService;
import edu.eci.dosw.DOSW_Library.Validator.BookValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Libros", description = "Gestión del inventario y catálogo de libros")
public class BookController {

    private final BookService bookService;
    private final BookValidator bookValidator;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookValidator bookValidator, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'LIBRARIAN', 'ADMIN')")
    @Operation(summary = "Obtener todos los libros")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'LIBRARIAN', 'ADMIN')")
    @Operation(summary = "Buscar libro por ID")
    public Book getBookById(@PathVariable String id) {
        return bookService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    @Operation(summary = "Agregar un nuevo libro")
    public Book addBook(@RequestBody Book book) {
        bookValidator.validate(book);
        return bookService.save(book);
    }

    @PutMapping("/{id}/stock")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    @Operation(summary = "Actualizar stock de un libro")
    public void updateStock(@PathVariable String id, @RequestParam int quantity) {
        var bookEntity = bookService.findEntityById(id);
        if (bookEntity != null) {
            bookEntity.setAvailableStock(quantity);
            bookService.saveEntity(bookEntity);
        } else {
            throw new RuntimeException("Libro no encontrado con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMIN')")
    @Operation(summary = "Eliminar un libro permanentemente")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteById(id);
    }
}




