package com.roky.thunderspi.controllers;

import com.roky.thunderspi.entities.LibCategory;
import com.roky.thunderspi.entities.LibElement;
import com.roky.thunderspi.services.ILibCategoryService;
import com.roky.thunderspi.services.ILibElementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/libCat")
@AllArgsConstructor
@CrossOrigin("*")
public class LibCategoryController {
    private ILibCategoryService iLibCategoryService;

    @GetMapping("/getAll")
    public List<LibCategory> findAllLibCat() {
        return iLibCategoryService.findAllLibCat();
    }

    @GetMapping("getAll/{id}")
    public LibCategory findLibCatById(@PathVariable Long id) { return iLibCategoryService.findLibCatById(id);
    }

    @PostMapping("/add")
    public LibCategory addLibCat(@RequestBody LibCategory libECategory) {
        return iLibCategoryService.addLibCat(libECategory);
    }

    @PutMapping("/update")
    public LibCategory editLibCat(@RequestBody LibCategory libCategory) {
        return iLibCategoryService.addLibCat(libCategory);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLibCat(@PathVariable Long id) {
        iLibCategoryService.deleteLibCat(id);
    }
}
