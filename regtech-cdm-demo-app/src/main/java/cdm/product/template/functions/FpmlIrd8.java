package cdm.product.template.functions;

import cdm.base.staticdata.party.Account;
import cdm.product.template.TradableProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Collections;
import java.util.List;


@ImplementedBy(FpmlIrd8.FpmlIrd8Default.class)
public abstract class FpmlIrd8 implements RosettaFunction {

	/**
	* @param tradableProduct 
	* @param accounts 
	* @return success Validation result
	*/
	public Boolean evaluate(TradableProduct tradableProduct, List<? extends Account> accounts) {
		Boolean success = doEvaluate(tradableProduct, accounts);
		
		return success;
	}

	protected abstract Boolean doEvaluate(TradableProduct tradableProduct, List<? extends Account> accounts);

	public static class FpmlIrd8Default extends FpmlIrd8 {
		@Override
		protected Boolean doEvaluate(TradableProduct tradableProduct, List<? extends Account> accounts) {
			if (accounts == null) {
				accounts = Collections.emptyList();
			}
			Boolean success = null;
			return assignOutput(success, tradableProduct, accounts);
		}
		
		protected Boolean assignOutput(Boolean success, TradableProduct tradableProduct, List<? extends Account> accounts) {
			return success;
		}
	}
}
