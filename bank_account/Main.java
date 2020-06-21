public class Main {

	//@ context \no_obs;
	public void main() {
		BankAccount bankAccount = new BankAccount(0);
		//@ charge_ob bankAccount;
		//@ set_wait_level \lock(bankAccount), 0;
		//@ set_wait_level \cond(bankAccount), 1;

		Debtor debtor = new Debtor(bankAccount);
		//@ transfer_ob bankAccount, debtor.getId();
		Creditor creditor = new Creditor(bankAccount);

		debtor.start();
		creditor.start();

		debtor.join();
		creditor.join();
	}
}