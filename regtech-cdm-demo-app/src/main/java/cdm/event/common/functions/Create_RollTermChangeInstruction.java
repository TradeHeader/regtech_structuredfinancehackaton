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


@ImplementedBy(Create_RollTermChangeInstruction.Create_RollTermChangeInstructionDefault.class)
public abstract class Create_RollTermChangeInstruction implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param contractualProduct The original contractual product to be rolled.
	* @param effectiveRollDate The date to close and open a new position.
	* @param terminationDate The new termination date.
	* @return termsChangeInstruction The relevant primitive instruction for the roll, which is a terms change.
	*/
	public TermsChangeInstruction evaluate(ContractualProduct contractualProduct, AdjustableOrRelativeDate effectiveRollDate, AdjustableOrRelativeDate terminationDate) {
		TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstructionBuilder = doEvaluate(contractualProduct, effectiveRollDate, terminationDate);
		
		final TermsChangeInstruction termsChangeInstruction;
		if (termsChangeInstructionBuilder == null) {
			termsChangeInstruction = null;
		} else {
			termsChangeInstruction = termsChangeInstructionBuilder.build();
			objectValidator.validate(TermsChangeInstruction.class, termsChangeInstruction);
		}
		
		return termsChangeInstruction;
	}

	protected abstract TermsChangeInstruction.TermsChangeInstructionBuilder doEvaluate(ContractualProduct contractualProduct, AdjustableOrRelativeDate effectiveRollDate, AdjustableOrRelativeDate terminationDate);

	public static class Create_RollTermChangeInstructionDefault extends Create_RollTermChangeInstruction {
		@Override
		protected TermsChangeInstruction.TermsChangeInstructionBuilder doEvaluate(ContractualProduct contractualProduct, AdjustableOrRelativeDate effectiveRollDate, AdjustableOrRelativeDate terminationDate) {
			TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstruction = TermsChangeInstruction.builder();
			return assignOutput(termsChangeInstruction, contractualProduct, effectiveRollDate, terminationDate);
		}
		
		protected TermsChangeInstruction.TermsChangeInstructionBuilder assignOutput(TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstruction, ContractualProduct contractualProduct, AdjustableOrRelativeDate effectiveRollDate, AdjustableOrRelativeDate terminationDate) {
			termsChangeInstruction
				.getOrCreateProduct()
				.setContractualProduct(contractualProduct);
			
			termsChangeInstruction
				.getOrCreateProduct()
				.getOrCreateContractualProduct()
				.getOrCreateEconomicTerms()
				.setEffectiveDate(effectiveRollDate);
			
			termsChangeInstruction
				.getOrCreateProduct()
				.getOrCreateContractualProduct()
				.getOrCreateEconomicTerms()
				.setTerminationDate(terminationDate);
			
			return Optional.ofNullable(termsChangeInstruction)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
