package tobi;

import org.junit.Assert;
import org.junit.Test;

import backend.classes.Preisvariation;

import static junit.framework.TestCase.assertTrue;

public class PreisvariationTest {
    Preisvariation testPreisvariation = new Preisvariation("testName",111,222);

    @Test
    public void getVariationID(){ Assert.assertEquals(111,testPreisvariation.getVariationID()); }

    @Test
    public void setVariationID(){
        testPreisvariation.setVariationID(000);
        assertTrue(testPreisvariation.getVariationID() == 000);
    }
    @Test
    public void getWert(){
        Assert.assertEquals(222,testPreisvariation.getWert());
    }

    @Test
    public void setWert(){
        testPreisvariation.setWert(000);
        assertTrue(testPreisvariation.getVariationID() == 000);
    }

    @Test
    public void getName(){
        Assert.assertEquals("testName",testPreisvariation.getName());
    }

    @Test
    public void setName (){
        testPreisvariation.setName("andererName");
        assertTrue(testPreisvariation.getName().equals("andererName"));
    }

    @Test
    public void equals(){
        Assert.assertEquals(true,testPreisvariation.equals(testPreisvariation));
        Assert.assertEquals(false,testPreisvariation.equals(new Preisvariation("nochmalAnders",11111,1111)));
    }
}
