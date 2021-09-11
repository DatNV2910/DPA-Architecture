package com.fa.DPA.NewsService;

import com.fa.DPA.model.News;
import com.fa.DPA.repos.NewsRepository;
import com.fa.DPA.service.NewsService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class NewsServiceTest {
    private NewsInput input = new NewsInput();
    private NewsOutput output = new NewsOutput();
    private NewsRepository newsRepositoryMock = Mockito.mock(NewsRepository.class);
    private NewsService service = new NewsService(newsRepositoryMock);

//    @Test
//    public void testGetAllNewsPaging_UTC01() {
//        Pageable input_UTC01 = input.getGetAllNewsPaging_UTC01();
//        Page<News> output_UTC01 = output.getGetAllNewsPaging_UTC01();
//
//        Mockito.when(newsRepositoryMock.findAll(input_UTC01)).thenReturn(output_UTC01);
//        Page<News> result = service.getAllNewsPaging(input_UTC01);
//
//        Assert.assertEquals(output_UTC01, result);
//    }

//    @Test
////    public void testGetAllNewsPaging_UTC02() {
////        Pageable input_UTC02 = input.getGetAllNewsPaging_UTC02();
////        Page<News> output_UTC02 = output.getGetAllNewsPaging_UTC02();
////
////        Mockito.when(newsRepositoryMock.findAll(input_UTC02)).thenThrow(NullPointerException.class);
//////        Page<News> result = service.getAllNewsPaging(input_UTC02);
////
////        Assert.assertEquals(output_UTC02, result);
////    }

    @Test
    public void testGetNewsById_UTC01() {
        Long input_UTC01 = input.getGetNewsById_UTC01();
        Optional<News> output_UTC01 = output.getGetNewsById_UTC01();

        Mockito.when(newsRepositoryMock.findById(input_UTC01)).thenReturn(output_UTC01);
        Optional<News> result = service.getNewsById(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testGetNewsById_UTC02() {
        Long input_UTC02 = input.getGetNewsById_UTC02();
        Optional<News> output_UTC02 = output.getGetNewsById_UTC02();

        Mockito.when(newsRepositoryMock.findById(input_UTC02)).thenThrow(EntityNotFoundException.class);
        Optional<News> result = service.getNewsById(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testGetNewsById_UTC03() {
        Long input_UTC03 = input.getGetNewsById_UTC03();
        Optional<News> output_UTC03 = output.getGetNewsById_UTC02();

        Mockito.when(newsRepositoryMock.findById(input_UTC03)).thenThrow(NullPointerException.class);
        Optional<News> result = service.getNewsById(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testSaveNews_UTC01() {
        News input_UTC01 = input.getSaveNews_UTC01();
        News output_UTC01 = output.getSaveNews_UTC01();

        Mockito.when(newsRepositoryMock.save(input_UTC01)).thenReturn(output_UTC01);
        News result = service.saveNews(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testSaveNews_UTC02() {
        News input_UTC02 = input.getSaveNews_UTC02();
        News output_UTC02 = output.getSaveNews_UTC02();

        Mockito.when(newsRepositoryMock.save(input_UTC02)).thenReturn(output_UTC02);
        News result = service.saveNews(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testSaveNews_UTC03() {
        News input_UTC03 = input.getSaveNews_UTC03();
        News output_UTC03 = output.getSaveNews_UTC03();

        Mockito.when(newsRepositoryMock.save(input_UTC03)).thenThrow(NullPointerException.class);
        News result = service.saveNews(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testSaveNews_UTC04() {
        News input_UTC04 = input.getSaveNews_UTC04();
        News output_UTC04 = output.getSaveNews_UTC03();

        Mockito.when(newsRepositoryMock.save(input_UTC04)).thenThrow(NullPointerException.class);
        News result = service.saveNews(input_UTC04);

        Assert.assertEquals(output_UTC04, result);
    }

    @Test
    public void testSaveNews_UTC05() {
        News input_UTC05 = input.getSaveNews_UTC05();
        News output_UTC05 = output.getSaveNews_UTC03();

        Mockito.when(newsRepositoryMock.save(input_UTC05)).thenThrow(NullPointerException.class);
        News result = service.saveNews(input_UTC05);

        Assert.assertEquals(output_UTC05, result);
    }

    @Test
    public void testSaveNews_UTC06() {
        News input_UTC06 = input.getSaveNews_UTC06();
        News output_UTC06 = output.getSaveNews_UTC03();

        Mockito.when(newsRepositoryMock.save(input_UTC06)).thenThrow(NullPointerException.class);
        News result = service.saveNews(input_UTC06);

        Assert.assertEquals(output_UTC06, result);
    }

    @Test
    public void testSaveNews_UTC07() {
        News input_UTC07 = input.getSaveNews_UTC07();
        News output_UTC07 = output.getSaveNews_UTC03();

        Mockito.when(newsRepositoryMock.save(input_UTC07)).thenThrow(NullPointerException.class);
        News result = service.saveNews(input_UTC07);

        Assert.assertEquals(output_UTC07, result);
    }

    @Test
    public void testSaveNews_UTC08() {
        News input_UTC08 = input.getSaveNews_UTC08();
        News output_UTC08 = output.getSaveNews_UTC03();

        Mockito.when(newsRepositoryMock.save(input_UTC08)).thenThrow(NullPointerException.class);
        News result = service.saveNews(input_UTC08);

        Assert.assertEquals(output_UTC08, result);
    }

    @Test
    public void testSaveNews_UTC09() {
        News input_UTC09 = input.getSaveNews_UTC09();
        News output_UTC09 = output.getSaveNews_UTC03();

        Mockito.when(newsRepositoryMock.save(input_UTC09)).thenThrow(NullPointerException.class);
        News result = service.saveNews(input_UTC09);

        Assert.assertEquals(output_UTC09, result);
    }

    @Test
    public void testSaveNews_UTC10() {
        News input_UTC10 = input.getSaveNews_UTC10();
        News output_UTC10 = output.getSaveNews_UTC03();

        Mockito.when(newsRepositoryMock.save(input_UTC10)).thenThrow(NullPointerException.class);
        News result = service.saveNews(input_UTC10);

        Assert.assertEquals(output_UTC10, result);
    }
}
