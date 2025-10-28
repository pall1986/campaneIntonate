public class App {
    public static void main(String[] args) {
        Object lock = new Object(); // oggetto condiviso per sincronizzazione

        Thread t1 = new Thread(new Campana("din", 5, lock, 0, 3));
        Thread t2 = new Thread(new Campana("don", 5, lock, 1, 3));
        Thread t3 = new Thread(new Campana("dan", 5, lock, 2, 3));

        t1.start();
        t2.start();
        t3.start();
    }
}