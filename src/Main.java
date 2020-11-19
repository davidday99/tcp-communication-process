public class Main {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            TCPProcess p = new TCPProcess("127.0.0.1", 5000);

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            p.addPeer("127.0.0.1", 8000);
            p.send("127.0.0.1", "Message sent from " + p.ip + ":" + p.port);
        });

        Thread t2 = new Thread(() -> {
            TCPProcess p = new TCPProcess("127.0.0.1", 8000);

            p.addPeer("127.0.0.1", 5000);
            p.send("127.0.0.1", "Message sent from " + p.ip + ":" + p.port);
        });

        t1.start();
        t2.start();
    }
}
