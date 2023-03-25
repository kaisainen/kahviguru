package com.example.nkk.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.nkk.models.ProductCategory;
import com.example.nkk.models.Supplier;
import com.example.nkk.models.Product;
import com.example.nkk.models.Manufacturer;
import com.example.nkk.services.ProductCategoryService;
import com.example.nkk.services.SupplierService;
import com.example.nkk.services.ProductService;
import com.example.nkk.services.ManufacturerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AdminController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private SupplierService supplierService;

    // LISÄTÄÄN TIETOKANTAAN
    // VALMIIKSI OSASTOJA;VALMISTAJIA,
    // TOIMITTAJIA JA
    // TUOTTEITA KÄYNNISTYKSEN YHTEYDESSÄ

    // @PostConstruct
    // public void init() {
    // productCategoryService.lisaaOsasto(new Osasto("Kahvilaitteet", 0L, new
    // ArrayList<>()));
    // productCategoryService.lisaaOsasto(new Osasto("Kulutustuotteet", 0L, new
    // ArrayList<>()));
    // productCategoryService.lisaaOsasto(new Osasto("Espressolaitteet", 1L, new
    // ArrayList<>()));
    // productCategoryService.lisaaOsasto(new Osasto("Kahvinkeittimet", 1L, new
    // ArrayList<>()));
    // productCategoryService.lisaaOsasto(new Osasto("Kahvimyllyt", 1L, new ArrayList<>()));
    // productCategoryService.lisaaOsasto(new Osasto("Suodattimet", 2L, new ArrayList<>()));
    // productCategoryService.lisaaOsasto(new Osasto("Kahvilaadut", 2L, new ArrayList<>()));
    // productCategoryService.lisaaOsasto(new Osasto("Espressokahvit", 7L, new
    // ArrayList<>()));
    // productCategoryService.lisaaOsasto(new Osasto("Suodatinkahvit", 7L, new
    // ArrayList<>()));

    // manufacturerService.uusiValmistaja(new Valmistaja("Breville",
    // "www.breville.com", new ArrayList<>()));
    // manufacturerService.uusiValmistaja(new Valmistaja("Black & Becker",
    // "www.blackdecker.com", new ArrayList<>()));
    // manufacturerService.uusiValmistaja(new Valmistaja("Bonavita",
    // "www.bonavita.com", new ArrayList<>()));
    // supplierService
    // .lisaaToimittaja(new Toimittaja("Italian coffee", "Greg",
    // "greg@italiancoffee.com", new ArrayList<>()));
    // supplierService.lisaaToimittaja(new Toimittaja("GG", "Kalle",
    // "kalle@gg.com", new ArrayList<>()));
    // }

    // TUOTTEIDEN HALLINTA

    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.listProducts());
        model.addAttribute("manufacturers", manufacturerService.listManufacturers());
        model.addAttribute("productcategories", productCategoryService.listProductCategories());
        model.addAttribute("suppliers", supplierService.listSuppliers());
        return "products";
    }

    @PostMapping("/products")
    public String addProduct(@RequestParam String name, @RequestParam BigDecimal price,
            @RequestParam String description, @RequestParam Long categoryID,
            @RequestParam Long supplierID,
            @RequestParam Long manufacturerID, @RequestParam("image") MultipartFile image)
            throws IOException {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setImage(image.getBytes());
        newProduct.setDescription(description);
        ProductCategory category = productCategoryService.getProductCategoryById(categoryID);
        newProduct.setProductCategory(category);
        Manufacturer manufacturer = manufacturerService.getmanufacturerById(manufacturerID);
        newProduct.setManufacturer(manufacturer);
        Supplier supplier = supplierService.getSupplierById(supplierID);
        newProduct.setSupplier(supplier);
        productService.addProduct(newProduct);
        return "redirect:/products";
    }

    @GetMapping(path = "/products/editproduct/{id}/content", produces = "image/jpg")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {
        return productService.getProductById(id).getImage();
    }

    @GetMapping("/products/editproduct/{id}")
    public String showEditProduct(@PathVariable("id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("manufacturers", manufacturerService.listManufacturers());
        model.addAttribute("productcategories", productCategoryService.listProductCategories());
        model.addAttribute("suppliers", supplierService.listSuppliers());
        return "editproduct";
    }

    @PostMapping("/products/editproduct/{id}")
    public String editProduct(@PathVariable("id") Long id, @RequestParam String name,
            @RequestParam BigDecimal price,
            @RequestParam String description, @RequestParam Long categoryID,
            @RequestParam Long supplierID,
            @RequestParam Long manufacturerID) {
        Product product = productService.getProductById(id);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        ProductCategory productcategory = productCategoryService.getProductCategoryById(categoryID);
        product.setProductCategory(productcategory);
        Manufacturer manufacturer = manufacturerService.getmanufacturerById(manufacturerID);
        product.setManufacturer(manufacturer);
        Supplier supplier = supplierService.getSupplierById(supplierID);
        product.setSupplier(supplier);
        productService.editProduct(product);
        return "redirect:/products/editproduct/" + id;
    }

    @RequestMapping(value = "products/delete", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam(name = "productid") Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    // VALMISTAJIEN HALLINTA

    @GetMapping("/manufacturers")
    public String showmanufacturers(Model model) {
        model.addAttribute("manufacturers", manufacturerService.listManufacturers());
        return "manufacturers";
    }

    @PostMapping("/manufacturers")
    public String addManufacturer(Model model, @ModelAttribute Manufacturer manufacturer) {
        manufacturerService.addManufacturer(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("/manufacturers/editmanufacturer/{id}")
    public String naytaMuokkaaValmistaja(@PathVariable("id") long id, Model model) {
        Manufacturer manufacturer = manufacturerService.getmanufacturerById(id);
        model.addAttribute("manufacturer", manufacturer);
        return "editmanufacturer";
    }

    @PostMapping("/manufacturers/editmanufacturer/{id}")
    public String editManufacturer(@PathVariable("id") Long id, @ModelAttribute Manufacturer manufacturer) {
        Manufacturer editmanufacturer = manufacturerService.getmanufacturerById(id);
        editmanufacturer.setName(manufacturer.getName());
        editmanufacturer.setUrl(manufacturer.getUrl());
        manufacturerService.editManufacturera(editmanufacturer);
        return "redirect:/manufacturers/edit/" + id;
    }

    @RequestMapping(value = "manufacturers/delete", method = RequestMethod.GET)
    public String deleteManufacturer(@RequestParam(name = "manufacturerid") Long id) {
        manufacturerService.deleteManufacturer(id);
        return "redirect:/manufacturers";
    }

    // OSASTOJEN HALLINTA

    @GetMapping("/productcategories")
    public String showProductCategories(Model model) {
        model.addAttribute("productcategories", productCategoryService.listProductCategories());
        return "productcategories";
    }

    @PostMapping("/productcategories")
    public String lisaaOsasto(Model model, @ModelAttribute ProductCategory productcategory) {
        productCategoryService.addProductCategory(productcategory);
        return "redirect:/productcategories";
    }

    @GetMapping("/productcategories/editproductcategory/{id}")
    public String showEditProductCategory(@PathVariable("id") long id, Model model) {
        ProductCategory productcategory = productCategoryService.getProductCategoryById(id);
        model.addAttribute("productcategory", productcategory);
        return "editproductcategory";
    }

    @PostMapping("/productcategories/editproductcategory/{id}")
    public String editProductCategory(@PathVariable("id") Long id, @ModelAttribute ProductCategory category) {
        ProductCategory editCategory = productCategoryService.getProductCategoryById(id);
        editCategory.setName(category.getName());
        editCategory.setProductCategoryIDP(category.getProductCategoryIDP());
        productCategoryService.editProductCategory(editCategory);
        return "redirect:/productcategories/editproductcategory/" + id;
    }

    @RequestMapping(value = "productcategories/delete", method = RequestMethod.GET)
    public String deleteProductCategory(@RequestParam(name = "categoryid") Long id) {
        productCategoryService.deleteProductCategory(id);
        return "redirect:/productcategories";
    }

    // HALLINNOI TOIMITTAJIA

    @GetMapping("/suppliers")
    public String showSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.listSuppliers());
        return "suppliers";
    }

    @PostMapping("/suppliers")
    public String addSupplier(Model model, @ModelAttribute Supplier supplier) {
        supplierService.addSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/suppliers/edit/{id}")
    public String showEditSupplier(@PathVariable("id") long id, Model model) {
        Supplier supplier = supplierService.getSupplierById(id);
        model.addAttribute("supplier", supplier);
        return "edit";
    }

    @PostMapping("/suppliers/edit/{id}")
    public String editSupplier(@PathVariable("id") Long id, @ModelAttribute Supplier supplier) {
        Supplier editSupplier = supplierService.getSupplierById(id);
        editSupplier.setName(supplier.getName());
        editSupplier.setContactPersonName(supplier.getContactPersonName());
        editSupplier.setContactEmail(supplier.getContactEmail());
        supplierService.editSupplier(editSupplier);
        return "redirect:/suppliers/edit/" + id;
    }

    @RequestMapping(value = "suppliers/delete", method = RequestMethod.GET)
    public String deleteSupplier(@RequestParam(name = "supplierid") Long id) {
        supplierService.deleteSupplier(id);
        return "redirect:/suppliers";
    }

}
