package cdm.observable.asset.validation.datarule;

import cdm.base.math.ArithmeticOperationEnum;
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
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("PriceSchedulePositiveAssetPrice")
@ImplementedBy(PriceSchedulePositiveAssetPrice.Default.class)
public interface PriceSchedulePositiveAssetPrice extends Validator<PriceSchedule> {
	
	String NAME = "PriceSchedulePositiveAssetPrice";
	String DEFINITION = "if (priceType = PriceTypeEnum -> ExchangeRate or priceType = PriceTypeEnum -> AssetPrice) and arithmeticOperator is absent then value > 0";
	
	ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule priceSchedule);
	
	class Default implements PriceSchedulePositiveAssetPrice {
	
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
				if (areEqual(MapperS.of(priceSchedule).<PriceTypeEnum>map("getPriceType", _priceSchedule -> _priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.EXCHANGE_RATE), CardinalityOperator.All).or(areEqual(MapperS.of(priceSchedule).<PriceTypeEnum>map("getPriceType", _priceSchedule -> _priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.ASSET_PRICE), CardinalityOperator.All)).and(notExists(MapperS.of(priceSchedule).<ArithmeticOperationEnum>map("getArithmeticOperator", _priceSchedule -> _priceSchedule.getArithmeticOperator()))).getOrDefault(false)) {
					return greaterThan(MapperS.of(priceSchedule).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(BigDecimal.valueOf(0)), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PriceSchedulePositiveAssetPrice {
	
		@Override
		public ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule priceSchedule) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceSchedule", path, DEFINITION);
		}
	}
}
