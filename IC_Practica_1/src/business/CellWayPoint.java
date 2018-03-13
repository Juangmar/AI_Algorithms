package business;

public class CellWayPoint extends Cell {

	private Integer queue;
	
	public CellWayPoint(int i, int j) {
		super(i, j);
		setQueue(null);
	}

	@Override
	public boolean isWalkable() {
		// TODO Auto-generated method stub
		return true;
	}

	public Integer getQueue() {
		return queue;
	}

	public void setQueue(Integer queue) {
		this.queue = queue;
	}

}
