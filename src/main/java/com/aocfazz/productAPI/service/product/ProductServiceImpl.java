package com.aocfazz.productAPI.service.product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aocfazz.productAPI.model.Product;
import com.aocfazz.productAPI.payload.req.ProductReq;
import com.aocfazz.productAPI.payload.res.ProductRes;
import com.aocfazz.productAPI.payload.res.ResponseHandler;
import com.aocfazz.productAPI.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
  ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  private Product convertDtoToEntity(ProductReq request, Product product) {
    // validasi input
    if (Objects.isNull(request.getNama()) || Objects.isNull(request.getHarga())
        || Objects.isNull(request.getKuantitas())) {
      throw new RuntimeException("Nama atau harga atau kuantitas wajib diisi!");
    }

    // convert from dto to entity
    // instance obj product
    if (Objects.isNull(product)) {
      product = new Product(request.getNama(), request.getDeskripsi(), request.getHarga(),
          request.getKuantitas());
    } else {
      product.setName(request.getNama());
      product.setDescription(request.getDeskripsi());
      product.setPrice(request.getHarga());
      product.setQuantity(request.getKuantitas());
    }

    return product;
  }

  @Override
  public ResponseEntity<?> addProductService(ProductReq request) {
    Product product = convertDtoToEntity(request, null);

    // save ke db
    productRepository.save(product);

    return ResponseHandler.response(HttpStatus.CREATED.value(), "Data successfully added!", null, null, true);
  }

  @Override
  public List<Product> getAllProductService() {
    // return ResponseHandler.response(200, "Success", productRepository.findAll(),
    // null, true);
    return productRepository.findAll();
  }

  @Override
  public Product getProductByIdService(String idProd) {
    // Optional<Product> productFind = productRepository.findById(idProd);
    // if (productFind.isEmpty()) {
    // throw new NoSuchElementException("Id Product is not found!");
    // }
    // Product product = productFind.get();

    Product product = productRepository.findById(idProd).orElseThrow(() -> {
      throw new NoSuchElementException("Id Product is not found!");
    });

    // return ResponseHandler.response(200, "success", product, null, true);

    return product;
  }

  @Override
  public ResponseEntity<?> updateProductByIdService(String idProd, ProductReq request) {
    Product product = getProductByIdService(idProd);

    product = convertDtoToEntity(request, product);

    // save ke db
    productRepository.save(product);

    return ResponseHandler.response(200, "successfully updated!", product, null, true);
  }

  @Override
  public ResponseEntity<?> deleteProductByIdService(String idProd) {
    Product product = productRepository.findById(idProd).orElseThrow(() -> {
      throw new NoSuchElementException("Id Product is not found!");
    });

    product.setIsDeleted(true);

    productRepository.save(product);

    return ResponseHandler.response(200, "successfully deleted!", null, null, true);
  }

  @Override
  public List<Product> getAllProductByIsDeletedService(Boolean isDeleted) {
    return productRepository.findByIsDeleted(isDeleted);
  }

  @Override
  public ResponseEntity<?> getProductsService(Boolean isDeleted) {
    List<Product> product;
    List<ProductRes> productRes = null;
    if (isDeleted == null) {
      // product = productRepository.findAll();
      productRes = productRepository.findSelectedData();
    } else {
      product = productRepository.findByStatus(isDeleted);
    }

    return ResponseHandler.response(200, "success", productRes, null, true);
  }

}
