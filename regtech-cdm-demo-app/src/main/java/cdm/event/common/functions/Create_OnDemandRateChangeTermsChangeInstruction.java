package cdm.event.common.functions;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.event.common.TermsChangeInstruction;
import cdm.event.common.TermsChangeInstruction.TermsChangeInstructionBuilder;
import cdm.product.template.ContractualProduct;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_OnDemandRateChangeTermsChangeInstruction.Create_OnDemandRateChangeTermsChangeInstructionDefault.class)
public abstract class Create_OnDemandRateChangeTermsChangeInstruction implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param contractualProduct The original contractual product whose rate is changed.
	* @param effectiveDate The date to open the new position.
	* @return termsChangeInstruction 
	*/
	public TermsChangeInstruction evaluate(ContractualProduct contractualProduct, AdjustableOrRelativeDate effectiveDate) {
		TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstructionBuilder = doEvaluate(contractualProduct, effectiveDate);
		
		final TermsChangeInstruction termsChangeInstruction;
		if (termsChangeInstructionBuilder == null) {
			termsChangeInstruction = null;
		} else {
			termsChangeInstruction = termsChangeInstructionBuilder.build();
			objectValidator.validate(TermsChangeInstruction.class, termsChangeInstruction);
		}
		
		return termsChangeInstruction;
	}

	protected abstract TermsChangeInstruction.TermsChangeInstructionBuilder doEvaluate(ContractualProduct contractualProduct, AdjustableOrRelativeDate effectiveDate);

	public static class Create_OnDemandRateChangeTermsChangeInstructionDefault extends Create_OnDemandRateChangeTermsChangeInstruction {
		@Override
		protected TermsChangeInstruction.TermsChangeInstructionBuilder doEvaluate(ContractualProduct contractualProduct, AdjustableOrRelativeDate effectiveDate) {
			TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstruction = TermsChangeInstruction.builder();
			return assignOutput(termsChangeInstruction, contractualProduct, effectiveDate);
		}
		
		protected TermsChangeInstruction.TermsChangeInstructionBuilder assignOutput(TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstruction, ContractualProduct contractualProduct, AdjustableOrRelativeDate effectiveDate) {
			termsChangeInstruction
				.getOrCreateProduct()
				.setContractualProduct(contractualProduct);
			
			termsChangeInstruction
				.getOrCreateProduct()
				.getOrCreateContractualProduct()
				.getOrCreateEconomicTerms()
				.setEffectiveDate(effectiveDate);
			
			return Optional.ofNullable(termsChangeInstruction)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
