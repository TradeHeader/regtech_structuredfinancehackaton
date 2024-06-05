package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.CrossRate;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
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
@RosettaDataRule("CrossRateCrossRate")
@ImplementedBy(CrossRateCrossRate.Default.class)
public interface CrossRateCrossRate extends Validator<CrossRate> {
	
	String NAME = "CrossRateCrossRate";
	String DEFINITION = "if forwardPoints exists then spotRate exists";
	
	ValidationResult<CrossRate> validate(RosettaPath path, CrossRate crossRate);
	
	class Default implements CrossRateCrossRate {
	
		@Override
		public ValidationResult<CrossRate> validate(RosettaPath path, CrossRate crossRate) {
			ComparisonResult result = executeDataRule(crossRate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CrossRate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CrossRate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CrossRate crossRate) {
			try {
				if (exists(MapperS.of(crossRate).<BigDecimal>map("getForwardPoints", _crossRate -> _crossRate.getForwardPoints())).getOrDefault(false)) {
					return exists(MapperS.of(crossRate).<BigDecimal>map("getSpotRate", _crossRate -> _crossRate.getSpotRate()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CrossRateCrossRate {
	
		@Override
		public ValidationResult<CrossRate> validate(RosettaPath path, CrossRate crossRate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CrossRate", path, DEFINITION);
		}
	}
}
