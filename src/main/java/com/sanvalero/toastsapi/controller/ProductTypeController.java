package com.sanvalero.toastsapi.controller;

import java.util.List;

import com.sanvalero.toastsapi.exception.ErrorResponse;
import com.sanvalero.toastsapi.exception.NotFoundException;
import com.sanvalero.toastsapi.model.ProductType;
import com.sanvalero.toastsapi.service.ProductTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductTypeController {

    @Autowired
    private ProductTypeService pts;

    @GetMapping("/types")
    public ResponseEntity<List<ProductType>> getAllTypes() {
        return new ResponseEntity<>(pts.findAll(), HttpStatus.OK);
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<ProductType> getById(@PathVariable int id) throws NotFoundException {
        return new ResponseEntity<>(pts.findById(id), HttpStatus.OK);
    }

    @GetMapping("/type/{name}")
    public ResponseEntity<List<ProductType>> getByName(@PathVariable String name) {
        return new ResponseEntity<>(pts.findByProductName(name), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<ProductType> getByType(@PathVariable String type) {
        return new ResponseEntity<>(pts.findByType(type), HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<ProductType> getByNameAndType(@RequestParam(value = "name") String name,
            @RequestParam(value = "type") String type) {

        return new ResponseEntity<>(pts.findByProductNameAndType(name, type), HttpStatus.OK);
    }

    @PostMapping("/type")
    public ResponseEntity<ProductType> create(@RequestBody ProductType type) {
        return new ResponseEntity<>(pts.addType(type), HttpStatus.OK);
    }

    @PutMapping("/type/{id}")
    public ResponseEntity<ProductType> update(@PathVariable int id, @RequestBody ProductType type) throws NotFoundException {
        ProductType typeToUpdate = pts.findById(id);
        typeToUpdate.setProductName(type.getProductName());
        typeToUpdate.setType(type.getType());

        return new ResponseEntity<>(pts.updateType(typeToUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/type/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) throws NotFoundException {
        ProductType type = pts.findById(id);
        pts.deleteType(type);
        return new ResponseEntity<>("Product type deleted.", HttpStatus.OK);
    }

    @DeleteMapping("/types")
    public ResponseEntity<String> deleteAll() {
        pts.deleteAll();

        return new ResponseEntity<>("All types deleted.", HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException nfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", nfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
