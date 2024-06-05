package cdm.product.asset.validation.datarule;

import cdm.observable.asset.Observable;
import cdm.product.asset.ValuationTerms;
import cdm.product.asset.VarianceReturnTerms;
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
@RosettaDataRule("VarianceReturnTermsReferenceContract")
@ImplementedBy(VarianceReturnTermsReferenceContract.Default.class)
public interface VarianceReturnTermsReferenceContract extends Validator<VarianceReturnTerms> {
	
	String NAME = "VarianceReturnTermsReferenceContract";
	String DEFINITION = "if valuationTerms -> futuresPriceValuation = True then exchangeTradedContractNearest exists";
	
	ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms);
	
	class Default implements VarianceReturnTermsReferenceContract {
	
		@Override
		public ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms) {
			ComparisonResult result = executeDataRule(varianceReturnTerms);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VarianceReturnTerms", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "VarianceReturnTerms", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(VarianceReturnTerms varianceReturnTerms) {
			try {
				if (areEqual(MapperS.of(varianceReturnTerms).<ValuationTerms>map("getValuationTerms", returnTermsBase -> returnTermsBase.getValuationTerms()).<Boolean>map("getFuturesPriceValuation", valuationTerms -> valuationTerms.getFuturesPriceValuation()), MapperS.of(true), CardinalityOperator.All).getOrDefault(false)) {
					return exists(MapperS.of(varianceReturnTerms).<Observable>map("getExchangeTradedContractNearest", _varianceReturnTerms -> _varianceReturnTerms.getExchangeTradedContractNearest()));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements VarianceReturnTermsReferenceContract {
	
		@Override
		public ValidationResult<VarianceReturnTerms> validate(RosettaPath path, VarianceReturnTerms varianceReturnTerms) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "VarianceReturnTerms", path, DEFINITION);
		}
	}
}
