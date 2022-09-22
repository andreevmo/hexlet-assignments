package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection{

    private TcpConnection tcpConnection;
    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        tcpConnection.setState(new Connected(tcpConnection));
        System.out.println("Connection established");;
    }

    @Override
    public void disconnect() {
        System.out.println("Error: try to disconnect when connection disconnected.");
    }

    @Override
    public void write(String data) {
        System.out.println("Error: " + data + " not written");
    }

    @Override
    public String toString() {
        return "disconnected";
    }
}
// END
