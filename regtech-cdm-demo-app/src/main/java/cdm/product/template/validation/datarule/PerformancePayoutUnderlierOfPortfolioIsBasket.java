package cdm.product.template.validation.datarule;

import cdm.product.asset.PriceReturnTerms;
import cdm.product.template.Basket;
import cdm.product.template.PerformancePayout;
import cdm.product.template.PortfolioReturnTerms;
import cdm.product.template.Product;
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
@RosettaDataRule("PerformancePayoutUnderlierOfPortfolioIsBasket")
@ImplementedBy(PerformancePayoutUnderlierOfPortfolioIsBasket.Default.class)
public interface PerformancePayoutUnderlierOfPortfolioIsBasket extends Validator<PerformancePayout> {
	
	String NAME = "PerformancePayoutUnderlierOfPortfolioIsBasket";
	String DEFINITION = "if portfolioReturnTerms -> priceReturnTerms exists then underlier -> basket only exists";
	
	ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout performancePayout);
	
	class Default implements PerformancePayoutUnderlierOfPortfolioIsBasket {
	
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
				if (exists(MapperS.of(performancePayout).<PortfolioReturnTerms>mapC("getPortfolioReturnTerms", _performancePayout -> _performancePayout.getPortfolioReturnTerms()).<PriceReturnTerms>map("getPriceReturnTerms", returnTerms -> returnTerms.getPriceReturnTerms())).getOrDefault(false)) {
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
	class NoOp implements PerformancePayoutUnderlierOfPortfolioIsBasket {
	
		@Override
		public ValidationResult<PerformancePayout> validate(RosettaPath path, PerformancePayout performancePayout) {
			return ValidationResult.success(NAME, ValidationResult.ValidationType.DATA_RULE, "PerformancePayout", path, DEFINITION);
		}
	}
}
