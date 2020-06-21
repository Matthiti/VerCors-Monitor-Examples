public class BarrierThread {

	private final Barrier barrier;

	/*@
	  ensures Perm(this.barrier, read);
	  ensures this.barrier == barrier;
	 */
	public BarrierThread(Barrier barrier) {
		this.barrier = barrier;
	}

	/*@
	  context Perm(barrier, read);
	  context Perm(\Ot(barrier), read);
	  context Perm(\wait_level(\lock(barrier)), read);
	  context Perm(\wait_level(\cond(barrier)), read);
	  context Perm(barrier.n, read);
	  context \wait_level(\lock(barrier)) == 0;
	  context \wait_level(\cond(barrier)) == 1;
	  requires barrier.n > 0;
	  requires \Ot(barrier) > 0;
	  requires barrier.n <= \Ot(barrier);
	  requires \has_ob(\cond(barrier));
	  ensures  \no_obs;
	  ensures barrier.n == \old(barrier.n) - 1;
	 */
	public void start() {
		for (int i = 0; i < 10; i++) { }
		barrier.waitForBarrier();
		for (int j = 10; j < 20; j++) { }
	}

	public void join();

	//@ ensures \result == \current_thread;
	public int getId();
}
