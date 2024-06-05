package cdm.product.template.validation.datarule;

import cdm.product.template.CancelableProvision;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ChoiceRuleValidationMethod;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("CancelableProvisionEffectiveDate")
@ImplementedBy(CancelableProvisionEffectiveDate.Default.class)
public interface CancelableProvisionEffectiveDate extends Validator<CancelableProvision> {
	
	String NAME = "CancelableProvisionEffectiveDate";
	String DEFINITION = "optional choice effectiveDate, effectivePeriod";
	
	ValidationResult<CancelableProvision> validate(RosettaPath path, CancelableProvision cancelableProvision);
	
	class Default implements CancelableProvisionEffectiveDate {
	
		@Override
		public ValidationResult<CancelableProvision> validate(RosettaPath path, CancelableProvision cancelableProvision) {
			ComparisonResult result = executeDataRule(cancelableProvision);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CancelableProvision", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CancelableProvision", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CancelableProvision cancelableProvision) {
			try {
				return choice(MapperS.of(cancelableProvision), Arrays.asList("effectiveDate", "effectivePeriod"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CancelableProvisionEffectiveDate {
	
		@Override
		public ValidationResult<CancelableProvision> validate(RosettaPath path, CancelableProvision cancelableProvision) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CancelableProvision", path, DEFINITION);
		}
	}
}
