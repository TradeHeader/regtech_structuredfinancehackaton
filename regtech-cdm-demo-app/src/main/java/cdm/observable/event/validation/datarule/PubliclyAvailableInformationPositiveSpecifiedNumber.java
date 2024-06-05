package cdm.observable.event.validation.datarule;

import cdm.observable.event.PubliclyAvailableInformation;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("PubliclyAvailableInformationPositiveSpecifiedNumber")
@ImplementedBy(PubliclyAvailableInformationPositiveSpecifiedNumber.Default.class)
public interface PubliclyAvailableInformationPositiveSpecifiedNumber extends Validator<PubliclyAvailableInformation> {
	
	String NAME = "PubliclyAvailableInformationPositiveSpecifiedNumber";
	String DEFINITION = "if specifiedNumber exists then specifiedNumber >= 0";
	
	ValidationResult<PubliclyAvailableInformation> validate(RosettaPath path, PubliclyAvailableInformation publiclyAvailableInformation);
	
	class Default implements PubliclyAvailableInformationPositiveSpecifiedNumber {
	
		@Override
		public ValidationResult<PubliclyAvailableInformation> validate(RosettaPath path, PubliclyAvailableInformation publiclyAvailableInformation) {
			ComparisonResult result = executeDataRule(publiclyAvailableInformation);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PubliclyAvailableInformation", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PubliclyAvailableInformation", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PubliclyAvailableInformation publiclyAvailableInformation) {
			try {
				if (exists(MapperS.of(publiclyAvailableInformation).<Integer>map("getSpecifiedNumber", _publiclyAvailableInformation -> _publiclyAvailableInformation.getSpecifiedNumber())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(publiclyAvailableInformation).<Integer>map("getSpecifiedNumber", _publiclyAvailableInformation -> _publiclyAvailableInformation.getSpecifiedNumber()), MapperS.of(0), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PubliclyAvailableInformationPositiveSpecifiedNumber {
	
		@Override
		public ValidationResult<PubliclyAvailableInformation> validate(RosettaPath path, PubliclyAvailableInformation publiclyAvailableInformation) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PubliclyAvailableInformation", path, DEFINITION);
		}
	}
}
