package cdm.product.template.validation.datarule;

import cdm.product.template.EarlyTerminationProvision;
import cdm.product.template.MandatoryEarlyTermination;
import cdm.product.template.OptionalEarlyTermination;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("EarlyTerminationProvisionMandatoryEarlyTermination")
@ImplementedBy(EarlyTerminationProvisionMandatoryEarlyTermination.Default.class)
public interface EarlyTerminationProvisionMandatoryEarlyTermination extends Validator<EarlyTerminationProvision> {
	
	String NAME = "EarlyTerminationProvisionMandatoryEarlyTermination";
	String DEFINITION = "(mandatoryEarlyTermination exists or optionalEarlyTermination exists) or (mandatoryEarlyTermination exists and optionalEarlyTermination exists)";
	
	ValidationResult<EarlyTerminationProvision> validate(RosettaPath path, EarlyTerminationProvision earlyTerminationProvision);
	
	class Default implements EarlyTerminationProvisionMandatoryEarlyTermination {
	
		@Override
		public ValidationResult<EarlyTerminationProvision> validate(RosettaPath path, EarlyTerminationProvision earlyTerminationProvision) {
			ComparisonResult result = executeDataRule(earlyTerminationProvision);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "EarlyTerminationProvision", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "EarlyTerminationProvision", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(EarlyTerminationProvision earlyTerminationProvision) {
			try {
				return exists(MapperS.of(earlyTerminationProvision).<MandatoryEarlyTermination>map("getMandatoryEarlyTermination", _earlyTerminationProvision -> _earlyTerminationProvision.getMandatoryEarlyTermination())).or(exists(MapperS.of(earlyTerminationProvision).<OptionalEarlyTermination>map("getOptionalEarlyTermination", _earlyTerminationProvision -> _earlyTerminationProvision.getOptionalEarlyTermination()))).or(exists(MapperS.of(earlyTerminationProvision).<MandatoryEarlyTermination>map("getMandatoryEarlyTermination", _earlyTerminationProvision -> _earlyTerminationProvision.getMandatoryEarlyTermination())).and(exists(MapperS.of(earlyTerminationProvision).<OptionalEarlyTermination>map("getOptionalEarlyTermination", _earlyTerminationProvision -> _earlyTerminationProvision.getOptionalEarlyTermination()))));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements EarlyTerminationProvisionMandatoryEarlyTermination {
	
		@Override
		public ValidationResult<EarlyTerminationProvision> validate(RosettaPath path, EarlyTerminationProvision earlyTerminationProvision) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "EarlyTerminationProvision", path, DEFINITION);
		}
	}
}
