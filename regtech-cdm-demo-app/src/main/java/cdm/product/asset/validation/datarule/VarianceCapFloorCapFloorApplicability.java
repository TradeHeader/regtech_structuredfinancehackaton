package cdm.product.asset.validation.datarule;

import cdm.product.asset.BoundedVariance;
import cdm.product.asset.VarianceCapFloor;
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
@RosettaDataRule("VarianceCapFloorCapFloorApplicability")
@ImplementedBy(VarianceCapFloorCapFloorApplicability.Default.class)
public interface VarianceCapFloorCapFloorApplicability extends Validator<VarianceCapFloor> {
	
	String NAME = "VarianceCapFloorCapFloorApplicability";
	String DEFINITION = "if varianceCap = True then unadjustedVarianceCap exists or boundedVariance exists else unadjustedVarianceCap is absent and boundedVariance is absent";
	
	ValidationResult<VarianceCapFloor> validate(RosettaPath path, VarianceCapFloor varianceCapFloor);
	
	class Default implements VarianceCapFloorCapFloorApplicability {
	
		@Override
		public ValidationResult<VarianceCapFloor> validate(RosettaPath path, VarianceCapFloor varianceCapFloor) {
			ComparisonResult result = executeDataRule(varianceCapFloor);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VarianceCapFloor", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "VarianceCapFloor", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(VarianceCapFloor varianceCapFloor) {
			try {
				if (areEqual(MapperS.of(varianceCapFloor).<Boolean>map("getVarianceCap", _varianceCapFloor -> _varianceCapFloor.getVarianceCap()), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(varianceCapFloor).<BigDecimal>map("getUnadjustedVarianceCap", _varianceCapFloor -> _varianceCapFloor.getUnadjustedVarianceCap())).or(exists(MapperS.of(varianceCapFloor).<BoundedVariance>map("getBoundedVariance", _varianceCapFloor -> _varianceCapFloor.getBoundedVariance())));
				}
				return notExists(MapperS.of(varianceCapFloor).<BigDecimal>map("getUnadjustedVarianceCap", _varianceCapFloor -> _varianceCapFloor.getUnadjustedVarianceCap())).and(notExists(MapperS.of(varianceCapFloor).<BoundedVariance>map("getBoundedVariance", _varianceCapFloor -> _varianceCapFloor.getBoundedVariance())));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements VarianceCapFloorCapFloorApplicability {
	
		@Override
		public ValidationResult<VarianceCapFloor> validate(RosettaPath path, VarianceCapFloor varianceCapFloor) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VarianceCapFloor", path, DEFINITION);
		}
	}
}
