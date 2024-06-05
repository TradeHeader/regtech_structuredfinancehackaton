package cdm.base.staticdata.party.validation.datarule;

import cdm.base.staticdata.party.AncillaryEntity;
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
@RosettaDataRule("AncillaryEntityOneOf0")
@ImplementedBy(AncillaryEntityOneOf0.Default.class)
public interface AncillaryEntityOneOf0 extends Validator<AncillaryEntity> {
	
	String NAME = "AncillaryEntityOneOf0";
	String DEFINITION = "one-of";
	
	ValidationResult<AncillaryEntity> validate(RosettaPath path, AncillaryEntity ancillaryEntity);
	
	class Default implements AncillaryEntityOneOf0 {
	
		@Override
		public ValidationResult<AncillaryEntity> validate(RosettaPath path, AncillaryEntity ancillaryEntity) {
			ComparisonResult result = executeDataRule(ancillaryEntity);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AncillaryEntity", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "AncillaryEntity", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(AncillaryEntity ancillaryEntity) {
			try {
				return choice(MapperS.of(ancillaryEntity), Arrays.asList("ancillaryParty", "legalEntity"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements AncillaryEntityOneOf0 {
	
		@Override
		public ValidationResult<AncillaryEntity> validate(RosettaPath path, AncillaryEntity ancillaryEntity) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "AncillaryEntity", path, DEFINITION);
		}
	}
}
