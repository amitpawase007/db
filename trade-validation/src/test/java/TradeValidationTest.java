import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.db.interview.example.bean.TradeDTO;

public class TradeValidationTest {
	private static Validator validator = null;

	@BeforeClass
	public static void initOnce() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testTradeId_Null() {
		TradeDTO trade = new TradeDTO();
		trade.setVersion(1);
		trade.setMaturityDate(LocalDate.now());

		Set<ConstraintViolation<TradeDTO>> violations = validator.validate(trade);

		Assert.assertTrue(violations.size() == 1);
		ConstraintViolation<TradeDTO> cv = violations.iterator().next();
		Assert.assertEquals("Trade Id can't be null", cv.getMessage());
	}

	@Test
	public void testTradeId_NotNull() {
		TradeDTO trade = new TradeDTO();
		trade.setTradeId(12345);
		trade.setVersion(1);
		trade.setMaturityDate(LocalDate.now());

		Set<ConstraintViolation<TradeDTO>> violations = validator.validate(trade);
		Assert.assertTrue(violations.size() == 0);
	}

	@Test
	public void testTradeVersion_Null() {
		TradeDTO trade = new TradeDTO();
		trade.setTradeId(12345);
		trade.setMaturityDate(LocalDate.now());

		Set<ConstraintViolation<TradeDTO>> violations = validator.validate(trade);

		Assert.assertTrue(violations.size() == 1);
		ConstraintViolation<TradeDTO> cv = violations.iterator().next();
		Assert.assertEquals("Trade version can't be null", cv.getMessage());
	}

	@Test
	public void testTradeVersion_Zero() {
		TradeDTO trade = new TradeDTO();
		trade.setTradeId(12345);
		trade.setVersion(0);
		trade.setMaturityDate(LocalDate.now());

		Set<ConstraintViolation<TradeDTO>> violations = validator.validate(trade);

		Assert.assertTrue(violations.size() == 1);
		ConstraintViolation<TradeDTO> cv = violations.iterator().next();
		Assert.assertEquals("Trade version must be greater than 0", cv.getMessage());
	}

	@Test
	public void testTradeMaturityDate_Null() {
		TradeDTO trade = new TradeDTO();
		trade.setTradeId(12345);
		trade.setVersion(2);

		Set<ConstraintViolation<TradeDTO>> violations = validator.validate(trade);

		Assert.assertTrue(violations.size() == 2);
		Object[] errors = { "Please provide maturity date", "Trade maturity date must not be less then Today" };

		Function<ConstraintViolation<TradeDTO>, String> mapper = (violation) -> violation.getMessage();

		Object[] actualErrors = violations.stream().map(mapper).toArray();
		Assert.assertArrayEquals(errors, actualErrors);
	}

}
