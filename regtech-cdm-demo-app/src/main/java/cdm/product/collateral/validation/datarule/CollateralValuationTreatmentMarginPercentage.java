package cdm.product.collateral.validation.datarule;

import cdm.product.collateral.CollateralValuationTreatment;
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
@RosettaDataRule("CollateralValuationTreatmentMarginPercentage")
@ImplementedBy(CollateralValuationTreatmentMarginPercentage.Default.class)
public interface CollateralValuationTreatmentMarginPercentage extends Validator<CollateralValuationTreatment> {
	
	String NAME = "CollateralValuationTreatmentMarginPercentage";
	String DEFINITION = "if marginPercentage exists then marginPercentage >= 1";
	
	ValidationResult<CollateralValuationTreatment> validate(RosettaPath path, CollateralValuationTreatment collateralValuationTreatment);
	
	class Default implements CollateralValuationTreatmentMarginPercentage {
	
		@Override
		public ValidationResult<CollateralValuationTreatment> validate(RosettaPath path, CollateralValuationTreatment collateralValuationTreatment) {
			ComparisonResult result = executeDataRule(collateralValuationTreatment);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralValuationTreatment", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "CollateralValuationTreatment", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(CollateralValuationTreatment collateralValuationTreatment) {
			try {
				if (exists(MapperS.of(collateralValuationTreatment).<BigDecimal>map("getMarginPercentage", _collateralValuationTreatment -> _collateralValuationTreatment.getMarginPercentage())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(collateralValuationTreatment).<BigDecimal>map("getMarginPercentage", _collateralValuationTreatment -> _collateralValuationTreatment.getMarginPercentage()), MapperS.of(BigDecimal.valueOf(1)), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements CollateralValuationTreatmentMarginPercentage {
	
		@Override
		public ValidationResult<CollateralValuationTreatment> validate(RosettaPath path, CollateralValuationTreatment collateralValuationTreatment) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "CollateralValuationTreatment", path, DEFINITION);
		}
	}
}
