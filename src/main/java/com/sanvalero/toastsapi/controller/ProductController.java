package com.sanvalero.toastsapi.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import com.sanvalero.toastsapi.exception.NotFoundException;
import com.sanvalero.toastsapi.model.Menu;
import com.sanvalero.toastsapi.model.Product;
import com.sanvalero.toastsapi.model.ProductType;
import com.sanvalero.toastsapi.model.Publication;
import com.sanvalero.toastsapi.model.dto.ProductDTO;
import com.sanvalero.toastsapi.service.MenuService;
import com.sanvalero.toastsapi.service.ProductService;
import com.sanvalero.toastsapi.service.ProductTypeService;
import com.sanvalero.toastsapi.service.PublicationService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService ps;
    @Autowired
    private ProductTypeService pts;
    @Autowired
    private MenuService ms;
    @Autowired
    private PublicationService publicationService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @GetMapping("/products")
    public List<Product> getAll() {
        return ps.findAll();
    }

    @GetMapping("/product/id={id}")
    public Product getById(@PathVariable int id) throws NotFoundException {
        return ps.findById(id);
    }

    @GetMapping("/products/date={dateString}")
    public List<Product> getByDate(@PathVariable String dateString) {
        LocalDate date = LocalDate.parse(dateString, formatter);

        return ps.findByDate(date);
    }

    @GetMapping("/products/minDate={minDateString}-maxDate={maxDateString}")
    public List<Product> getByDateBetween(@PathVariable String minDateString,
            @PathVariable String maxDateString) {

        LocalDate minDate = LocalDate.parse(minDateString, formatter);
        LocalDate maxDate = LocalDate.parse(maxDateString, formatter);

        return ps.findByDateBetween(minDate, maxDate);
    }

    @GetMapping("/products/inMenu={inMenu}")
    public List<Product> getByInMenu(@PathVariable boolean inMenu) {
        return ps.findByInMenu(inMenu);
    }

    @GetMapping("/products/price={price}")
    public List<Product> getByPrice(@PathVariable float price) {
        return ps.findByPrice(price);
    }

    @GetMapping("/products/minPrice={minPrice}-maxPrice={maxPrice}")
    public List<Product> getByPriceBetween(@PathVariable float minPrice,
            @PathVariable float maxPrice) {

        return ps.findByPriceBetween(minPrice, maxPrice);
    }

    @GetMapping("/products/punctuation={punctuation}")
    public List<Product> getByPunctuation(@PathVariable float punctuation) {
        return ps.findByPunctuation(punctuation);
    }

    @GetMapping("/products/minPunctuation={minPunctuation}-maxPunctuation={maxPunctuation}")
    public List<Product> getByPunctuationBetween(@PathVariable float minPunctuation,
            @PathVariable float maxPunctuation) {

        return ps.findByPunctuationBetween(minPunctuation, maxPunctuation);
    }

    @GetMapping("products/type")
    public List<Product> getByTypeId(@RequestParam(value = "id") int typeId) throws NotFoundException {
        ProductType type = pts.findById(typeId);
        return ps.findByType(type);
    }

    @GetMapping("/products/types")
    public List<Product> getByTypes(@RequestParam(value = "ids") Vector<Integer> typesIds) {
        List<ProductType> types = pts.findByIds(typesIds);
        return ps.findByTypes(types);
    }

    @GetMapping("/products/menu")
    public List<Product> getByMenu(@RequestParam(value = "menuId") int id) throws NotFoundException {
        Menu menu = ms.findById(id);

        return ps.findByMenu(menu);
    }

    @GetMapping("/products/publication")
    public List<Product> getByPublication(@RequestParam(value = "id") int id) throws NotFoundException {
        Publication publication = publicationService.findById(id);

        return ps.findByPublication(publication);
    }

    @PostMapping("/product")
    public Product create(@RequestBody ProductDTO productDTO) throws NotFoundException {
        ProductType type = pts.findById(productDTO.getTypeId());
        Menu menu = ms.findById(productDTO.getMenuId());
        Publication publication = publicationService.findById(productDTO.getPublicationId());

        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDTO, Product.class);
        product.setDate(LocalDate.now());
        product.setType(type);
        product.setMenu(menu);
        product.setPublication(publication);

        return ps.addProduct(product);
    }

    @PutMapping("/product/id={id}")
    public Product update(@RequestBody ProductDTO productDTO, @PathVariable int id) throws NotFoundException {
        Product productToUpdate = ps.findById(id);

        ProductType type = pts.findById(productDTO.getTypeId());
        Menu menu = ms.findById(productDTO.getMenuId());
        Publication publication = publicationService.findById(productDTO.getPublicationId());

        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDTO, Product.class);

        productToUpdate.setDate(product.getDate());
        productToUpdate.setInMenu(product.isInMenu());
        productToUpdate.setMenu(menu);
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setPublication(publication);
        productToUpdate.setPunctuation(product.getPunctuation());
        productToUpdate.setType(type);

        return ps.modifyProduct(productToUpdate);
    }

    @DeleteMapping("/product/id={id}")
    public Product delete(@PathVariable int id) throws NotFoundException {
        Product product = ps.findById(id);

        return ps.deleteProduct(product);
    }

    @DeleteMapping("/products")
    public String deleteAll() {
        ps.deleteAll();

        return "All products deleted";
    }
}
