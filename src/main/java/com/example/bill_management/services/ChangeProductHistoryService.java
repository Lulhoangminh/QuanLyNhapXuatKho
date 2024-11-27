package com.example.bill_management.services;

import com.example.bill_management.dto.requests.ChangeProductHistoryRequest;
import com.example.bill_management.entities.ChangeProductHistoryEntity;
import com.example.bill_management.repositories.ChangeProductHistoryRepository;
import com.example.bill_management.repositories.ProductRepository;
import com.example.bill_management.repositories.StorageRepository;
import com.example.bill_management.util.ChangeProductHistoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeProductHistoryService {
    @Autowired
    private ChangeProductHistoryRepository changeProductHistoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private ChangeProductHistoryUtil changeProductHistoryUtil;
    public void saveProductHistory(ChangeProductHistoryRequest request){
        ChangeProductHistoryEntity changeProductHistoryEntity = changeProductHistoryRepository.findChangeInQuantityToday(
                request.getStorageId(),
                request.getProductId(),
                request.getType(),
                request.getCreatedDate()).orElse(new ChangeProductHistoryEntity());
        if (changeProductHistoryEntity.getProductId() != null && changeProductHistoryEntity.getStorageId() != null){
            changeProductHistoryEntity.addAmount(request.getAmount());
        }
        else{
            changeProductHistoryEntity.setProductId(request.getProductId());
            changeProductHistoryEntity.setStorageId(request.getStorageId());
            changeProductHistoryEntity.setType(request.getType());
            changeProductHistoryEntity.setCreatedDate(request.getCreatedDate());
            changeProductHistoryEntity.setAmount(request.getAmount());
        }
//        changeProductHistory.addAmount(entity.getAmount());

        changeProductHistoryRepository.save(changeProductHistoryEntity);
    }
}
