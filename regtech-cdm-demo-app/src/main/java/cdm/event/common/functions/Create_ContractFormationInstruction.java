package cdm.event.common.functions;

import cdm.event.common.ContractFormationInstruction;
import cdm.event.common.ContractFormationInstruction.ContractFormationInstructionBuilder;
import cdm.legaldocumentation.common.LegalAgreement;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.ConditionValidator;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.records.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(Create_ContractFormationInstruction.Create_ContractFormationInstructionDefault.class)
public abstract class Create_ContractFormationInstruction implements RosettaFunction {
	
	@Inject protected ConditionValidator conditionValidator;
	
	@Inject protected ModelObjectValidator objectValidator;

	/**
	* @param legalAgreement 
	* @return instruction 
	*/
	public ContractFormationInstruction evaluate(List<? extends LegalAgreement> legalAgreement) {
		// pre-conditions
		conditionValidator.validate(() -> {
			if (exists(MapperC.<LegalAgreement>of(legalAgreement)).getOrDefault(false)) {
				return exists(MapperC.<LegalAgreement>of(legalAgreement).<Date>map("getAgreementDate", legalAgreementBase -> legalAgreementBase.getAgreementDate()));
			}
			return ComparisonResult.successEmptyOperand("");
		},
			"The full formation of a contract can only be completed with executed legal agreements if any.");
		
		ContractFormationInstruction.ContractFormationInstructionBuilder instructionBuilder = doEvaluate(legalAgreement);
		
		final ContractFormationInstruction instruction;
		if (instructionBuilder == null) {
			instruction = null;
		} else {
			instruction = instructionBuilder.build();
			objectValidator.validate(ContractFormationInstruction.class, instruction);
		}
		
		return instruction;
	}

	protected abstract ContractFormationInstruction.ContractFormationInstructionBuilder doEvaluate(List<? extends LegalAgreement> legalAgreement);

	public static class Create_ContractFormationInstructionDefault extends Create_ContractFormationInstruction {
		@Override
		protected ContractFormationInstruction.ContractFormationInstructionBuilder doEvaluate(List<? extends LegalAgreement> legalAgreement) {
			if (legalAgreement == null) {
				legalAgreement = Collections.emptyList();
			}
			ContractFormationInstruction.ContractFormationInstructionBuilder instruction = ContractFormationInstruction.builder();
			return assignOutput(instruction, legalAgreement);
		}
		
		protected ContractFormationInstruction.ContractFormationInstructionBuilder assignOutput(ContractFormationInstruction.ContractFormationInstructionBuilder instruction, List<? extends LegalAgreement> legalAgreement) {
			instruction
				.addLegalAgreement(legalAgreement);
			
			return Optional.ofNullable(instruction)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
