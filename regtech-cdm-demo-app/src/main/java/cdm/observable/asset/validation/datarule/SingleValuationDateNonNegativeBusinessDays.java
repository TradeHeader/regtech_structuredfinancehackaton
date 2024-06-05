package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.SingleValuationDate;
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
@RosettaDataRule("SingleValuationDateNonNegativeBusinessDays")
@ImplementedBy(SingleValuationDateNonNegativeBusinessDays.Default.class)
public interface SingleValuationDateNonNegativeBusinessDays extends Validator<SingleValuationDate> {
	
	String NAME = "SingleValuationDateNonNegativeBusinessDays";
	String DEFINITION = "if businessDays exists then businessDays >= 0";
	
	ValidationResult<SingleValuationDate> validate(RosettaPath path, SingleValuationDate singleValuationDate);
	
	class Default implements SingleValuationDateNonNegativeBusinessDays {
	
		@Override
		public ValidationResult<SingleValuationDate> validate(RosettaPath path, SingleValuationDate singleValuationDate) {
			ComparisonResult result = executeDataRule(singleValuationDate);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SingleValuationDate", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "SingleValuationDate", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(SingleValuationDate singleValuationDate) {
			try {
				if (exists(MapperS.of(singleValuationDate).<Integer>map("getBusinessDays", _singleValuationDate -> _singleValuationDate.getBusinessDays())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(singleValuationDate).<Integer>map("getBusinessDays", _singleValuationDate -> _singleValuationDate.getBusinessDays()), MapperS.of(0), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements SingleValuationDateNonNegativeBusinessDays {
	
		@Override
		public ValidationResult<SingleValuationDate> validate(RosettaPath path, SingleValuationDate singleValuationDate) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "SingleValuationDate", path, DEFINITION);
		}
	}
}
