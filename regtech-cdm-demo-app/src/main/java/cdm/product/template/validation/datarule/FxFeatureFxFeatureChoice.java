package cdm.product.template.validation.datarule;

import cdm.product.template.FxFeature;
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
@RosettaDataRule("FxFeatureFxFeatureChoice")
@ImplementedBy(FxFeatureFxFeatureChoice.Default.class)
public interface FxFeatureFxFeatureChoice extends Validator<FxFeature> {
	
	String NAME = "FxFeatureFxFeatureChoice";
	String DEFINITION = "required choice composite, quanto, crossCurrency";
	
	ValidationResult<FxFeature> validate(RosettaPath path, FxFeature fxFeature);
	
	class Default implements FxFeatureFxFeatureChoice {
	
		@Override
		public ValidationResult<FxFeature> validate(RosettaPath path, FxFeature fxFeature) {
			ComparisonResult result = executeDataRule(fxFeature);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FxFeature", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "FxFeature", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(FxFeature fxFeature) {
			try {
				return choice(MapperS.of(fxFeature), Arrays.asList("composite", "quanto", "crossCurrency"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements FxFeatureFxFeatureChoice {
	
		@Override
		public ValidationResult<FxFeature> validate(RosettaPath path, FxFeature fxFeature) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "FxFeature", path, DEFINITION);
		}
	}
}
