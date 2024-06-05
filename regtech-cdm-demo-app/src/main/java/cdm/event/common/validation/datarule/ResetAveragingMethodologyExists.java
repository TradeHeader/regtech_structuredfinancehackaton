package cdm.event.common.validation.datarule;

import cdm.event.common.Reset;
import cdm.observable.event.Observation;
import cdm.observable.event.metafields.ReferenceWithMetaObservation;
import cdm.product.template.AveragingCalculation;
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
@RosettaDataRule("ResetAveragingMethodologyExists")
@ImplementedBy(ResetAveragingMethodologyExists.Default.class)
public interface ResetAveragingMethodologyExists extends Validator<Reset> {
	
	String NAME = "ResetAveragingMethodologyExists";
	String DEFINITION = "if observations count > 1 then averagingMethodology exists";
	
	ValidationResult<Reset> validate(RosettaPath path, Reset reset);
	
	class Default implements ResetAveragingMethodologyExists {
	
		@Override
		public ValidationResult<Reset> validate(RosettaPath path, Reset reset) {
			ComparisonResult result = executeDataRule(reset);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Reset", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Reset", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Reset reset) {
			try {
				if (greaterThan(MapperS.of(MapperS.of(reset).<ReferenceWithMetaObservation>mapC("getObservations", _reset -> _reset.getObservations()).<Observation>map("getValue", _f->_f.getValue()).resultCount()), MapperS.of(1), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(reset).<AveragingCalculation>map("getAveragingMethodology", _reset -> _reset.getAveragingMethodology()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ResetAveragingMethodologyExists {
	
		@Override
		public ValidationResult<Reset> validate(RosettaPath path, Reset reset) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Reset", path, DEFINITION);
		}
	}
}
