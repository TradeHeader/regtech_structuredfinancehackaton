package cdm.product.template.validation.datarule;

import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.template.Basket;
import cdm.product.template.PerformancePayout;
import cdm.product.template.Product;
import cdm.product.template.ReturnTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.annotations.RosettaDataRule;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.mapper.MapperS;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.Validator;
import java.util.Arrays;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

/**
 * @version ${project.version}
 */
@RosettaDataRule("PerformancePayoutCorrelationUnderlierOnlyBasket")
@ImplementedBy(PerformancePayoutCorrelationUnderlierOnlyBasket.Default.class)
public interface PerformancePayoutCorrelationUnderlierOnlyBasket extends Validator<PerformancePayout> {
	
	String NAME = "PerformancePayoutCorrelationUnderlierOnlyBasket";
	String DEFINITION = "if returnTerms -> correlationReturnTerms exists then underlier -> basket only exists";
	
	ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout performancePayout);
	
	class Default implements PerformancePayoutCorrelationUnderlierOnlyBasket {
	
		@Override
		public ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout performancePayout) {
			ComparisonResult result = executeDataRule(performancePayout);
			if (result.get()) {
				return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PerformancePayout", path, DEFINITION);
			}
			
			String failureMessage = result.getError();
			if (failureMessage == null || failureMessage.contains("Null") || failureMessage == "") {
				failureMessage = "Condition has failed.";
			}
			return ValidationResult.failure(NAME, ValidationType.DATA_RULE, "PerformancePayout", path, DEFINITION, failureMessage);
		}
		
		private ComparisonResult executeDataRule(PerformancePayout performancePayout) {
			try {
				if (exists(MapperS.of(performancePayout).<ReturnTerms>map("getReturnTerms", _performancePayout -> _performancePayout.getReturnTerms()).<CorrelationReturnTerms>map("getCorrelationReturnTerms", returnTerms -> returnTerms.getCorrelationReturnTerms())).getOrDefault(false)) {
					return onlyExists(Arrays.asList(MapperS.of(performancePayout).<Product>map("getUnderlier", _performancePayout -> _performancePayout.getUnderlier()).<Basket>map("getBasket", product -> product.getBasket())));
				}
				return ComparisonResult.successEmptyOperand("");
			}
			catch (Exception ex) {
				return ComparisonResult.failure(ex.getMessage());
			}
		}
	}
	
	@SuppressWarnings("unused")
	class NoOp implements PerformancePayoutCorrelationUnderlierOnlyBasket {
	
		@Override
		public ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout performancePayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PerformancePayout", path, DEFINITION);
		}
	}
}
