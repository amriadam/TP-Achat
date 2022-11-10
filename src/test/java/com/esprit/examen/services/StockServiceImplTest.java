package com.esprit.examen.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.*;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.StockRepository;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Stock;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StockServiceImplTest {
    
    @InjectMocks
    StockServiceImpl stockService;

    @Mock
    StockRepository dao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Order(1)
    public void testAddStock() {
        Stock s = new Stock("libelle",55,1);
        stockService.addStock(s);
        verify(dao,times(1)).save(s);
    }
    /*----------------------------------------------------------------------------------------------------*/

    @Test
    @Order(2)
    public  void testRetrieveAllStocks(){
        List <Stock> stockList = new ArrayList<Stock>();
        Stock s1 = new Stock("libelle1",75,5);
        Stock s2 = new Stock("libelle2",55,1);

        stockList.add(s1);
        stockList.add(s2);
        when(dao.findAll()).thenReturn(stockList);
        //test
        List<Stock> empList = stockService.retrieveAllStocks();
        assertEquals(2, empList.size());
        verify(dao, times(1)).findAll();

    }

    /*----------------------------------------------------------------------------------------------------*/

    private static final double DELTA = 1e-15;

    @Test
    @Order(3)
    public void testRetrieveStockById(){
        Stock s1 = new Stock("libelle1",75,5);

        when(dao.findById(1L)).thenReturn(Optional.of(s1));
        Stock stock = stockService.retrieveStock(1L);
        assertEquals(10,stock.getQte(),DELTA);
        assertEquals(75,stock.getQte(),DELTA);
        assertEquals(5,stock.getQteMin(), DELTA);
    }


}
