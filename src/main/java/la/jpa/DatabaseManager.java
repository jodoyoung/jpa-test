package la.jpa;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;

import org.h2.tools.Server;

public enum DatabaseManager implements Closeable {
	INSTANCE;

	private Server dbServer;

	DatabaseManager() {
		try {
			dbServer = Server.createTcpServer("-tcpPort", "3060", "-tcpAllowOthers").start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		dbServer.stop();
	}
}