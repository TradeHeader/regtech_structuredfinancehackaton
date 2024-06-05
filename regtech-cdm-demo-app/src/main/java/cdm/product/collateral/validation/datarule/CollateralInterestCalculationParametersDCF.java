package cdm.product.collateral.validation.datarule;

import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.product.collateral.CollateralInterestCalculationParameters;
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
@RosettaDataRule("CollateralInterestCalculationParametersDCF")
@ImplementedBy(CollateralInterestCalculationParametersDCF.Default.class)
public interface CollateralInterestCalculationParametersDCF extends Validator<CollateralInterestCalculationParameters> {
	
	String NAME = "CollateralInterestCalculationParametersDCF";
	String DEFINITION = "dayCountFraction = DayCountFractionEnum -> ACT_360 or dayCountFraction = DayCountFractionEnum -> ACT_365_FIXED";
	
	ValidationResult<CollateralInterestCalculationParameters> validate(RosettaPath path, CollateralInterestCalculationParameters collateralInterestCalculationParameters);
	
	class Default implements CollateralInterestCalculationParametersDCF {
	
		@Override
		public ValidationResult<CollateralInterestCalculationParameters> validate(RosettaPath path, CollateralInterestCalculationParameters collateralInterestCalculationParameters) {
			ComparisonResult result = executeDataRule(collateralInterestCalculationParameters);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralInterestCalculationParameters", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CollateralInterestCalculationParameters", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CollateralInterestCalculationParameters collateralInterestCalculationParameters) {
			try {
				return areEqual(MapperS.of(collateralInterestCalculationParameters).<DayCountFractionEnum>map("getDayCountFraction", _collateralInterestCalculationParameters -> _collateralInterestCalculationParameters.getDayCountFraction()), MapperS.of(DayCountFractionEnum.ACT_360), CardinalityOperator.All).or(areEqual(MapperS.of(collateralInterestCalculationParameters).<DayCountFractionEnum>map("getDayCountFraction", _collateralInterestCalculationParameters -> _collateralInterestCalculationParameters.getDayCountFraction()), MapperS.of(DayCountFractionEnum.ACT_365_FIXED), CardinalityOperator.All));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CollateralInterestCalculationParametersDCF {
	
		@Override
		public ValidationResult<CollateralInterestCalculationParameters> validate(RosettaPath path, CollateralInterestCalculationParameters collateralInterestCalculationParameters) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralInterestCalculationParameters", path, DEFINITION);
		}
	}
}
