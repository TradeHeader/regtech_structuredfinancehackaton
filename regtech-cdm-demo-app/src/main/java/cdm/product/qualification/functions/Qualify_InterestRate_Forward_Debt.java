package cdm.product.qualification.functions;

import cdm.base.staticdata.asset.common.Security;
import cdm.product.template.EconomicTerms;
import cdm.product.template.ForwardPayout;
import cdm.product.template.Payout;
import cdm.product.template.Product;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.CardinalityOperator;
import com.rosetta.model.lib.functions.IQualifyFunctionExtension;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Arrays;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Qualify_InterestRate_Forward_Debt.Qualify_InterestRate_Forward_DebtDefault.class)
public abstract class Qualify_InterestRate_Forward_Debt implements RosettaFunction,IQualifyFunctionExtension<EconomicTerms> {
	
	// RosettaFunction dependencies
	//
	@Inject protected Qualify_AssetClass_InterestRate qualify_AssetClass_InterestRate;

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

	protected abstract MapperS<? extends ForwardPayout> forwardPayout(EconomicTerms economicTerms);

	public static class Qualify_InterestRate_Forward_DebtDefault extends Qualify_InterestRate_Forward_Debt {
		@Override
		protected Boolean doEvaluate(EconomicTerms economicTerms) {
			Boolean is_product = null;
			return assignOutput(is_product, economicTerms);
		}
		
		protected Boolean assignOutput(Boolean is_product, EconomicTerms economicTerms) {
			is_product = areEqual(MapperS.of(qualify_AssetClass_InterestRate.evaluate(economicTerms)), MapperS.of(true), CardinalityOperator.All).and(onlyExists(Arrays.asList(forwardPayout(economicTerms).<Product>map("getUnderlier", _forwardPayout -> _forwardPayout.getUnderlier()).<Security>map("getSecurity", product -> product.getSecurity())))).get();
			
			return is_product;
		}
		
		@Override
		protected MapperS<? extends ForwardPayout> forwardPayout(EconomicTerms economicTerms) {
			return MapperS.of(MapperS.of(economicTerms).<Payout>map("getPayout", _economicTerms -> _economicTerms.getPayout()).<ForwardPayout>mapC("getForwardPayout", payout -> payout.getForwardPayout()).get());
		}
	}
		
		@Override
		public String getNamePrefix() {
			return "Qualify";
		}
}
