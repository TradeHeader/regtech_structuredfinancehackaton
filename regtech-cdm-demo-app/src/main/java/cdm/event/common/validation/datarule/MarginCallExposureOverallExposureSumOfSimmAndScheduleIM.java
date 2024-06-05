package cdm.event.common.validation.datarule;

import cdm.base.math.UnitType;
import cdm.event.common.Exposure;
import cdm.event.common.MarginCallExposure;
import cdm.observable.asset.Money;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.expression.MapperMaths;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.metafields.FieldWithMetaString;
import java.math.BigDecimal;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("MarginCallExposureOverallExposureSumOfSimmAndScheduleIM")
@ImplementedBy(MarginCallExposureOverallExposureSumOfSimmAndScheduleIM.Default.class)
public interface MarginCallExposureOverallExposureSumOfSimmAndScheduleIM extends Validator<MarginCallExposure> {
	
	String NAME = "MarginCallExposureOverallExposureSumOfSimmAndScheduleIM";
	String DEFINITION = "if simmIMExposure exists and scheduleGridIMExposure exists then (overallExposure -> aggregateValue -> value = simmIMExposure -> aggregateValue -> value + scheduleGridIMExposure -> aggregateValue -> value) and (overallExposure -> aggregateValue -> unit -> currency = simmIMExposure -> aggregateValue -> unit -> currency) and (overallExposure -> aggregateValue -> unit -> currency = scheduleGridIMExposure -> aggregateValue -> unit -> currency)";
	
	ValidationResult<MarginCallExposure> validate(RosettaPath path, MarginCallExposure marginCallExposure);
	
	class Default implements MarginCallExposureOverallExposureSumOfSimmAndScheduleIM {
	
		@Override
		public ValidationResult<MarginCallExposure> validate(RosettaPath path, MarginCallExposure marginCallExposure) {
			ComparisonResult result = executeDataRule(marginCallExposure);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MarginCallExposure", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "MarginCallExposure", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(MarginCallExposure marginCallExposure) {
			try {
				if (exists(MapperS.of(marginCallExposure).<Exposure>map("getSimmIMExposure", _marginCallExposure -> _marginCallExposure.getSimmIMExposure())).and(exists(MapperS.of(marginCallExposure).<Exposure>map("getScheduleGridIMExposure", _marginCallExposure -> _marginCallExposure.getScheduleGridIMExposure()))).getOrDefault(false)) {
					return areEqual(MapperS.of(marginCallExposure).<Exposure>map("getOverallExposure", _marginCallExposure -> _marginCallExposure.getOverallExposure()).<Money>map("getAggregateValue", exposure -> exposure.getAggregateValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperMaths.<BigDecimal, BigDecimal, BigDecimal>add(MapperS.of(marginCallExposure).<Exposure>map("getSimmIMExposure", _marginCallExposure -> _marginCallExposure.getSimmIMExposure()).<Money>map("getAggregateValue", exposure -> exposure.getAggregateValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue()), MapperS.of(marginCallExposure).<Exposure>map("getScheduleGridIMExposure", _marginCallExposure -> _marginCallExposure.getScheduleGridIMExposure()).<Money>map("getAggregateValue", exposure -> exposure.getAggregateValue()).<BigDecimal>map("getValue", measureBase -> measureBase.getValue())), CardinalityOperator.All).and(areEqual(MapperS.of(marginCallExposure).<Exposure>map("getOverallExposure", _marginCallExposure -> _marginCallExposure.getOverallExposure()).<Money>map("getAggregateValue", exposure -> exposure.getAggregateValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), MapperS.of(marginCallExposure).<Exposure>map("getSimmIMExposure", _marginCallExposure -> _marginCallExposure.getSimmIMExposure()).<Money>map("getAggregateValue", exposure -> exposure.getAggregateValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), CardinalityOperator.All)).and(areEqual(MapperS.of(marginCallExposure).<Exposure>map("getOverallExposure", _marginCallExposure -> _marginCallExposure.getOverallExposure()).<Money>map("getAggregateValue", exposure -> exposure.getAggregateValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), MapperS.of(marginCallExposure).<Exposure>map("getScheduleGridIMExposure", _marginCallExposure -> _marginCallExposure.getScheduleGridIMExposure()).<Money>map("getAggregateValue", exposure -> exposure.getAggregateValue()).<UnitType>map("getUnit", measureBase -> measureBase.getUnit()).<FieldWithMetaString>map("getCurrency", unitType -> unitType.getCurrency()).<String>map("getValue", _f->_f.getValue()), CardinalityOperator.All));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MarginCallExposureOverallExposureSumOfSimmAndScheduleIM {
	
		@Override
		public ValidationResult<MarginCallExposure> validate(RosettaPath path, MarginCallExposure marginCallExposure) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MarginCallExposure", path, DEFINITION);
		}
	}
}
