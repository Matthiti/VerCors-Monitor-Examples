public class Debtor {

	private final BankAccount bankAccount;

	/*@
		ensures Perm(this.bankAccount, read);
		ensures this.bankAccount == bankAccount;
	 */
	public Debtor(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	/*@
		context Perm(bankAccount, read);
		context Perm(bankAccount.balance, write);
		context Perm(\Ot(bankAccount), read);
		context Perm(\Wt(bankAccount), read);
		context Perm(\wait_level(\lock(bankAccount)), read);
		context Perm(\wait_level(\cond(bankAccount)), read);
		context \wait_level(\lock(bankAccount)) == 0;
		context \wait_level(\cond(bankAccount)) == 1;
		requires \Ot(bankAccount) > 0;
		requires \has_ob(\cond(bankAccount));
		ensures \no_obs;
	 */
	public void start() {
		bankAccount.deposit(1000);
	}

	public void join();

	//@ ensures \result == \current_thread;
	public int getId();
}
