package mvc.domain;

import java.util.Date;

public class AccountEntry {
	private Date date;
	private double amount;
	private String description;

	public AccountEntry() {
	}

	public AccountEntry(Date date, double amount, String description) {
		System.out.println("created entry: ------------------------1>");
		this.date = date;
		System.out.println("created entry: ------------------------2>");
		this.amount = amount;
		System.out.println("created entry: ------------------------3>");
		this.description = description;
		System.out.println("created entry: ------------------------4>");
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
