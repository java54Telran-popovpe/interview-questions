package telran.interviews;

import java.util.LinkedHashMap;
import java.util.Map;

//O[1] complexity
//TODO Data Structure
public class ConnectionPool {
	
		LinkedHashMap<Long,Connection> map;
		int size;
		
		@SuppressWarnings("serial")
		public ConnectionPool(int size) {
			this.size = size;
			map = new LinkedHashMap<>(16, 0.75f, true ) {
				@Override
				protected boolean removeEldestEntry(Map.Entry<Long, Connection> entry) {
					return size() > ConnectionPool.this.size;	
				}
			};
		}
	public Connection getConnection (Connection connection) {
		//TODO returns connection from the pool if it exist
		//otherwise creates new connection, adds in pool and returns new creates connection
		return map.computeIfAbsent(connection.id(), k -> new Connection( k ));
	}
	public boolean isInPool(long id) {
		//TODO returns true if connection exists in the pool
		//othewise returns false
		return map.get(id) != null;
	}

}
