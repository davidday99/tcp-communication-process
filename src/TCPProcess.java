import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class TCPProcess {
    String ip;
    int port;

    ServerSocket server;

    HashMap<String, DataOutputStream> peers;

    public TCPProcess(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.peers = new HashMap<>();

        try {
            this.server = new ServerSocket(port);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Thread connectionHandler = new Thread(() -> {
            while (true) {
                try {
                    Socket peer = server.accept();
                    System.out.println("connection established to " + peer.getInetAddress());
                    peers.put(peer.getInetAddress().getHostAddress(), new DataOutputStream(peer.getOutputStream()));

                    Thread clientHandler = new Thread(() -> {
                        String sender = peer.getInetAddress().getHostAddress();
                        String s;
                        DataInputStream dataInputStream;
                        try {
                            dataInputStream = new DataInputStream(peer.getInputStream());

                            while ((s = dataInputStream.readUTF()) != null) {
                                System.out.println("Recipient: " + this.ip + ":" + this.port + " | Sender: " + sender + " | Message: " + s);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    clientHandler.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        connectionHandler.start();
    }

    public boolean addPeer(String ip, int port) {
        try {
            peers.put(ip, new DataOutputStream(new Socket(ip, port).getOutputStream()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendMessage(String recipient, String s) {
        try {
            this.peers.get(recipient).writeUTF(s);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
