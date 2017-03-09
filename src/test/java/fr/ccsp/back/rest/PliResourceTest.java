package fr.ccsp.back.rest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fr.ccsp.back.domain.Pli;
import fr.ccsp.back.repository.PliRepository;

@RunWith(MockitoJUnitRunner.class)
public class PliResourceTest {
	
	@InjectMocks
    public static PliResource pliResource;
	
    
    @Mock
    private PliRepository pliRepository;
    
    @BeforeClass
    public static void beforeClass() throws Exception {
    	
    }
    @AfterClass
    public static void afterClass() throws Exception {
    	
    }
    
    @Test
    public void test(){
    	
    	Pli pli = new Pli();
    	pli.setId("10");
    	pli.setDescription("Je suis un Pli nouvelle requete");
    	
    	Mockito.when(pliRepository.findOne("10")).thenReturn(pli);
    	
    	Pli resultat = pliResource.getPli("10");
    	
    	Assert.assertNotNull(resultat);
    }

}
