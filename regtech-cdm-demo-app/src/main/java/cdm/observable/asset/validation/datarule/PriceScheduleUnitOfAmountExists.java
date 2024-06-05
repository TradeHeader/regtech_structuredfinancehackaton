package cdm.observable.asset.validation.datarule;

import cdm.base.math.UnitType;
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
@RosettaDataRule("PriceScheduleUnitOfAmountExists")
@ImplementedBy(PriceScheduleUnitOfAmountExists.Default.class)
public interface PriceScheduleUnitOfAmountExists extends Validator<PriceSchedule> {
	
	String NAME = "PriceScheduleUnitOfAmountExists";
	String DEFINITION = "if priceType = PriceTypeEnum -> Variance or priceType = PriceTypeEnum -> Volatility or priceType = PriceTypeEnum -> Correlation then unit is absent and perUnitOf is absent else unit exists and perUnitOf exists";
	
	ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule priceSchedule);
	
	class Default implements PriceScheduleUnitOfAmountExists {
	
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
				if (areEqual(MapperS.of(priceSchedule).<PriceTypeEnum>map("getPriceType", _priceSchedule -> _priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.VARIANCE), CardinalityOperator.All).or(areEqual(MapperS.of(priceSchedule).<PriceTypeEnum>map("getPriceType", _priceSchedule -> _priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.VOLATILITY), CardinalityOperator.All)).or(areEqual(MapperS.of(priceSchedule).<PriceTypeEnum>map("getPriceType", _priceSchedule -> _priceSchedule.getPriceType()), MapperS.of(PriceTypeEnum.CORRELATION), CardinalityOperator.All)).getOrDefault(false)) {
					return notExists(MapperS.of(priceSchedule).<UnitType>map("getUnit", measureBase -> measureBase.getUnit())).and(notExists(MapperS.of(priceSchedule).<UnitType>map("getPerUnitOf", _priceSchedule -> _priceSchedule.getPerUnitOf())));
				}
				return exists(MapperS.of(priceSchedule).<UnitType>map("getUnit", measureBase -> measureBase.getUnit())).and(exists(MapperS.of(priceSchedule).<UnitType>map("getPerUnitOf", _priceSchedule -> _priceSchedule.getPerUnitOf())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PriceScheduleUnitOfAmountExists {
	
		@Override
		public ValidationResult<PriceSchedule> validate(RosettaPath path, PriceSchedule priceSchedule) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PriceSchedule", path, DEFINITION);
		}
	}
}
