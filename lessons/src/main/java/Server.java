import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Socket socket;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Client joined in conversation");
            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Read
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = null;
                        try {
                            str = in.readUTF();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (str != null && str.equals("/end")) {
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
                            break;
                        }
                        System.out.println("Client say: " + str);
                    }
                }
            }).start();

            // Write
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Scanner scanner = new Scanner(System.in);
                    while (true) {
                        if (scanner.hasNextLine()) {
                            String str = scanner.nextLine();
                            if (!str.isEmpty()) {
                                try {
                                    out.writeUTF(str);
                                } catch (IOException e) {
                                    System.out.println("Client left the chat");
                                }
                            }

                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


