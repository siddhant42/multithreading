package concurrent3;

class Taxi {
	//@GuardedBy("this") 
	private Point location, destination;
	private final Dispatcher dispatcher;
	public Taxi(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}
	public synchronized Point getLocation() {
		return location;
	}
/*	// deadlock prone because it want to get lock of dispatcher
	// while holding the Taxi object lock
	public synchronized void setLocation(Point location) {
		this.location = location;
		if (location.equals(destination))
			dispatcher.notifyAvailable(this);
	}*/
	// limit your synchronization to remove deadlock
	// as well as to improve performance.
	public void setLocation(Point location) {
		boolean reachedDestination;
		synchronized (this) {
			this.location = location;
			reachedDestination = location.equals(destination);
		}
		if (reachedDestination)
			dispatcher.notifyAvailable(this);
	}
}