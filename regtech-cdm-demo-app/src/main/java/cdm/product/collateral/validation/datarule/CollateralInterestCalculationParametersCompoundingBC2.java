package cdm.product.collateral.validation.datarule;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.CompoundingTypeEnum;
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
@RosettaDataRule("CollateralInterestCalculationParametersCompoundingBC2")
@ImplementedBy(CollateralInterestCalculationParametersCompoundingBC2.Default.class)
public interface CollateralInterestCalculationParametersCompoundingBC2 extends Validator<CollateralInterestCalculationParameters> {
	
	String NAME = "CollateralInterestCalculationParametersCompoundingBC2";
	String DEFINITION = "if compoundingType <> CompoundingTypeEnum -> Business then compoundingBusinessCenter is absent";
	
	ValidationResult<CollateralInterestCalculationParameters> validate(RosettaPath path, CollateralInterestCalculationParameters collateralInterestCalculationParameters);
	
	class Default implements CollateralInterestCalculationParametersCompoundingBC2 {
	
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
				if (notEqual(MapperS.of(collateralInterestCalculationParameters).<CompoundingTypeEnum>map("getCompoundingType", _collateralInterestCalculationParameters -> _collateralInterestCalculationParameters.getCompoundingType()), MapperS.of(CompoundingTypeEnum.BUSINESS), CardinalityOperator.Any).getOrDefault(false)) {
					return notExists(MapperS.of(collateralInterestCalculationParameters).<BusinessCenterEnum>mapC("getCompoundingBusinessCenter", _collateralInterestCalculationParameters -> _collateralInterestCalculationParameters.getCompoundingBusinessCenter()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CollateralInterestCalculationParametersCompoundingBC2 {
	
		@Override
		public ValidationResult<CollateralInterestCalculationParameters> validate(RosettaPath path, CollateralInterestCalculationParameters collateralInterestCalculationParameters) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralInterestCalculationParameters", path, DEFINITION);
		}
	}
}
