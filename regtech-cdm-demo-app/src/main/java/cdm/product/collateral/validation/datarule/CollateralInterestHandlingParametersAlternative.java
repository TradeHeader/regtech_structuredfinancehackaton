package cdm.product.collateral.validation.datarule;

import cdm.product.collateral.AlternativeToInterestAmountEnum;
import cdm.product.collateral.CollateralInterestHandlingParameters;
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
@RosettaDataRule("CollateralInterestHandlingParametersAlternative")
@ImplementedBy(CollateralInterestHandlingParametersAlternative.Default.class)
public interface CollateralInterestHandlingParametersAlternative extends Validator<CollateralInterestHandlingParameters> {
	
	String NAME = "CollateralInterestHandlingParametersAlternative";
	String DEFINITION = "if alternativeToInterestAmount = AlternativeToInterestAmountEnum -> Other then alternativeProvision exists";
	
	ValidationResult<CollateralInterestHandlingParameters> validate(RosettaPath path, CollateralInterestHandlingParameters collateralInterestHandlingParameters);
	
	class Default implements CollateralInterestHandlingParametersAlternative {
	
		@Override
		public ValidationResult<CollateralInterestHandlingParameters> validate(RosettaPath path, CollateralInterestHandlingParameters collateralInterestHandlingParameters) {
			ComparisonResult result = executeDataRule(collateralInterestHandlingParameters);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralInterestHandlingParameters", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CollateralInterestHandlingParameters", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CollateralInterestHandlingParameters collateralInterestHandlingParameters) {
			try {
				if (areEqual(MapperS.of(collateralInterestHandlingParameters).<AlternativeToInterestAmountEnum>map("getAlternativeToInterestAmount", _collateralInterestHandlingParameters -> _collateralInterestHandlingParameters.getAlternativeToInterestAmount()), MapperS.of(AlternativeToInterestAmountEnum.OTHER), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(collateralInterestHandlingParameters).<String>map("getAlternativeProvision", _collateralInterestHandlingParameters -> _collateralInterestHandlingParameters.getAlternativeProvision()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CollateralInterestHandlingParametersAlternative {
	
		@Override
		public ValidationResult<CollateralInterestHandlingParameters> validate(RosettaPath path, CollateralInterestHandlingParameters collateralInterestHandlingParameters) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralInterestHandlingParameters", path, DEFINITION);
		}
	}
}
