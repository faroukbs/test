package com.roky.thunderspi.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roky.thunderspi.entities.CategoryProduct;
import com.roky.thunderspi.entities.MultiPicture;
import com.roky.thunderspi.entities.Product;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.repositories.MultiPictureRepo;
import com.roky.thunderspi.repositories.ProductRepo;
import com.roky.thunderspi.services.*;
import com.roky.thunderspi.util.FileUploadUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController implements ServletContextAware {
    private final ProductServiceImpl productService;
    private ProductCategoryServiceImpl productCategoryService;
    ServletContext context;
    private final ProductRepo productRepository;
    @Autowired
    MultiPictureRepo imageRepository;

    public ProductController(ProductServiceImpl productService, ProductRepo productRepository, ProductCategoryServiceImpl productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    private String saveImage(MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(context.getRealPath("/Imagess/" + multipartFile.getOriginalFilename()));
            Files.write(path, bytes);
            return multipartFile.getOriginalFilename();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    @PostMapping("/addproduct")
    public long newProduct(@RequestParam("files") MultipartFile[] files,
                           @RequestParam("product") String product,
                           @RequestParam("file") MultipartFile image) throws JsonParseException, JsonMappingException, Exception {

        Product arti = new ObjectMapper().readValue(product, Product.class);
        boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
        String fileN = StringUtils.cleanPath(image.getOriginalFilename());
        arti.setPicture(fileN);
        System.out.println("====================");

        System.out.println(arti.getId());
        String uploadDir = "Imagess/" + arti.getId();
        System.out.println(uploadDir);
        FileUploadUtil.saveFile(uploadDir, fileN, image);
        if (!isExit) {
            new File(context.getRealPath("/Imagess/")).mkdir();
            System.out.println("mk dir Imagess.............");
        }
        System.out.println("Save Article  22222.............");
        Set<MultiPicture> photos = new HashSet<>();
        for (MultipartFile file : files) {
            MultiPicture fileinfo = new MultiPicture();
            String filename = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
            File serverFile = new File(context.getRealPath("/Imagess/" + File.separator + newFileName));
            try {
                System.out.println("Image");
                FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
            fileinfo.setName(newFileName);
            fileinfo.setImage(arti);
            imageRepository.save(fileinfo);
            photos.add(fileinfo);
        }
        String fileName = image.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(fileName);
        File serverFile = new File(context.getRealPath("/Imagess/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, image.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Save Article 333333.............");
        arti.setPicture(newFileName);

        System.out.println("Save Article 333333.............");
        // arti.setProducts(photos);
        // User user = new User();
        //System.out.println(user.getPhone_number());
         productService.SendSms("+21628608927",
               "Hello we send you this sms to inform you that we have add  new product:  " + arti.getName() + " with a price :  "+ arti.getPrix() +  "  " + arti.getDescription() + arti.getProducts() +  "to our shop check our shop  and by somthing to let us help needy peapole");

        return productService.addProduct(arti);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteproduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public long updateproduct(@RequestParam("files") MultipartFile[] files,
                              @RequestParam("product") String product,
                              @RequestParam("file") MultipartFile image) throws JsonParseException, JsonMappingException, Exception {

        Product arti = new ObjectMapper().readValue(product, Product.class);
        boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
        if (!isExit) {
            new File(context.getRealPath("/Imagess/")).mkdir();
            System.out.println("mk dir Imagess.............");
        }
        System.out.println("Save Article  22222.............");
        Set<MultiPicture> photos = new HashSet<>();
        for (MultipartFile file : files) {
            MultiPicture fileinfo = new MultiPicture();
            String filename = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
            File serverFile = new File(context.getRealPath("/Imagess/" + File.separator + newFileName));
            try {
                System.out.println("Image");
                FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
            fileinfo.setName(newFileName);
            fileinfo.setImage(arti);
            imageRepository.save(fileinfo);
            photos.add(fileinfo);
        }
        String fileName = image.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(fileName);
        File serverFile = new File(context.getRealPath("/Imagess/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, image.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        arti.setPicture(newFileName);


        return productService.editProduct(arti);



    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getEmployeeById(@PathVariable("id") Long id) throws Exception {
        Product product = productService.findProdById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //@GetMapping(path = "/Imgarticles/{id}")
    //public List<byte[]> getPhoto(@PathVariable("id") Long id) throws Exception {
    //  ArrayList<MultiPicture> files = new ArrayList<MultiPicture>();
    //  Product product = productService.findProdById(id);
    //  List<byte[]> fi = new ArrayList<>();
    //  files = imageRepository.findByImage(product);
    //  System.out.println(files);

    //  for (MultiPicture file : files) {
    //      // fi.add(Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/")+file.getImage())));
    //      fi.add(Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/") + file.getName())));
    //  }

    //  return fi;
    // }

    @GetMapping("/images/{id}")
    public ResponseEntity<List<MultiPicture>> getImagesByProduct(@PathVariable("id") Long id) throws Exception {
        ArrayList<MultiPicture> files = new ArrayList<MultiPicture>();
        Product product = productService.findProdById(id);
        files = imageRepository.findByImage(product);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping(path = "/getimage/{id}")
    public byte[] getPhotoProduct(@PathVariable("id") Long id) throws Exception {
        MultiPicture Article = imageRepository.findById(id).orElseThrow(() -> new Exception("File by id " + id + " was not found"));

        return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/") + Article.getName()));
    }


  //  @GetMapping(path = "/Imgarticle/{id}")
  //  public byte[] getProductImage(@PathVariable("id") Long id) throws Exception {
  //      Product Article = productService.findProdById(id);
  //      return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/") + Article.getPicture()));
  //  }
    @GetMapping(path = "/Imgarticle/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
        Product Article = productService.findProdById(id);
        return Files.readAllBytes(Paths.get("Imagess/null/" + Article.getPicture()));
    }

    @GetMapping("/add-etoile/{produit-id}/{client-id}/{rev}")
    @ResponseBody
    public void moyEtoile(@PathVariable("produit-id") Long produitId, @PathVariable("client-id") Long clientId, @PathVariable("rev") Double rev) throws Exception {
        productService.calculeEtoile(rev, produitId, clientId);
    }

    @GetMapping("prix/{minP}/{maxP}")
    public List<Product> findByPrice(@PathVariable BigDecimal minP, @PathVariable BigDecimal maxP) {
        return productService.findByPrice(minP, maxP);
    }

    @GetMapping("/catproducts/{id}")
    public ResponseEntity<List<Product>> getAllProductByCategory(@PathVariable("id") Long id) throws Exception {
        CategoryProduct category = productCategoryService.findProdById(id);
        List<Product> products = productService.getAllProductByCategory(category);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
