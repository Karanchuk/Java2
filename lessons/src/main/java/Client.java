import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final Scanner scanner = new Scanner(System.in);

    public Client() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        readConsole();
    }

    public static void main(String[] args) {
        new Client();
    }

    public void openConnection() throws IOException {
        final String SERVER_ADDR = "localhost";
        final int SERVER_PORT = 8189;
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {

                        String strFromServer = in.readUTF();
                        if (strFromServer.equalsIgnoreCase("/end")) {
                            break;
                        }
                        System.out.println("Server say: " + strFromServer);
                    }
                } catch (Exception e) {
                    System.out.println("Connection closed");
                }
            }
        }).start();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readConsole() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if  (scanner.hasNextLine()) {
                        String str = scanner.nextLine();

                        if (!str.isEmpty()) {
                            if (str.equalsIgnoreCase("end") || str.equalsIgnoreCase("close")) {
                                try {
                                    out.writeUTF("/end");
                                    closeConnection();
                                    break;
                                } catch (IOException exc) {
                                    exc.printStackTrace();
                                }
                            }

                            try {
                                out.writeUTF(str);
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("Ошибка отправки сообщения");
                            }
                        }

                    }
                }
            }
        }).start();
    }
}

