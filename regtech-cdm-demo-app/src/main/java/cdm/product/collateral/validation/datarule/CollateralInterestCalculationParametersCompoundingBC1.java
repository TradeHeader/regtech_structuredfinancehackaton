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
@RosettaDataRule("CollateralInterestCalculationParametersCompoundingBC1")
@ImplementedBy(CollateralInterestCalculationParametersCompoundingBC1.Default.class)
public interface CollateralInterestCalculationParametersCompoundingBC1 extends Validator<CollateralInterestCalculationParameters> {
	
	String NAME = "CollateralInterestCalculationParametersCompoundingBC1";
	String DEFINITION = "if compoundingType = CompoundingTypeEnum -> Business then compoundingBusinessCenter exists";
	
	ValidationResult<CollateralInterestCalculationParameters> validate(RosettaPath path, CollateralInterestCalculationParameters collateralInterestCalculationParameters);
	
	class Default implements CollateralInterestCalculationParametersCompoundingBC1 {
	
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
				if (areEqual(MapperS.of(collateralInterestCalculationParameters).<CompoundingTypeEnum>map("getCompoundingType", _collateralInterestCalculationParameters -> _collateralInterestCalculationParameters.getCompoundingType()), MapperS.of(CompoundingTypeEnum.BUSINESS), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(collateralInterestCalculationParameters).<BusinessCenterEnum>mapC("getCompoundingBusinessCenter", _collateralInterestCalculationParameters -> _collateralInterestCalculationParameters.getCompoundingBusinessCenter()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CollateralInterestCalculationParametersCompoundingBC1 {
	
		@Override
		public ValidationResult<CollateralInterestCalculationParameters> validate(RosettaPath path, CollateralInterestCalculationParameters collateralInterestCalculationParameters) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralInterestCalculationParameters", path, DEFINITION);
		}
	}
}
