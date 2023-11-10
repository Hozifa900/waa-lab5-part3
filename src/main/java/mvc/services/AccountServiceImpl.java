package mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mvc.data.AccountRepository;
import mvc.domain.Account;
import mvc.dto.AccountCommand;

@Service
public class AccountServiceImpl {

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccount(Long accountnumber) {
        Account account = accountRepository.findByAccountnumber(accountnumber);
        return account;
    }

    public void deleteAccount(Long accountnumber) {
        accountRepository.findByAccountnumber(accountnumber);
    }

    public Account handlePost(AccountCommand accountCommand) {
        String operation = accountCommand.getOperation();
        Long accountNumber = accountCommand.getAccountNumber();
        String accountHolder = accountCommand.getAccountHolder();
        Double amount = accountCommand.getAmount();

        if (operation.equals("create")) {
            // create account
            if (accountNumber != 0 && accountHolder != null) {
                Account newAccount = new Account(accountNumber, accountHolder);
                Account account = accountRepository.save(newAccount);
            }
        }
        if (operation.equals("deposit")) {
            Account account = accountRepository.findByAccountnumber(accountNumber);
            account.deposit(amount);
            return accountRepository.save(account);
        }
        if (operation.equals("withdraw")) {
            Account account = accountRepository.findByAccountnumber(accountNumber);
            account.withdraw(amount);
            return accountRepository.save(account);
        }
        // create anonymous account
        return new Account();

    }
}
