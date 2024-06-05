package cdm.product.collateral.validation.datarule;

import cdm.product.collateral.Collateral;
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
@RosettaDataRule("CollateralCollateralchoice")
@ImplementedBy(CollateralCollateralchoice.Default.class)
public interface CollateralCollateralchoice extends Validator<Collateral> {
	
	String NAME = "CollateralCollateralchoice";
	String DEFINITION = "optional choice independentAmount, portfolioIdentifier, collateralPortfolio";
	
	ValidationResult<Collateral> validate(RosettaPath path, Collateral collateral);
	
	class Default implements CollateralCollateralchoice {
	
		@Override
		public ValidationResult<Collateral> validate(RosettaPath path, Collateral collateral) {
			ComparisonResult result = executeDataRule(collateral);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Collateral", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Collateral", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Collateral collateral) {
			try {
				return choice(MapperS.of(collateral), Arrays.asList("independentAmount", "portfolioIdentifier", "collateralPortfolio"), ChoiceRuleValidationMethod.OPTIONAL);
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CollateralCollateralchoice {
	
		@Override
		public ValidationResult<Collateral> validate(RosettaPath path, Collateral collateral) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Collateral", path, DEFINITION);
		}
	}
}
