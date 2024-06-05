package cdm.base.staticdata.party.validation.datarule;

import cdm.base.staticdata.party.NaturalPerson;
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
@RosettaDataRule("NaturalPersonNaturalPersonChoice")
@ImplementedBy(NaturalPersonNaturalPersonChoice.Default.class)
public interface NaturalPersonNaturalPersonChoice extends Validator<NaturalPerson> {
	
	String NAME = "NaturalPersonNaturalPersonChoice";
	String DEFINITION = "optional choice middleName, initial";
	
	ValidationResult<NaturalPerson> validate(RosettaPath path, NaturalPerson naturalPerson);
	
	class Default implements NaturalPersonNaturalPersonChoice {
	
		@Override
		public ValidationResult<NaturalPerson> validate(RosettaPath path, NaturalPerson naturalPerson) {
			ComparisonResult result = executeDataRule(naturalPerson);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NaturalPerson", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "NaturalPerson", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(NaturalPerson naturalPerson) {
			try {
				return choice(MapperS.of(naturalPerson), Arrays.asList("middleName", "initial"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements NaturalPersonNaturalPersonChoice {
	
		@Override
		public ValidationResult<NaturalPerson> validate(RosettaPath path, NaturalPerson naturalPerson) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "NaturalPerson", path, DEFINITION);
		}
	}
}
