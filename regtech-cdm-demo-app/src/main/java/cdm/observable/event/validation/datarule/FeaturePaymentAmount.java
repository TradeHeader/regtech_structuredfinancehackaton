package cdm.observable.event.validation.datarule;

import cdm.observable.event.FeaturePayment;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
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
@RosettaDataRule("FeaturePaymentAmount")
@ImplementedBy(FeaturePaymentAmount.Default.class)
public interface FeaturePaymentAmount extends Validator<FeaturePayment> {
	
	String NAME = "FeaturePaymentAmount";
	String DEFINITION = "if amount exists then amount >= 0.0";
	
	ValidationResult<FeaturePayment> validate(RosettaPath path, FeaturePayment featurePayment);
	
	class Default implements FeaturePaymentAmount {
	
		@Override
		public ValidationResult<FeaturePayment> validate(RosettaPath path, FeaturePayment featurePayment) {
			ComparisonResult result = executeDataRule(featurePayment);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FeaturePayment", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FeaturePayment", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FeaturePayment featurePayment) {
			try {
				if (exists(MapperS.of(featurePayment).<BigDecimal>map("getAmount", _featurePayment -> _featurePayment.getAmount())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(featurePayment).<BigDecimal>map("getAmount", _featurePayment -> _featurePayment.getAmount()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FeaturePaymentAmount {
	
		@Override
		public ValidationResult<FeaturePayment> validate(RosettaPath path, FeaturePayment featurePayment) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FeaturePayment", path, DEFINITION);
		}
	}
}
