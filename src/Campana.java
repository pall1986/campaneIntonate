public class Campana implements Runnable {
    private String suono;
    private int ripetizioni;
    private final Object lock;
    private final int mioTurno;
    private final int totaleCampane;
    private static int turnoCorrente = 0;

    public Campana(String suono, int ripetizioni, Object lock, int mioTurno, int totaleCampane) {
        this.suono = suono;
        this.ripetizioni = ripetizioni;
        this.lock = lock;
        this.mioTurno = mioTurno;
        this.totaleCampane = totaleCampane;
    }// fine costruttore

    @Override
    public void run() {
        for (int i = 0; i < ripetizioni; i++) {
            synchronized (lock) {// inizio sezione critica: acquisizione del lock ed esclusione mutua
                while (turnoCorrente  != mioTurno) {//se non è il mio turno, aspetto    
                    try {
                        lock.wait();// rilascio il lock e metto in attesa il thread
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();//
                    }
                }
                System.out.print (turnoCorrente+" " +suono);// eseguo l'azione
                turnoCorrente = (turnoCorrente + 1) % totaleCampane;// passo il turno alla campana successiva
                lock.notifyAll();// notifico le altre campane
            }
        }
    }
    
}
