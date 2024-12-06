package com.example.bill_management.services;

import com.example.bill_management.dto.requests.ChangeProductHistoryRequest;
import com.example.bill_management.dto.requests.UpdateAmountRequest;
import com.example.bill_management.dto.responses.AmountProductResponse;
import com.example.bill_management.dto.responses.StorageProductResponse;
import com.example.bill_management.entities.StorageProductEntity;
import com.example.bill_management.enums.HistoryUpdateType;
import com.example.bill_management.exceptions.AppException;
import com.example.bill_management.exceptions.ECProductsStorage;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.repositories.StorageProductRepository;
import com.example.bill_management.repositories.StorageRepository;
import com.example.bill_management.services.factory.ProductFactory;
import com.example.bill_management.services.factory.StorageFactory;
import com.example.bill_management.util.StorageProductUtil;
import com.example.bill_management.util.converter.StorageProductConverter;
import com.example.bill_management.validator.StorageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private StorageProductConverter storageProductConverter;
    @Autowired
    private StorageFactory storageFactory;
    @Autowired
    private StorageValidator storageValidator;
    @Autowired
    private ProductFactory productFactory;

    @Transactional
    public StorageProductResponse addProductToStorage(Long storageId, UpdateAmountRequest request){
        StorageProductEntity storageProduct = storageFactory.addQuantityToInventoryProduct(storageId, request);

        changeProductHistoryService.saveProductHistory(new ChangeProductHistoryRequest(
                storageProduct.getStorageId(),
                storageProduct.getProductId(),
                HistoryUpdateType.ADD.name(),
                request.getQuantity(),
                LocalDate.now()
        ));

        return storageProductConverter.toResponseConverter(storageProductRepository.save(storageProduct));
    }

    public Void deleteProductFromStorage(Long storageId, String productId){

        return null;
    }

    public StorageProductResponse dispatchProductFromStorage(Long storageId, String productId, UpdateAmountRequest request){
        StorageProductEntity storageProduct = storageFactory.dispatchProductFromStorage(storageId, productId, request);
        changeProductHistoryService.saveProductHistory(new ChangeProductHistoryRequest(
                storageId,
                productId,
                HistoryUpdateType.DISPATCH.name(),
                request.getQuantity(),
                LocalDate.now()
        ));
        return storageProductConverter.toResponseConverter(storageProductRepository.save(storageProduct));
    }

    public StorageProductResponse updateInventoryInStorage(Long storageId, String productId, UpdateAmountRequest request){
        StorageProductEntity storageProduct = storageFactory.updateInventoryProductFromStorage(storageId, productId, request);
        changeProductHistoryService.saveProductHistory(new ChangeProductHistoryRequest(
                storageId,
                productId,
                HistoryUpdateType.UPDATE.name(),
                request.getQuantity(),
                LocalDate.now()
        ));
        return storageProductConverter.toResponseConverter(storageProductRepository.save(storageProduct));
    }

    public List<StorageProductResponse> getAllProductsInStorage(Long storageId){
        if (!storageRepository.existsById(storageId)){
            throw new AppException(ECProductsStorage.NONEXISTENT_STORAGE_ID);
        }

        List<StorageProductEntity> storageProductEntityList = storageProductRepository.findAllByStorageId(storageId);
        return storageProductEntityList.stream()
                .map(storageProductConverter::toResponseConverter)
                .toList();
    }

    public List<StorageProductResponse> getAllStoragesHaveProduct(String productId){
        if (!productRepository.existsById(productId)){
            throw new AppException(ECProductsStorage.NONEXISTENT_PRODUCT_ID);
        }

        List<StorageProductEntity> storageProductEntityList = storageProductRepository.findAllByProductId(productId);
        return storageProductEntityList.stream()
                .map(storageProductConverter::toResponseConverter)
                .toList();
    }

    public AmountProductResponse<Long> getInventory(Long storageId, String productId){
        StorageProductEntity storageProduct = storageValidator.findByStorageAndProductId(storageId, productId);
        return AmountProductResponse.<Long> builder()
                .storageId(storageId)
                .storageName(storageFactory.nameOfStorage(storageId))
                .productId(productId)
                .productName(productFactory.nameOfProduct(productId))
                .amountName("inventory")
                .amount(storageProduct.getInventory())
                .build();
    }
}
