package com.ss4.model.controller;

import com.ss4.model.dto.BookDTO;
import com.ss4.model.entity.Book;
import com.ss4.model.entity.Category;
import com.ss4.model.service.BookService;
import com.ss4.model.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;

    @GetMapping
    public String list(
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
            Model model) {

        if (page < 1) page = 1;

        int pageSize = 5;

        Pageable pageable = PageRequest.of(page - 1, pageSize);

        Page<Book> bookPage;
        if (sort.equals("asc")) {
            bookPage = bookService.findByNameContainingOrderByIdAsc(search, pageable);
        } else {
            bookPage = bookService.findByNameContainingOrderByIdDesc(search, pageable);
        }

        model.addAttribute("books", bookPage);
        model.addAttribute("search", search);
        model.addAttribute("sort", sort);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "book/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookDTO());
        model.addAttribute("categories", categoryService.findAll());
        return "book/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("book") @Valid BookDTO dto,
                         BindingResult result,
                         RedirectAttributes redirect,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "book/create";
        }

        Book existingBook = bookService.findByName(dto.getName());
        if (existingBook != null) {
            result.rejectValue("name", "error.book", "Tên sách đã tồn tại");
            model.addAttribute("categories", categoryService.findAll());
            return "book/create";
        }

        Category category = categoryService.findById(dto.getCategoryId());
        if (category == null) {
            result.rejectValue("categoryId", "error.book", "Thể loại không tồn tại");
            model.addAttribute("categories", categoryService.findAll());
            return "book/create";
        }
        Book book = new Book();
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setYear(dto.getYear());
        book.setPrice(dto.getPrice());
        book.setCategory(category);

        bookService.save(book);

        redirect.addFlashAttribute("success", "Thêm sách thành công");
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Book book = bookService.findById(id);
        if (book == null) {
            redirect.addFlashAttribute("error", "Sách không tồn tại");
            return "redirect:/books";
        }

        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setPublisher(book.getPublisher());
        dto.setYear(book.getYear());
        dto.setPrice(book.getPrice());
        dto.setCategoryId(book.getCategory().getId());
        dto.setCategoryName(book.getCategory().getName());

        model.addAttribute("book", dto);
        model.addAttribute("categories", categoryService.findAll());

        return "book/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("book") @Valid BookDTO dto,
                         BindingResult result,
                         RedirectAttributes redirect,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "book/edit";
        }

        if (bookService.existsByNameAndIdNot(dto.getName(), dto.getId())) {
            result.rejectValue("name", "error.book", "Tên sách đã tồn tại");
            model.addAttribute("categories", categoryService.findAll());
            return "book/edit";
        }

        Book book = bookService.findById(dto.getId());
        if (book == null) {
            redirect.addFlashAttribute("error", "Sách không tồn tại");
            return "redirect:/books";
        }

        Category category = categoryService.findById(dto.getCategoryId());
        if (category == null) {
            result.rejectValue("categoryId", "error.book", "Thể loại không tồn tại");
            model.addAttribute("categories", categoryService.findAll());
            return "book/edit";
        }

        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setYear(dto.getYear());
        book.setPrice(dto.getPrice());
        book.setCategory(category);

        bookService.update(book);

        redirect.addFlashAttribute("success", "Cập nhật sách thành công");
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        Book book = bookService.findById(id);
        if (book == null) {
            redirect.addFlashAttribute("error", "Sách không tồn tại");
            return "redirect:/books";
        }

        bookService.deleteById(id);
        redirect.addFlashAttribute("success", "Xóa sách thành công");
        return "redirect:/books";
    }
}

