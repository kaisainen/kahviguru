package com.example.nkk.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.nkk.models.Product;
import com.example.nkk.services.ProductCategoryService;
import com.example.nkk.services.SupplierService;
import com.example.nkk.services.ProductService;
import com.example.nkk.services.ManufacturerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductsController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    /* The method displays the first page of search results for a list of coffee makers. */
    @RequestMapping(path = "/coffeemakers")
    public String showFirstPage(Model model, String searchTerm) {
        return showCoffeeMakers(model, 1, searchTerm);
    }

    /* This method fetches a list of coffee makers and returns a page of coffee maker(s) (possibly based on a searchterm), 
    along with information about the current page and the total number of pages and products. */
    @GetMapping("/coffeemakers/{pagenum}")
    public String showCoffeeMakers(Model model, @PathVariable(name = "pagenum") Integer pagenum,
            @Param("searchTerm") String searchTerm) {

        // caoffee maker category IDs        
        List<Long> coffeeMakers = Arrays.asList(3L, 4L, 5L);

        if (searchTerm != null) {
            Page<Product> productPage = productService.getProductsPageable(pagenum - 1, 6, coffeeMakers, searchTerm); // we want to how 6 products in a view
            List<Product> products = productPage.getContent();
            Integer totalPages = productPage.getTotalPages();
            Long totalProducts = productPage.getTotalElements();
            model.addAttribute("currentPage", pagenum);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalItems", totalProducts);
            model.addAttribute("coffeeMakers", products);
            return "coffeemakers";

        } else {
            Page<Product> productPages = productService.getProductsByProductCategory(pagenum - 1, 6, coffeeMakers);
            List<Product> products = productPages.getContent();
            Integer totalPages = productPages.getTotalPages();
            Long totalProducts = productPages.getTotalElements();
            model.addAttribute("currentPage", pagenum);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalItems", totalProducts);
            model.addAttribute("coffeeMakers", products);
            return "coffeemakers";
        }
    }

    /* This method returns products' images based on the products' ID. */
    @GetMapping(path = "/coffeemakers/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] getImages(@PathVariable Long id) {
        return productService.getProductById(id).getImage();
    }

    /* This method returns the details of a product in a separate /productdetails view */
    @GetMapping("/coffeemakers/productdetails/{id}")
    public String showCoffeeMaker(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("manufacturers", manufacturerService.listManufacturers());
        model.addAttribute("productCategories", productCategoryService.listProductCategories());
        model.addAttribute("suppliers", supplierService.listSuppliers());
        return "productdetails";
    }

    // Supplies

    /* The method displays the first page of search results for a list of supplies. */
    @RequestMapping(path="/supplies")
    public String showSuppliesFirstPage(Model model, String searchTerm) {
        return showSuppliesFirstPage(model, searchTerm);
    }

    /* This method fetches a list of supplies and returns a page of supplies (possibly based on a searchterm), 
    along with information about the current page and the total number of pages and products. */
    @GetMapping("/supplies/{pagenum}")
    public String showSupplies(Model model, @PathVariable(name = "pagenum") Integer pagenum,
            @Param("searchTerm") String searchTerm) {
        
        // Supplies related product category IDs
        List<Long> supplies = Arrays.asList(6L, 7L, 8L, 9L);

        if (searchTerm != null) {
            Page<Product> productPages = productService.getProductsPageable(pagenum - 1, 6, supplies, searchTerm);
            List<Product> products = productPages.getContent();
            Integer totalPages = productPages.getTotalPages();
            Long totalProducts = productPages.getTotalElements();
            model.addAttribute("currentPage", pagenum);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalItems", totalProducts);
            model.addAttribute("supplies", products);
            return "supplies";

        } else {
            Page<Product> productPages = productService.getProductsByProductCategory(pagenum - 1, 6, supplies);
            List<Product> products = productPages.getContent();
            Integer totalPages = productPages.getTotalPages();
            Long totalProducts = productPages.getTotalElements();
            model.addAttribute("currentPage", pagenum);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalItems", totalProducts);
            model.addAttribute("supplies", products);
            return "supplies";
        }
    }

    /* This method returns products' images based on the products' ID. */
    @GetMapping(path = "/supplies/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] getContentImage(@PathVariable Long id) {
        return productService.getProductById(id).getImage();
    }

    /* This method returns the details of a product in a separate /productdetails view */
    @GetMapping("/supplies/productdetails/{id}")
    public String showSupplies(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("manufacturers", manufacturerService.listManufacturers());
        model.addAttribute("productcategories", productCategoryService.listProductCategories());
        model.addAttribute("suppliers", supplierService.listSuppliers());
        return "productdetails";
    }

    /* This method returns products' images based on the products' ID. */
    @GetMapping(path = "/productdetails/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] getSuppliesImage(@PathVariable Long id) {
        return productService.getProductById(id).getImage();
    }

}
