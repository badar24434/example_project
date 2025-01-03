package adv_prog.chat_server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatService extends Remote {
    void broadcastMessage(ChatMessage message) throws RemoteException;
    void registerClient(String username, ClientCallback callback) throws RemoteException;
    void unregisterClient(String username) throws RemoteException;
    List<String> getConnectedUsers() throws RemoteException;
    void sendPrivateMessage(ChatMessage message) throws RemoteException;
    void updateProfile(String username, UserProfile profile) throws RemoteException;
    UserProfile getUserProfile(String username) throws RemoteException;
    void setUserStatus(String username, String status) throws RemoteException;
}