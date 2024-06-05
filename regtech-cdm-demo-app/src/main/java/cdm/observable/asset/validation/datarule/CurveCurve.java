package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.Curve;
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
@RosettaDataRule("CurveCurve")
@ImplementedBy(CurveCurve.Default.class)
public interface CurveCurve extends Validator<Curve> {
	
	String NAME = "CurveCurve";
	String DEFINITION = "one-of";
	
	ValidationResult<Curve> validate(RosettaPath path, Curve curve);
	
	class Default implements CurveCurve {
	
		@Override
		public ValidationResult<Curve> validate(RosettaPath path, Curve curve) {
			ComparisonResult result = executeDataRule(curve);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Curve", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Curve", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Curve curve) {
			try {
				return choice(MapperS.of(curve), Arrays.asList("interestRateCurve", "commodityCurve"), ChoiceRuleValidationMethod.REQUIRED);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CurveCurve {
	
		@Override
		public ValidationResult<Curve> validate(RosettaPath path, Curve curve) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Curve", path, DEFINITION);
		}
	}
}
