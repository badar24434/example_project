package adv_prog.chat_server;

// ClientCallback.java
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientCallback extends Remote {
    void receiveMessage(ChatMessage message) throws RemoteException;
    void updateUserList(List<String> users) throws RemoteException;
}
