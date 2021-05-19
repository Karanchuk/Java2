package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Непосредственно сервер
 */
public class MyServer {
    private List<ClientHandler> clients;
    private AuthService authService;

    public MyServer() {
        try (ServerSocket server = new ServerSocket(ChatConstants.PORT)){
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket); // Создаем нового клиента, передаем ему сервер и сокет
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    /**
     * synchronized для того, чтобы одновременно несколько пользователей
     * не подключались по одним авторизационным данным
     */
    public synchronized boolean isNickBusy(String nick) {
        return clients.stream().anyMatch(client -> client.getName().equals(nick));
        /*for (ClientHandler client : clients) {
            if (client.getName().equals(nick)) {
                return true;
            }
        }
        return false;*/
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    /**
     * Отправляет сообщение всем пользователям либо выбранному
     */
    public void broadcastMessage(String name, String message) {

        String[] parts = message.split("\\s+");
        if (parts[1].equals(ChatConstants.DIRECT) && parts.length > 3) {
            var ref = new Object() {
                String cutMessage = parts[0];
            };
            IntStream.range(3, parts.length).forEach(i -> {
                ref.cutMessage += parts[i] + " ";
            });
            ClientHandler thisClient = clients.stream().
                    filter(client -> client.getName().equals(name)).
                    findFirst().orElse(null);
            ClientHandler foundClient = clients.stream().
                    filter(client -> client.getName().equals(parts[2]) && name != parts[2]).
                    findFirst().
                    orElse(null);
            if (foundClient == null) {
                thisClient.sendMsg("Ошибка. Пользователь " + parts[2] + " не авторизован");
            } else {
                thisClient.sendMsg(ref.cutMessage);
                foundClient.sendMsg(ref.cutMessage);
            }
        } else {
            clients.forEach(client -> client.sendMsg(message));
        }
        /*for (ClientHandler client : clients) {
            client.sendMsg(message);
        }*/
    }
}
