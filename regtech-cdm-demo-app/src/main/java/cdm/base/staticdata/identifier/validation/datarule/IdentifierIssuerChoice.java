package cdm.base.staticdata.identifier.validation.datarule;

import cdm.base.staticdata.identifier.Identifier;
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
@RosettaDataRule("IdentifierIssuerChoice")
@ImplementedBy(IdentifierIssuerChoice.Default.class)
public interface IdentifierIssuerChoice extends Validator<Identifier> {
	
	String NAME = "IdentifierIssuerChoice";
	String DEFINITION = "required choice issuerReference, issuer";
	
	ValidationResult<Identifier> validate(RosettaPath path, Identifier identifier);
	
	class Default implements IdentifierIssuerChoice {
	
		@Override
		public ValidationResult<Identifier> validate(RosettaPath path, Identifier identifier) {
			ComparisonResult result = executeDataRule(identifier);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Identifier", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Identifier", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Identifier identifier) {
			try {
				return choice(MapperS.of(identifier), Arrays.asList("issuerReference", "issuer"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements IdentifierIssuerChoice {
	
		@Override
		public ValidationResult<Identifier> validate(RosettaPath path, Identifier identifier) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Identifier", path, DEFINITION);
		}
	}
}
