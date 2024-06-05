package cdm.product.collateral.functions;

import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(MergeEligibleCollateralCriteria.MergeEligibleCollateralCriteriaDefault.class)
public abstract class MergeEligibleCollateralCriteria implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param criteria1 
	* @param criteria2 
	* @return criteria 
	*/
	public EligibleCollateralCriteria evaluate(EligibleCollateralCriteria criteria1, EligibleCollateralCriteria criteria2) {
		EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder criteriaBuilder = doEvaluate(criteria1, criteria2);
		
		final EligibleCollateralCriteria criteria;
		if (criteriaBuilder == null) {
			criteria = null;
		} else {
			criteria = criteriaBuilder.build();
			objectValidator.validate(EligibleCollateralCriteria.class, criteria);
		}
		
		return criteria;
	}

	protected abstract EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder doEvaluate(EligibleCollateralCriteria criteria1, EligibleCollateralCriteria criteria2);

	public static class MergeEligibleCollateralCriteriaDefault extends MergeEligibleCollateralCriteria {
		@Override
		protected EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder doEvaluate(EligibleCollateralCriteria criteria1, EligibleCollateralCriteria criteria2) {
			EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder criteria = EligibleCollateralCriteria.builder();
			return assignOutput(criteria, criteria1, criteria2);
		}
		
		protected EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder assignOutput(EligibleCollateralCriteria.EligibleCollateralCriteriaBuilder criteria, EligibleCollateralCriteria criteria1, EligibleCollateralCriteria criteria2) {
			return Optional.ofNullable(criteria)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
