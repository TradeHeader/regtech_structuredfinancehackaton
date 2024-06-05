package cdm.product.asset.validation.datarule;

import cdm.product.asset.ValuationTerms;
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
@RosettaDataRule("ValuationTermsPositiveNumberOfValuationDates")
@ImplementedBy(ValuationTermsPositiveNumberOfValuationDates.Default.class)
public interface ValuationTermsPositiveNumberOfValuationDates extends Validator<ValuationTerms> {
	
	String NAME = "ValuationTermsPositiveNumberOfValuationDates";
	String DEFINITION = "if numberOfValuationDates exists then numberOfValuationDates > 0";
	
	ValidationResult<ValuationTerms> validate(RosettaPath path, ValuationTerms valuationTerms);
	
	class Default implements ValuationTermsPositiveNumberOfValuationDates {
	
		@Override
		public ValidationResult<ValuationTerms> validate(RosettaPath path, ValuationTerms valuationTerms) {
			ComparisonResult result = executeDataRule(valuationTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ValuationTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "ValuationTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(ValuationTerms valuationTerms) {
			try {
				if (exists(MapperS.of(valuationTerms).<Integer>map("getNumberOfValuationDates", _valuationTerms -> _valuationTerms.getNumberOfValuationDates())).getOrDefault(false)) {
					return greaterThan(MapperS.of(valuationTerms).<Integer>map("getNumberOfValuationDates", _valuationTerms -> _valuationTerms.getNumberOfValuationDates()), MapperS.of(0), CardinalityOperator.All);
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements ValuationTermsPositiveNumberOfValuationDates {
	
		@Override
		public ValidationResult<ValuationTerms> validate(RosettaPath path, ValuationTerms valuationTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "ValuationTerms", path, DEFINITION);
		}
	}
}
