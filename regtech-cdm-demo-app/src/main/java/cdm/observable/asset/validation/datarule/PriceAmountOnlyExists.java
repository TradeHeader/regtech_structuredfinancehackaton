package cdm.observable.asset.validation.datarule;

import cdm.base.math.DatedValue;
import cdm.observable.asset.Price;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("PriceAmountOnlyExists")
@ImplementedBy(PriceAmountOnlyExists.Default.class)
public interface PriceAmountOnlyExists extends Validator<Price> {
	
	String NAME = "PriceAmountOnlyExists";
	String DEFINITION = "value exists and datedValue is absent";
	
	ValidationResult<Price> validate(RosettaPath path, Price price);
	
	class Default implements PriceAmountOnlyExists {
	
		@Override
		public ValidationResult<Price> validate(RosettaPath path, Price price) {
			ComparisonResult result = executeDataRule(price);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Price", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Price", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Price price) {
			try {
				return exists(MapperS.of(price).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).and(notExists(MapperS.of(price).<DatedValue>mapC("getDatedValue", measureSchedule -> measureSchedule.getDatedValue())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PriceAmountOnlyExists {
	
		@Override
		public ValidationResult<Price> validate(RosettaPath path, Price price) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Price", path, DEFINITION);
		}
	}
}
