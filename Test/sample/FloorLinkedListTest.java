//package sample;
//        import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.Assert.assertEquals;
//
//
//
//class FloorLinkedListTest {
//
//    private Floor a,b,c;
//
//
//    @BeforeEach
//    void setUp(){
//        a=new Floor(-1,"MODERATE" ,15);
//        b=new Floor(1,"HIGH", 25);
//        c=new Floor(2,"LOW",5);
//
//    }
//
//
//
//
//
//    @Test
//    void addFloor(Floor e) {
//        addFloor(a);
//        addFloor(b);
//        addFloor(c);
//        assertEquals(1,b.getFloorNum());
//        assertEquals("High",b.getFloorSecurityLevel());
//        assertEquals(25,b.getFloorTemperature());
//        assertEquals(2,c.getFloorNum());
//        assertEquals("LOW",c.getFloorSecurityLevel());
//        assertEquals(5,c.getFloorTemperature());
//        assertEquals(null,a.getFloorNum());
//        assertEquals(null,a.getFloorSecurityLevel());
//        assertEquals(null,a.getFloorTemperature());
//    }
//
//    @AfterEach
//    void tearDown() {
//        a = b = c = null;
//    }
//}