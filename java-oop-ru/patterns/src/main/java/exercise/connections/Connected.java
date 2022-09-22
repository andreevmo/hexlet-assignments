package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    private TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        System.out.println("Error: try to connect when connection already established");
    }

    @Override
    public void disconnect() {
        tcpConnection.setState(new Disconnected(tcpConnection));
        System.out.println("Connection disconnected");
    }

    @Override
    public void write(String data) {
        System.out.println(data + " written");
    }

    @Override
    public String toString() {
        return "connected";
    }
}
// END
