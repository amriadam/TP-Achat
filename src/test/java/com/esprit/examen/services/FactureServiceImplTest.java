package com.esprit.examen.services;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.repositories.FactureRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import javax.persistence.EntityNotFoundException;
import java.time.LocalTime; // import the LocalTime class
import java.sql.Date;

@SpringBootTest
public class FactureServiceImplTest {

    @Autowired
    private  FactureServiceImpl serviceFacture;

    @Autowired
    private FactureRepository factureRepository;

    @Test
    public void addFacture() {
        Facture f = new Facture();
        f.setIdFacture(1L);
        f.setMontantFacture(100);
        f.setArchivee(false);
        f.setDetailsFacture(null);
        f.setReglements(null);
        f.setDateCreationFacture(new Date(2000, 11, 21));
        f.setMontantRemise(10);
        f.setDateDerniereModificationFacture(new Date(2010, 11, 21));
        f.setFournisseur(null);

        serviceFacture.addFacture(f);
        assertNotNull(factureRepository.findById(1L).get());
    }




}