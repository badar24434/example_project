package adv_prog.chat_server;

import java.rmi.RemoteException;
// ChatServer.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

public class ChatServer implements ChatService {
    private ConcurrentHashMap<String, ClientCallback> clients = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, UserProfile> userProfiles = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            ChatService stub = (ChatService) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatService", stub);
            System.out.println("Chat Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void broadcastMessage(ChatMessage message) throws RemoteException {
        for (ClientCallback client : clients.values()) {
            client.receiveMessage(message);
        }
    }

    @Override
    public void registerClient(String username, ClientCallback callback) throws RemoteException {
        clients.put(username, callback);
        userProfiles.put(username, new UserProfile(username));
        broadcastMessage(new ChatMessage("Server", username + " has joined the chat.", null, ChatMessage.MessageType.USER_JOIN));
        updateClientLists();
    }

    @Override
    public void unregisterClient(String username) throws RemoteException {
        clients.remove(username);
        broadcastMessage(new ChatMessage("Server", username + " has left the chat.", null, ChatMessage.MessageType.USER_LEAVE));
        updateClientLists();
    }

    @Override
    public List<String> getConnectedUsers() throws RemoteException {
        return new ArrayList<>(clients.keySet());
    }

    @Override
    public void sendPrivateMessage(ChatMessage message) throws RemoteException {
        ClientCallback recipient = clients.get(message.getRecipient());
        if (recipient != null) {
            recipient.receiveMessage(message);
            // Also send to sender
            ClientCallback sender = clients.get(message.getSender());
            if (sender != null) {
                sender.receiveMessage(message);
            }
        }
    }

    @Override
    public void updateProfile(String username, UserProfile profile) throws RemoteException {
        userProfiles.put(username, profile);
        broadcastMessage(new ChatMessage("Server", 
            username + " updated their profile", null, ChatMessage.MessageType.SYSTEM));
    }

    @Override
    public UserProfile getUserProfile(String username) throws RemoteException {
        return userProfiles.get(username);
    }

    @Override
    public void setUserStatus(String username, String status) throws RemoteException {
        UserProfile profile = userProfiles.get(username);
        if (profile != null) {
            profile.setStatus(status);
            broadcastMessage(new ChatMessage("Server", 
                username + " is now " + status, null, ChatMessage.MessageType.STATUS));
        }
    }

    private void updateClientLists() throws RemoteException {
        List<String> userList = getConnectedUsers();
        for (ClientCallback client : clients.values()) {
            client.updateUserList(userList);
        }
    }
}