package com.example.ecommerce.restcontroller;

import com.example.ecommerce.ApiUrls;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.ItemService;
import com.example.ecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiUrls.ROOT_URL_ITEMs)
public class ItemRestController {
    private ItemService itemService;

    public ItemRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Item> orderList= itemService.findAll();
        return ResponseEntity.ok(orderList);
    }

    @GetMapping(ApiUrls.URL_ITEMS_ITEM)
    public ResponseEntity<?> findOne(@PathVariable("itemId") long id) {
        if (!itemService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item item = itemService.findOne(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Item item) {
        item = itemService.save(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(location).body(item);
    }

    @PutMapping(ApiUrls.URL_ITEMS_ITEM)
    public ResponseEntity<?> update(@PathVariable("itemId") Long id,@Validated @RequestBody Item item) {
        if (!itemService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        item.setId(id);
        item = itemService.update(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping(ApiUrls.URL_ITEMS_ITEM)
    public ResponseEntity<?> delete(@PathVariable("itemId") long id) {
        if (!itemService.exists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        itemService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
