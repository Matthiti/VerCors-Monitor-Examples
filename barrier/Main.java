public class Main {

	//@ context \no_obs;
	public void main() {
		Barrier barrier = new Barrier(3);
		//@ set_wait_level \lock(barrier), 0;
		//@ set_wait_level \cond(barrier), 1;
		//@ charge_obs barrier, 3;

		BarrierThread t1 = new BarrierThread(barrier);
		//@ transfer_ob barrier, t1.getId();
		BarrierThread t2 = new BarrierThread(barrier);
		//@ transfer_ob barrier, t2.getId();
		BarrierThread t3 = new BarrierThread(barrier);
		//@ transfer_ob barrier, t3.getId();
		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();
	}
}
