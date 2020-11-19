import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoteMain {
    public static void main(String[] args) throws IOException {

        String myIp = "192.168.1.29";
        String peerIp = "192.168.1.55";

        TCPProcess p = new TCPProcess(myIp, 5000);

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String s;


        while (!(s = input.readLine()).equals(""));

        p.addPeer(peerIp, 8000);

        while (!(s = input.readLine()).equals("Close")) {
            p.send(peerIp, s);
        }

        System.exit(0);
    }
}
