package cdm.observable.asset.validation.datarule;

import cdm.observable.asset.MultipleValuationDates;
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
@RosettaDataRule("MultipleValuationDatesNumberValuationDates")
@ImplementedBy(MultipleValuationDatesNumberValuationDates.Default.class)
public interface MultipleValuationDatesNumberValuationDates extends Validator<MultipleValuationDates> {
	
	String NAME = "MultipleValuationDatesNumberValuationDates";
	String DEFINITION = "if numberValuationDates exists then numberValuationDates >= 0";
	
	ValidationResult<MultipleValuationDates> validate(RosettaPath path, MultipleValuationDates multipleValuationDates);
	
	class Default implements MultipleValuationDatesNumberValuationDates {
	
		@Override
		public ValidationResult<MultipleValuationDates> validate(RosettaPath path, MultipleValuationDates multipleValuationDates) {
			ComparisonResult result = executeDataRule(multipleValuationDates);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MultipleValuationDates", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "MultipleValuationDates", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(MultipleValuationDates multipleValuationDates) {
			try {
				if (exists(MapperS.of(multipleValuationDates).<Integer>map("getNumberValuationDates", _multipleValuationDates -> _multipleValuationDates.getNumberValuationDates())).getOrDefault(false)) {
					return greaterThanEquals(MapperS.of(multipleValuationDates).<Integer>map("getNumberValuationDates", _multipleValuationDates -> _multipleValuationDates.getNumberValuationDates()), MapperS.of(0), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements MultipleValuationDatesNumberValuationDates {
	
		@Override
		public ValidationResult<MultipleValuationDates> validate(RosettaPath path, MultipleValuationDates multipleValuationDates) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "MultipleValuationDates", path, DEFINITION);
		}
	}
}
