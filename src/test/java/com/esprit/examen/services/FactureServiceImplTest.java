package com.esprit.examen.services;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.sql.Date;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FactureServiceImplTest {

/*----------------------------------------------------------------------------------------------------*/
    @Autowired
    FactureRepository fr;
    @Autowired
    FactureServiceImpl fs;
    @InjectMocks
    FactureServiceImpl factureService;
    @Mock
    FactureRepository dao;
    @Mock
    FournisseurRepository daoFournisseur;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    /*----------------------------------------------------------------------------------------------------*/

    @Test
    public void addFactureTest() {
        Facture f = new Facture(1L,10,100,new Date(2000, 10, 21),new Date(2022, 11, 23),true,null,null,null);
        factureService.addFacture(f);
        verify(dao,times(1)).save(f);
    }

    /*----------------------------------------------------------------------------------------------------*/



    @Test
    public  void retrieveAllFacturesTest(){
        List <Facture> factureList = new ArrayList<Facture>();
        Facture facture1 = new Facture(1L,10,100,new Date(2000, 10, 21),new Date(2022, 11, 23),true,null,null,null);
        Facture facture2 = new Facture(2L,20,150,new Date(2022, 11, 1),new Date(2022, 10, 23),false,null,null,null);
        factureList.add(facture1);
        factureList.add(facture2);
        when(dao.findAll()).thenReturn(factureList);
        //test
        List<Facture> empList = factureService.retrieveAllFactures();
        assertEquals(2, empList.size());
        verify(dao, times(1)).findAll();

    }
    /*----------------------------------------------------------------------------------------------------*/

    private static final double DELTA = 1e-15;

    @Test
    public void retrieveFactureByIdTest(){
        Facture f = new Facture(1L,10,100,new Date(2000, 10, 21),new Date(2000, 10, 21),true,null,null,null);
        when(dao.findById(1L)).thenReturn(Optional.of(f));
        Facture fact = factureService.retrieveFacture(1L);
        assertEquals(10,fact.getMontantRemise(),DELTA);
        assertEquals(100,fact.getMontantFacture(),DELTA);
        assertEquals(new Date(2000, 10, 21),fact.getDateCreationFacture());
    }
    /*----------------------------------------------------------------------------------------------------*/

    @Test
    public void retrieveFactureByFournisseurTest(){
        Facture f = new Facture(1L,10,100,new Date(2000, 10, 21),new Date(2000, 10, 21),true,null,null,null);
        Set<Facture> setFacture = new HashSet<>();
        setFacture.add(f);
        Fournisseur fournisseur = new Fournisseur(1L,"f1","lib1", CategorieFournisseur.ORDINAIRE,setFacture,null,null);
        when(daoFournisseur.save(fournisseur)).thenReturn(fournisseur);
        assertNotNull(fournisseur.getFactures());
    }
    /*----------------------------------------------------------------------------------------------------*/

    @Test
    public void cancelFactureTest(){
        Facture f = new Facture(6L,10,100,new Date(2000, 10, 21),new Date(2000, 10, 21),false,null,null,null);
        fs.addFacture(f);
        fs.cancelFacture(6L);
        Facture fres = fr.findById(6L).get();
        assertEquals(true,fres.getArchivee());
    }



}