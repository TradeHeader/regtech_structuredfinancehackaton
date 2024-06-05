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


@ImplementedBy(Create_CancellationTermChangeInstruction.Create_CancellationTermChangeInstructionDefault.class)
public abstract class Create_CancellationTermChangeInstruction implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param contractualProduct Contractual product of original trade
	* @param cancellationDate The new termination date.
	* @return termsChangeInstruction 
	*/
	public TermsChangeInstruction evaluate(ContractualProduct contractualProduct, AdjustableOrRelativeDate cancellationDate) {
		TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstructionBuilder = doEvaluate(contractualProduct, cancellationDate);
		
		final TermsChangeInstruction termsChangeInstruction;
		if (termsChangeInstructionBuilder == null) {
			termsChangeInstruction = null;
		} else {
			termsChangeInstruction = termsChangeInstructionBuilder.build();
			objectValidator.validate(TermsChangeInstruction.class, termsChangeInstruction);
		}
		
		return termsChangeInstruction;
	}

	protected abstract TermsChangeInstruction.TermsChangeInstructionBuilder doEvaluate(ContractualProduct contractualProduct, AdjustableOrRelativeDate cancellationDate);

	public static class Create_CancellationTermChangeInstructionDefault extends Create_CancellationTermChangeInstruction {
		@Override
		protected TermsChangeInstruction.TermsChangeInstructionBuilder doEvaluate(ContractualProduct contractualProduct, AdjustableOrRelativeDate cancellationDate) {
			TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstruction = TermsChangeInstruction.builder();
			return assignOutput(termsChangeInstruction, contractualProduct, cancellationDate);
		}
		
		protected TermsChangeInstruction.TermsChangeInstructionBuilder assignOutput(TermsChangeInstruction.TermsChangeInstructionBuilder termsChangeInstruction, ContractualProduct contractualProduct, AdjustableOrRelativeDate cancellationDate) {
			termsChangeInstruction
				.getOrCreateProduct()
				.setContractualProduct(contractualProduct);
			
			termsChangeInstruction
				.getOrCreateProduct()
				.getOrCreateContractualProduct()
				.getOrCreateEconomicTerms()
				.setTerminationDate(cancellationDate);
			
			return Optional.ofNullable(termsChangeInstruction)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
