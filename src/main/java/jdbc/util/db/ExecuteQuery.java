package jdbc.util.db;

import java.util.List;

public class ExecuteQuery <T>{
	private List<T> list;
	public T FirstOrDefault () {
		return  (list != null && list.size() > 0) ? list.get(0) : null;
	}
	public List<T> toList() {
		return list;
	}
	public ExecuteQuery(List<T> list) {
		this.list = list;
	}
}	
