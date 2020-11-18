public class RemoteMain {
    public static void main(String[] args) {

        String myIp = "127.0.0.1";
        String peerIp = "127.0.0.1";

        TCPProcess p = new TCPProcess(myIp, 8000);

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        p.addPeer(peerIp, 5000);

        p.sendMessage(peerIp, "This is a message from " + p.ip);
    }
}
