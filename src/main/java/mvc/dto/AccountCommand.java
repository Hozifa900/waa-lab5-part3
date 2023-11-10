package mvc.dto;

import jakarta.validation.constraints.NotNull;

public class AccountCommand {
    private String operation;
    @NotNull
    private Long accountNumber;
    private Double amount;
    private String accountHolder;

    public AccountCommand() {
    }

    public AccountCommand(String operation, Long accountNumber, Double amount, String accountHolder) {
        this.operation = operation;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.accountHolder = accountHolder;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
}
