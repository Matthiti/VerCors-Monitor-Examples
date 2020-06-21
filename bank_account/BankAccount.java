public class BankAccount {

	private int balance;

	/*@
		requires balance >= 0;
		ensures Perm(this.balance, write);
		ensures this.balance == balance;
	 */
	public BankAccount(int balance) {
		this.balance = balance;
	}

	/*@
		context Perm(balance, read);
		ensures \result == balance;
	 */
	public int getBalance() {
		return this.balance;
	}

	/*@
		context Perm(balance, write);
		context Perm(\Wt(this), read);
		context Perm(\Ot(this), read);
		context Perm(\wait_level(\lock(this)), read);
	  	context Perm(\wait_level(\cond(this)), read);
	  	context \wait_level(\lock(this)) == 0;
	  	context \wait_level(\cond(this)) == 1;
		requires \Ot(this) > 0;
		requires amount >= 0;
		ensures balance == \old(balance) + amount;
		ensures \Wt(this) == 0;
	 */
	public synchronized void deposit(int amount) {
		balance = balance + amount;
		notifyAll();
		//@ discharge_ob this;
	}

	/*@
		context Perm(balance, write);
		context Perm(\Ot(this), read);
		context Perm(\wait_level(\lock(this)), read);
	  	context Perm(\wait_level(\cond(this)), read);
	  	context \wait_level(\lock(this)) == 0;
	  	context \wait_level(\cond(this)) == 1;
		requires balance < amount ==> \Ot(this) > 0;
		requires amount >= 0;
		ensures balance == \old(balance) - amount;
	 */
	public synchronized void withdraw(int amount) {
		if (balance < amount) {
			wait();
		}
		balance = balance - amount;
	}
}
