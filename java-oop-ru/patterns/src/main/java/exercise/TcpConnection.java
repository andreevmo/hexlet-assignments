package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {

    private final String ipAddsress;
    private final int port;
    private Connection state;

    public TcpConnection(String ipAddsress, int port) {
        this.ipAddsress = ipAddsress;
        this.port = port;
        this.state = new Disconnected(this);
    }

    public String getCurrentState() {
        return state.toString();
    }

    public void setState(Connection state) {
        this.state = state;
    }
    public void connect() {
        this.state.connect();
    }

    public void disconnect() {
        this.state.disconnect();
    }

    public void write(String data) {
        this.state.write(data);
    }
}
// END
