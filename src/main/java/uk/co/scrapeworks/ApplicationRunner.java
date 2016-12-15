package uk.co.scrapeworks;

public class ApplicationRunner {
    public static void main(String... args){
        Application application = new Application();
        try {
            System.out.println(application.run());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
