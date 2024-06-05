package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.PriceComposite;
import cdm.observable.asset.PriceOperandEnum;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.PriceTypeEnum;
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
@RosettaDataRule("PriceScheduleAccruedInterest")
@ImplementedBy(PriceScheduleAccruedInterest.Default.class)
public interface PriceScheduleAccruedInterest extends Validator<PriceSchedule> {
	
	String NAME = "PriceScheduleAccruedInterest";
	String DEFINITION = "if composite -> operandType = PriceOperandEnum -> AccruedInterest then priceType = PriceTypeEnum -> AssetPrice";
	
	ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule priceSchedule);
	
	class Default implements PriceScheduleAccruedInterest {
	
		@Override
		public ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule priceSchedule) {
			ComparisonResult result = executeDataRule(priceSchedule);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceSchedule", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PriceSchedule", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PriceSchedule priceSchedule) {
			try {
				if (areEqual(MapperS.of(priceSchedule).<PriceComposite>map("getComposite", _priceSchedule -> _priceSchedule.getComposite()).<PriceOperandEnum>map("getOperandType", priceComposite -> priceComposite.getOperandType()), MapperS.of(PriceOperandEnum.ACCRUED_INTEREST), CardinalityOperator.All).getOrDefault(false)) {
					return areEqual(MapperS.of(priceSchedule).<PriceTypeEnum>map("getPriceType", _priceSchedule -> _priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.ASSET_PRICE), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PriceScheduleAccruedInterest {
	
		@Override
		public ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule priceSchedule) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceSchedule", path, DEFINITION);
		}
	}
}
