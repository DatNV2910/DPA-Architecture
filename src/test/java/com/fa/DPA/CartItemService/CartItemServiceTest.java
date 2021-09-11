package com.fa.DPA.CartItemService;

import com.fa.DPA.model.CartItem;
import com.fa.DPA.repos.CartItemRepository;
import com.fa.DPA.service.CartItemService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class CartItemServiceTest {
    private CartItemInput input = new CartItemInput();
    private CartItemOutput output = new CartItemOutput();
    CartItemService cartItemService;

    @Test
    public void testGetAllCartItem_UTC01() {
        CartItemRepository cartItemRepositoryMock = Mockito.mock(CartItemRepository.class);
        cartItemService = new CartItemService(cartItemRepositoryMock);

        long input_UTC01 = input.getGetAllCartItem_UTC01();
        List<CartItem> output_UTC01 = output.getGetAllCartItem_UTC01();

        Mockito.when(cartItemRepositoryMock.findAllByUserId(input_UTC01)).thenReturn(output_UTC01);
        List<CartItem> result = cartItemService.getAllCartItem(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testGetAllCartItem_UTC02() {
        CartItemRepository cartItemRepositoryMock = Mockito.mock(CartItemRepository.class);
        cartItemService = new CartItemService(cartItemRepositoryMock);

        long input_UTC02 = input.getGetAllCartItem_UTC02();
        List<CartItem> output_UTC02 = output.getGetAllCartItem_UTC02();

        Mockito.when(cartItemRepositoryMock.findAllByUserId(input_UTC02)).thenThrow(EntityNotFoundException.class);
        List<CartItem> result = cartItemService.getAllCartItem(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testGetAllCartItem_UTC03() {
        CartItemRepository cartItemRepositoryMock = Mockito.mock(CartItemRepository.class);
        cartItemService = new CartItemService(cartItemRepositoryMock);

        Long input_UTC03 = input.getGetAllCartItem_UTC03();
        List<CartItem> output_UTC03 = output.getGetAllCartItem_UTC02();

        Mockito.when(cartItemRepositoryMock.findAllByUserId(input_UTC03)).thenThrow(NullPointerException.class);
        List<CartItem> result = cartItemService.getAllCartItem(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testSaveItem_UTC01() {
        CartItemRepository cartItemRepositoryMock = Mockito.mock(CartItemRepository.class);
        cartItemService = new CartItemService(cartItemRepositoryMock);

        CartItem input_UTC01 = input.getSaveItem_UTC01();
        CartItem output_UTC01 = output.getSaveItem_UTC01();

        Mockito.when(cartItemRepositoryMock.save(input_UTC01)).thenReturn(output_UTC01);
        CartItem result = cartItemService.saveItem(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testSaveItem_UTC02() {
        CartItemRepository cartItemRepositoryMock = Mockito.mock(CartItemRepository.class);
        cartItemService = new CartItemService(cartItemRepositoryMock);

        CartItem input_UTC02 = input.getSaveItem_UTC02();
        CartItem output_UTC02 = output.getSaveItem_UTC02();

        Mockito.when(cartItemRepositoryMock.save(input_UTC02)).thenThrow(NullPointerException.class);
        CartItem result = cartItemService.saveItem(input_UTC02);

        Assert.assertEquals(result, output_UTC02);
    }

    @Test
    public void testSaveItem_UTC03() {
        CartItemRepository cartItemRepositoryMock = Mockito.mock(CartItemRepository.class);
        cartItemService = new CartItemService(cartItemRepositoryMock);

        CartItem input_UTC03 = input.getSaveItem_UTC03();
        CartItem output_UTC03 = output.getSaveItem_UTC02();

        Mockito.when(cartItemRepositoryMock.save(input_UTC03)).thenThrow(NullPointerException.class);
        CartItem result = cartItemService.saveItem(input_UTC03);

        Assert.assertEquals(result, output_UTC03);
    }

    @Test
    public void testSaveItem_UTC04() {
        CartItemRepository cartItemRepositoryMock = Mockito.mock(CartItemRepository.class);
        cartItemService = new CartItemService(cartItemRepositoryMock);

        CartItem input_UTC04 = input.getSaveItem_UTC04();
        CartItem output_UTC04 = output.getSaveItem_UTC02();

        Mockito.when(cartItemRepositoryMock.save(input_UTC04)).thenThrow(NullPointerException.class);
        CartItem result = cartItemService.saveItem(input_UTC04);

        Assert.assertEquals(result, output_UTC04);
    }
}
