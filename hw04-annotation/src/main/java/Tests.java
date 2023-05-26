public class Tests {

    private Object object;
    private int a = 2;
    private int b = 2;


    @Before
    public void settingInitParams() {
        object = new Object();
        System.out.println("Init complete");
    }

    @Test
    public void testA() throws Exception {
        if (a == 2) return;
        throw new Exception();
    }

    @Test
    public void testB() throws Exception {
        if (a == 2) return;
        throw new Exception();
    }

    @Test
    public void testAMultiplyB() throws Exception {
        if (a * b == 3) return;
        throw new Exception();
    }

    @Test
    public void testTwoPlusTwo() throws Exception {
        if (a + b == 4) return;
        throw new Exception();
    }


    @After
    public void clearResources() {
        object = null;
    }
}
