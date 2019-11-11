package ru.Lopatina.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {

    @Test
    public void testDistance1() {

        Point p1 = new Point(0,5);
        Point p2 = new Point(0,5);

        Assert.assertEquals(p1.distance(p2),0);
    }

    @Test
    public void testDistance2() {

        Point p1 = new Point(0,5);
        Point p2 = new Point(0,0);

        Assert.assertEquals(p1.distance(p2),5);
    }

    @Test
    public void testDistance3() {

        Point p1 = new Point(-7.5,0);
        Point p2 = new Point(3,0);

        Assert.assertEquals(p1.distance(p2),10.5);
    }

    @Test
    public void testDistance4() {

        Point p1 = new Point(-7.5,0);
        Point p2 = new Point(-5.5,0);

        Assert.assertEquals(p1.distance(p2),1);
    }
}
