public class Barrier {

	private int n;

	/*@
	  requires n > 0;
	  ensures Perm(this.n, read);
	  ensures this.n == n;
	 */
	public Barrier(int n) {
		this.n = n;
	}

	/*@
	  context Perm(n, read);
	  context Perm(\Ot(this), read);
	  context Perm(\wait_level(\lock(this)), read);
	  context Perm(\wait_level(\cond(this)), read);
	  context \wait_level(\lock(this)) == 0;
	  context \wait_level(\cond(this)) == 1;
	  requires n > 0;
	  requires \Ot(this) > 0;
	  requires n <= \Ot(this);
	  requires \has_ob(\cond(this));
	  ensures !\has_ob(\cond(this));
	  ensures n == \old(n) - 1;
	 */
	public synchronized void waitForBarrier() {
		n--;
		if (n == 0) {
			notifyAll();
			//@ discharge_ob this;
		} else {
			//@ discharge_ob this;
			wait();
		}
	}
}