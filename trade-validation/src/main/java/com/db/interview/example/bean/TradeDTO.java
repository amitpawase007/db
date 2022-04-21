package com.db.interview.example.bean;

import javax.validation.constraints.*;

import com.db.interview.example.constraints.TradeMaturityDate;

/***
 * Bean Validation - JSR 380
 * 
 * @author AmitPawase
 *
 */
public class TradeDTO {

	@NotNull(message = "Trade Id can't be null")
	private Integer tradeId;

	@NotNull(message = "Trade version can't be null")
	@Min(value = 1, message = "Trade version must be greater than 0")
	private Integer version;

	private Integer counterPartyId;

	private Integer bookId;

	@NotNull(message = "Please provide maturity date")
	@TradeMaturityDate(message = "Trade maturity date must not be less then Today")
	private java.time.LocalDate maturityDate;

	private java.time.LocalDate createdDate;

	private Boolean expired = false;

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getCounterPartyId() {
		return counterPartyId;
	}

	public void setCounterPartyId(Integer counterPartyId) {
		this.counterPartyId = counterPartyId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public java.time.LocalDate getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(java.time.LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}

	public java.time.LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.time.LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getExpired() {
		return expired;
	}

	public void setExpired(Boolean expired) {
		this.expired = expired;
	}

}