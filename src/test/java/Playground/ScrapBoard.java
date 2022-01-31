package Playground;


import org.junit.Test;

public class ScrapBoard {

    @Test
    public void setDay() {
        int day = 4;

        switch (day) {
            case 1:
                System.out.println("Saturday");
                break;
            case 2:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Weekend");
                System.out.println("Weekend");
        }
    }
}
