package cdm.legaldocumentation.common.validation.datarule;

import cdm.legaldocumentation.common.Resource;
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
@RosettaDataRule("ResourceResourceChoice")
@ImplementedBy(ResourceResourceChoice.Default.class)
public interface ResourceResourceChoice extends Validator<Resource> {
	
	String NAME = "ResourceResourceChoice";
	String DEFINITION = "optional choice string, url";
	
	ValidationResult<Resource> validate(RosettaPath path, Resource resource);
	
	class Default implements ResourceResourceChoice {
	
		@Override
		public ValidationResult<Resource> validate(RosettaPath path, Resource resource) {
			ComparisonResult result = executeDataRule(resource);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Resource", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Resource", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Resource resource) {
			try {
				return choice(MapperS.of(resource), Arrays.asList("string", "url"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ResourceResourceChoice {
	
		@Override
		public ValidationResult<Resource> validate(RosettaPath path, Resource resource) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Resource", path, DEFINITION);
		}
	}
}
