package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.dto.CartItemDeleteList;
import com.fa.DPA.model.CartItem;
import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.CategoryRepository;
import com.fa.DPA.repos.ConstructionDrawingRepository;
import com.fa.DPA.repos.CustomerAccountRepository;
import com.fa.DPA.service.CartItemService;
import com.fa.DPA.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cart")
public class CartItemController {
    private CartItemService cartItemService;
    private CustomerAccountService customerAccountService;
    private ConstructionDrawingRepository constructionDrawingRepository;
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    public CartItemController(CartItemService cartItemService, CustomerAccountService customerAccountService, ConstructionDrawingRepository constructionDrawingRepository, CustomerAccountRepository customerAccountRepository) {
        this.cartItemService = cartItemService;
        this.customerAccountService = customerAccountService;
        this.constructionDrawingRepository = constructionDrawingRepository;
        this.customerAccountRepository = customerAccountRepository;
    }







    CustomerAccount customerAccount;
    @GetMapping("/cart/{userid}")
//    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Map<String, Object>> getAllCartItems(
            @PathVariable("userid") String raw_userid){
        long userid = Constant.checkId(raw_userid);
        if(userid == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
//        customerAccount = Constant.getCustomer(customerAccountService);
//        if (customerAccount== null){
//            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//        }
        Map<String, Object> response = new HashMap<>();
        try{
            List<CartItem> cartItemList = cartItemService.getAllCartItem(userid);
            response.put("cartItemList", cartItemList);
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/cart/add/{userid}")
    //    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CartItem> addItemFromList(@RequestParam long item_id, @PathVariable("userid") String raw_userid){
        long userid = Constant.checkId(raw_userid);
        if(userid == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
//        customerAccount = Constant.getCustomer(customerAccountService);
//        if(customerAccount==null){
//            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
//        }

        ConstructionDrawing constructionDrawing = new ConstructionDrawing();
        if(constructionDrawingRepository.existsById(item_id)){
            constructionDrawing.setId(item_id);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        CustomerAccount owner = new CustomerAccount();
        if (customerAccountRepository.existsById(userid)) {
            owner.setId(userid);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        CartItem item = new CartItem();
        item.setCustomerAccount(owner);
        item.setConstructionDrawing(constructionDrawing);

        try {
            CartItem cartItem = cartItemService.saveItem(item);
            return new ResponseEntity<>(cartItem,HttpStatus.OK);
        } catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cart/delete")
    //    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<CartItem> removeItem(@RequestBody CartItemDeleteList ids){
//        customerAccount = Constant.getCustomer(customerAccountService);
//        if(customerAccount==null){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
        try{
            cartItemService.removeItem(ids.getIds());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
