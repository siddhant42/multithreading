package concurrent3;

import java.util.HashSet;
import java.util.Set;

class Dispatcher {
	//@GuardedBy("this") 
	private final Set<Taxi> taxis;
	//@GuardedBy("this") 
	private final Set<Taxi> availableTaxis;
	public Dispatcher() {
		taxis = new HashSet<Taxi>();
		availableTaxis = new HashSet<Taxi>();
	}
	public synchronized void notifyAvailable(Taxi taxi) {
		availableTaxis.add(taxi);
	}
/*	// deadlock prone because it want to get lock of dispatcher
	// while holding the Taxi object lock
	public synchronized Image getImage() {
		Image image = new Image();
		for (Taxi t : taxis)
			image.drawMarker(t.getLocation());
		return image;
	}*/
	// limit your synchronization to remove deadlock
	// as well as to improve performance.
	public Image getImage() {
		Set<Taxi> copy;
		synchronized (this) {
			copy = new HashSet<Taxi>(taxis);
		}
		Image image = new Image();
		for (Taxi t : copy)
			image.drawMarker(t.getLocation());
		return image;
	}
}
