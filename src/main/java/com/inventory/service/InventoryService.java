package com.inventory.service;

import com.inventory.exception.ResourceNotFoundException;
import com.inventory.model.Product;
import com.inventory.model.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class InventoryService {
    private List<Product> products = new ArrayList<>();
    private List<Supplier> suppliers = new ArrayList<>();
    private AtomicLong productId = new AtomicLong();
    private AtomicLong supplierId = new AtomicLong();

    // Product CRUD operations
    public Product addProduct(Product product) {
        product.setId(productId.incrementAndGet());
        products.add(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product getProductByBarcode(String barcode) {
        return products.stream()
                .filter(p -> p.getBarcode().equals(barcode))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public String updateStock(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        product.setCurrentStock(quantity);
        return checkLowStock(product);
    }

    private String checkLowStock(Product product) {
        if (product.getCurrentStock() < product.getMinStockLevel()) {
            String alertMessage = "\n" + 
                "************************************************\n" +
                "                LOW STOCK ALERT                  \n" +
                "************************************************\n" +
                "Product: " + product.getName() + "\n" +
                "Current Stock: " + product.getCurrentStock() + "\n" +
                "Minimum Stock Level: " + product.getMinStockLevel() + "\n" +
                "************************************************\n";
            
            log.warn(alertMessage);
            return alertMessage;
        }
        return "Stock updated successfully. Current stock: " + product.getCurrentStock();
    }

    // Supplier CRUD operations
    public Supplier addSupplier(Supplier supplier) {
        supplier.setId(supplierId.incrementAndGet());
        suppliers.add(supplier);
        return supplier;
    }

    public List<Supplier> getAllSuppliers() {
        return suppliers;
    }

    // Weekly report generation
    public String generateInventoryReport() {
        StringBuilder report = new StringBuilder("Weekly Inventory Report\n");
        report.append("Generated on: ").append(LocalDateTime.now()).append("\n\n");
        
        products.forEach(product -> {
            report.append(String.format("Product: %s (ID: %d)\n", product.getName(), product.getId()));
            report.append(String.format("Current Stock: %d\n", product.getCurrentStock()));
            report.append(String.format("Min Stock Level: %d\n", product.getMinStockLevel()));
            report.append("------------------------\n");
        });
        
        return report.toString();
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getProductById(id);
        product.setName(updatedProduct.getName());
        product.setBarcode(updatedProduct.getBarcode());
        product.setPrice(updatedProduct.getPrice());
        product.setMinStockLevel(updatedProduct.getMinStockLevel());
        product.setCurrentStock(updatedProduct.getCurrentStock());
        checkLowStock(product);
        return product;
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        products.remove(product);
    }

    public Supplier getSupplierById(Long id) {
        return suppliers.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        Supplier supplier = getSupplierById(id);
        supplier.setName(updatedSupplier.getName());
        supplier.setEmail(updatedSupplier.getEmail());
        supplier.setPhone(updatedSupplier.getPhone());
        supplier.setAddress(updatedSupplier.getAddress());
        return supplier;
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        suppliers.remove(supplier);
    }
} 