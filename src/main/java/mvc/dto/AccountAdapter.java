package mvc.dto;

import mvc.domain.Account;

public class AccountAdapter {
    static public AccountCommand getAccountCommand(Account account) {
        AccountCommand accountCommand = new AccountCommand();
        accountCommand.setAccountHolder(account.getAccountHolder());
        accountCommand.setAccountNumber(account.getAccountnumber());
        return accountCommand;
    }

    static public Account getAccount(AccountCommand accountCommand) {
        Account account = new Account();
        account.setAccountHolder(accountCommand.getAccountHolder());
        account.setAccountnumber(accountCommand.getAccountNumber());
        return account;
    }

}
