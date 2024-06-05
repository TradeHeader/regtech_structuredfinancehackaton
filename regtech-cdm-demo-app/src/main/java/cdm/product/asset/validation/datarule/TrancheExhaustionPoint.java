package cdm.product.asset.validation.datarule;

import cdm.product.asset.Tranche;
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
@RosettaDataRule("TrancheExhaustionPoint")
@ImplementedBy(TrancheExhaustionPoint.Default.class)
public interface TrancheExhaustionPoint extends Validator<Tranche> {
	
	String NAME = "TrancheExhaustionPoint";
	String DEFINITION = "exhaustionPoint >= 0.0 and exhaustionPoint <= 1.0";
	
	ValidationResult<Tranche> validate(RosettaPath path, Tranche tranche);
	
	class Default implements TrancheExhaustionPoint {
	
		@Override
		public ValidationResult<Tranche> validate(RosettaPath path, Tranche tranche) {
			ComparisonResult result = executeDataRule(tranche);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Tranche", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "Tranche", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(Tranche tranche) {
			try {
				return greaterThanEquals(MapperS.of(tranche).<BigDecimal>map("getExhaustionPoint", _tranche -> _tranche.getExhaustionPoint()), MapperS.of(new BigDecimal("0.0")), CardinalityOperator.All).and(lessThanEquals(MapperS.of(tranche).<BigDecimal>map("getExhaustionPoint", _tranche -> _tranche.getExhaustionPoint()), MapperS.of(new BigDecimal("1.0")), CardinalityOperator.All));
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements TrancheExhaustionPoint {
	
		@Override
		public ValidationResult<Tranche> validate(RosettaPath path, Tranche tranche) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "Tranche", path, DEFINITION);
		}
	}
}
