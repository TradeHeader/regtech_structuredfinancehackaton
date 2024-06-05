package cdm.product.collateral.functions;

import cdm.product.collateral.CheckEligibilityResult;
import cdm.product.collateral.CheckEligibilityResult.CheckEligibilityResultBuilder;
import cdm.product.collateral.EligibleCollateralSpecification;
import cdm.product.template.Product;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(CheckEligibilityForProduct.CheckEligibilityForProductDefault.class)
public abstract class CheckEligibilityForProduct implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param specifications Specifications that determine which collateral meets the eligibility and can be used/posted for delivery. For ICMA usecase - this is the basket(s). For ISDA usecase these are the Elegibility Schedule Lists.
	* @param product 
	* @return eligibilityResult 
	*/
	public CheckEligibilityResult evaluate(List<? extends EligibleCollateralSpecification> specifications, Product product) {
		CheckEligibilityResult.CheckEligibilityResultBuilder eligibilityResultBuilder = doEvaluate(specifications, product);
		
		final CheckEligibilityResult eligibilityResult;
		if (eligibilityResultBuilder == null) {
			eligibilityResult = null;
		} else {
			eligibilityResult = eligibilityResultBuilder.build();
			objectValidator.validate(CheckEligibilityResult.class, eligibilityResult);
		}
		
		return eligibilityResult;
	}

	protected abstract CheckEligibilityResult.CheckEligibilityResultBuilder doEvaluate(List<? extends EligibleCollateralSpecification> specifications, Product product);

	public static class CheckEligibilityForProductDefault extends CheckEligibilityForProduct {
		@Override
		protected CheckEligibilityResult.CheckEligibilityResultBuilder doEvaluate(List<? extends EligibleCollateralSpecification> specifications, Product product) {
			if (specifications == null) {
				specifications = Collections.emptyList();
			}
			CheckEligibilityResult.CheckEligibilityResultBuilder eligibilityResult = CheckEligibilityResult.builder();
			return assignOutput(eligibilityResult, specifications, product);
		}
		
		protected CheckEligibilityResult.CheckEligibilityResultBuilder assignOutput(CheckEligibilityResult.CheckEligibilityResultBuilder eligibilityResult, List<? extends EligibleCollateralSpecification> specifications, Product product) {
			return Optional.ofNullable(eligibilityResult)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
