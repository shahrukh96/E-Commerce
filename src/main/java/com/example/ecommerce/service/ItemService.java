package com.example.ecommerce.service;

import com.example.ecommerce.entity.Item;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.repository.ItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findById(id).orElse(null);
    }


    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Transactional
    public Item update(Item item) {
        Item mItem = itemRepository.findById(item.getId()).orElse(null);
        if (mItem != null) {
            mItem.setName(item.getName());
            mItem.setPrice(item.getPrice());
            mItem.setDescription(item.getDescription());
            mItem.setSku(item.getSku());
        }
        return mItem;
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return itemRepository.existsById(id);
    }
}
