public class Creditor {

	private final BankAccount bankAccount;

	/*@
		ensures Perm(this.bankAccount, read);
		ensures this.bankAccount == bankAccount;
	 */
	public Creditor(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	/*@
		context Perm(bankAccount, read);
		context Perm(bankAccount.balance, write);
		context Perm(\Ot(bankAccount), read);
		context Perm(\wait_level(\lock(bankAccount)), read);
		context Perm(\wait_level(\cond(bankAccount)), read);
		context \wait_level(\lock(bankAccount)) == 0;
		context \wait_level(\cond(bankAccount)) == 1;
		context \no_obs;
		requires \Ot(bankAccount) > 0;
	 */
	public void start() {
		bankAccount.withdraw(1000);
	}

	public void join();

	//@ ensures \result == \current_thread;
	public int getId();
}
