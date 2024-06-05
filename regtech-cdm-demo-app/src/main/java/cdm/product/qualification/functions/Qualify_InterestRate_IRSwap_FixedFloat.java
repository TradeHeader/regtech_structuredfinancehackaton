package cdm.product.qualification.functions;

import cdm.product.template.EconomicTerms;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_InterestRate_IRSwap_FixedFloat.Qualify_InterestRate_IRSwap_FixedFloatDefault.class)
public abstract class Qualify_InterestRate_IRSwap_FixedFloat implements RosettaFunction,IQualifyFunctionExtension<EconomicTerms> {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_BaseProduct_IRSwap qualify_BaseProduct_IRSwap;
	@Inject protected Qualify_SubProduct_FixedFloat qualify_SubProduct_FixedFloat;
	@Inject protected Qualify_Transaction_OIS qualify_Transaction_OIS;
	@Inject protected Qualify_Transaction_ZeroCoupon qualify_Transaction_ZeroCoupon;

	/**
	* @param economicTerms 
	* @return is_product 
	*/
	@Override
	public Boolean evaluate(EconomicTerms economicTerms) {
		Boolean is_product = doEvaluate(economicTerms);
		
		return is_product;
	}

	protected abstract Boolean doEvaluate(EconomicTerms economicTerms);

	public static class Qualify_InterestRate_IRSwap_FixedFloatDefault extends Qualify_InterestRate_IRSwap_FixedFloat {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			is_product = areEqual(MapperS.of(qualify_BaseProduct_IRSwap.evaluate(economicTerms)), MapperS.of(true), CardinalityOperator.All).and(areEqual(MapperS.of(qualify_SubProduct_FixedFloat.evaluate(economicTerms)), MapperS.of(true), CardinalityOperator.All)).and(areEqual(MapperS.of(qualify_Transaction_ZeroCoupon.evaluate(economicTerms)), MapperS.of(false), CardinalityOperator.All)).and(areEqual(MapperS.of(qualify_Transaction_OIS.evaluate(economicTerms)), MapperS.of(false), CardinalityOperator.All)).get();
			
			return is_product;
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
