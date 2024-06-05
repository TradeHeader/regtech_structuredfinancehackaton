package cdm.base.math.validation.datarule;

import cdm.base.math.DatedValue;
import cdm.base.math.Quantity;
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
@RosettaDataRule("QuantityAmountOnlyExists")
@ImplementedBy(QuantityAmountOnlyExists.Default.class)
public interface QuantityAmountOnlyExists extends Validator<Quantity> {
	
	String NAME = "QuantityAmountOnlyExists";
	String DEFINITION = "value exists and datedValue is absent";
	
	ValidationResult<Quantity> validate(RosettaPath path, Quantity quantity);
	
	class Default implements QuantityAmountOnlyExists {
	
		@Override
		public ValidationResult<Quantity> validate(RosettaPath path, Quantity quantity) {
			ComparisonResult result = executeDataRule(quantity);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Quantity", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Quantity", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Quantity quantity) {
			try {
				return exists(MapperS.of(quantity).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())).and(notExists(MapperS.of(quantity).<DatedValue>mapC("getDatedValue", measureSchedule -> measureSchedule.getDatedValue())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements QuantityAmountOnlyExists {
	
		@Override
		public ValidationResult<Quantity> validate(RosettaPath path, Quantity quantity) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Quantity", path, DEFINITION);
		}
	}
}
