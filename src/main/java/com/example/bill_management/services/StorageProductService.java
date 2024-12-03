package com.example.bill_management.services;

import com.example.bill_management.dto.requests.AddProductToStorageRequest;
import com.example.bill_management.dto.requests.ChangeProductHistoryRequest;
import com.example.bill_management.dto.requests.UpdateAmountRequest;
import com.example.bill_management.dto.requests.UpdateInventoryRequest;
import com.example.bill_management.dto.responses.AmountProductResponse;
import com.example.bill_management.dto.responses.StorageProductResponse;
import com.example.bill_management.entities.ProductEntity;
import com.example.bill_management.entities.StorageEntity;
import com.example.bill_management.entities.StorageProductEntity;
import com.example.bill_management.enums.HistoryUpdateType;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.repositories.StorageProductRepository;
import com.example.bill_management.repositories.StorageRepository;
import com.example.bill_management.util.StorageProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StorageProductService {
    @Autowired
    private StorageProductRepository storageProductRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageProductUtil storageProductUtil;
    @Autowired
    private ChangeProductHistoryService changeProductHistoryService;

    public StorageProductResponse addProductToStorage(Long storageId, AddProductToStorageRequest request){
        if (request.getQuantity() == null){
            throw new IllegalArgumentException("Quantity is not correct");
        }
        if(request.getQuantity() < 0){
            throw new IllegalArgumentException("Quantity cannot be less than 0");
        }
        StorageEntity storage = storageRepository.findById(storageId).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_STORAGE_ID));
        ProductEntity product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_PRODUCT_ID));
        StorageProductEntity storageProductEntity = storageProductRepository.findByProductIdAndStorageId(storageId, request.getProductId()).orElse(new StorageProductEntity());

        // check is create new ?
        if (storageProductEntity.getStorageId() == null || storageProductEntity.getProductId() == null){
            storageProductEntity.setProductId(request.getProductId());
            storageProductEntity.setStorageId(storageId);
        }
        storageProductEntity.addToInventory(request.getQuantity());

        changeProductHistoryService.saveProductHistory(new ChangeProductHistoryRequest(
                storageId,
                product.getId(),
                HistoryUpdateType.ADD.name(),
                request.getQuantity(),
                LocalDate.now()
        ));
        return storageProductUtil.toStorageProductResponse(storageProductRepository.save(storageProductEntity));
    }

    public Void deleteProductFromStorage(Long storageId, String productId){

        return null;
    }

    public StorageProductResponse addInventoryProductToStorage(Long storageId, String productId, UpdateAmountRequest request){
        StorageProductEntity storageProduct = storageProductRepository.findByProductIdAndStorageId(storageId, productId).orElseThrow(() -> new AppException(ECProductsStorage.PRODUCT_IS_NOT_IN_STORAGE));
        storageProduct.addToInventory(request.getAmount());

        changeProductHistoryService.saveProductHistory(new ChangeProductHistoryRequest(
                storageId,
                productId,
                HistoryUpdateType.ADD.name(),
                request.getAmount(),
                LocalDate.now()
        ));
        return storageProductUtil.toStorageProductResponse(storageProductRepository.save(storageProduct));
    }

    public StorageProductResponse dispatchProductFromStorage(Long storageId, String productId, UpdateAmountRequest request){
        StorageProductEntity storageProduct = storageProductRepository.findByProductIdAndStorageId(storageId, productId).orElseThrow(() -> new AppException(ECProductsStorage.PRODUCT_IS_NOT_IN_STORAGE));
        storageProduct.dispatchProduct(request.getAmount());
        changeProductHistoryService.saveProductHistory(new ChangeProductHistoryRequest(
                storageId,
                productId,
                HistoryUpdateType.DISPATCH.name(),
                request.getAmount(),
                LocalDate.now()
        ));
        return storageProductUtil.toStorageProductResponse(storageProductRepository.save(storageProduct));
    }

    public StorageProductResponse updateInventoryInStorage(Long storageId, String productId, UpdateInventoryRequest request){
        StorageProductEntity storageProduct = storageProductRepository.findByProductIdAndStorageId(storageId, productId).orElseThrow(() -> new AppException(ECProductsStorage.PRODUCT_IS_NOT_IN_STORAGE));
        storageProduct.setInventory(request.getInventory());
        changeProductHistoryService.saveProductHistory(new ChangeProductHistoryRequest(
                storageId,
                productId,
                HistoryUpdateType.UPDATE.name(),
                request.getInventory(),
                LocalDate.now()
        ));
        return storageProductUtil.toStorageProductResponse(storageProductRepository.save(storageProduct));
    }

    public List<StorageProductResponse> getAllProductsInStorage(Long storageId){
        if (!storageRepository.existsById(storageId)){
            throw new AppException(ECProductsStorage.NONEXISTENT_STORAGE_ID);
        }
        List<StorageProductEntity> storageProductEntityList = storageProductRepository.findAllByStorageId(storageId);
        return storageProductEntityList.stream()
                .map(sp -> storageProductUtil.toStorageProductResponse(sp))
                .toList();
    }

    public List<StorageProductResponse> getAllStoragesHaveProduct(String productId){
        if (!productRepository.existsById(productId)){
            throw new AppException(ECProductsStorage.NONEXISTENT_PRODUCT_ID);
        }
        List<StorageProductEntity> storageProductEntityList = storageProductRepository.findAllByProductId(productId);
        return storageProductEntityList.stream()
                .map(sp -> storageProductUtil.toStorageProductResponse(sp))
                .toList();
    }

    public AmountProductResponse<Long> getInventory(Long storageId, String productId){
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_PRODUCT_ID));
        StorageEntity storage = storageRepository.findById(storageId).orElseThrow(() -> new AppException(ECProductsStorage.NONEXISTENT_STORAGE_ID));
        StorageProductEntity storageProduct = storageProductRepository.findByProductIdAndStorageId(storageId, productId).orElseThrow(() -> new AppException(ECProductsStorage.PRODUCT_IS_NOT_IN_STORAGE));
        return AmountProductResponse.<Long> builder()
                .storageId(storageId)
                .storageName(storage.getName())
                .productId(productId)
                .productName(product.getName())
                .amountName("inventory")
                .amount(storageProduct.getInventory())
                .build();
    }
}
