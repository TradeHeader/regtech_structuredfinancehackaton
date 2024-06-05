package cdm.product.collateral.functions;

import cdm.product.collateral.EligibleCollateralCriteria;
import cdm.product.collateral.EligibleCollateralSpecification;
import cdm.product.collateral.EligibleCollateralSpecification.EligibleCollateralSpecificationBuilder;
import cdm.product.collateral.EligibleCollateralSpecificationInstruction;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.functions.ModelObjectValidator;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Optional;
import javax.inject.Inject;


@ImplementedBy(Create_EligibleCollateralSpecificationFromInstruction.Create_EligibleCollateralSpecificationFromInstructionDefault.class)
public abstract class Create_EligibleCollateralSpecificationFromInstruction implements RosettaFunction {
	
	@Inject protected ModelObjectValidator objectValidator;
	
	// RosettaFunction dependencies
	//
	@Inject protected MergeEligibleCollateralCriteria mergeEligibleCollateralCriteria;

	/**
	* @param instruction 
	* @return specification 
	*/
	public EligibleCollateralSpecification evaluate(EligibleCollateralSpecificationInstruction instruction) {
		EligibleCollateralSpecification.EligibleCollateralSpecificationBuilder specificationBuilder = doEvaluate(instruction);
		
		final EligibleCollateralSpecification specification;
		if (specificationBuilder == null) {
			specification = null;
		} else {
			specification = specificationBuilder.build();
			objectValidator.validate(EligibleCollateralSpecification.class, specification);
		}
		
		return specification;
	}

	protected abstract EligibleCollateralSpecification.EligibleCollateralSpecificationBuilder doEvaluate(EligibleCollateralSpecificationInstruction instruction);

	public static class Create_EligibleCollateralSpecificationFromInstructionDefault extends Create_EligibleCollateralSpecificationFromInstruction {
		@Override
		protected EligibleCollateralSpecification.EligibleCollateralSpecificationBuilder doEvaluate(EligibleCollateralSpecificationInstruction instruction) {
			EligibleCollateralSpecification.EligibleCollateralSpecificationBuilder specification = EligibleCollateralSpecification.builder();
			return assignOutput(specification, instruction);
		}
		
		protected EligibleCollateralSpecification.EligibleCollateralSpecificationBuilder assignOutput(EligibleCollateralSpecification.EligibleCollateralSpecificationBuilder specification, EligibleCollateralSpecificationInstruction instruction) {
			specification
				.addCriteria(MapperS.of(instruction).<EligibleCollateralCriteria>mapC("getVariable", eligibleCollateralSpecificationInstruction -> eligibleCollateralSpecificationInstruction.getVariable())
					.mapItem(item -> MapperS.of(mergeEligibleCollateralCriteria.evaluate(item.get(), MapperS.of(instruction).<EligibleCollateralCriteria>map("getCommon", eligibleCollateralSpecificationInstruction -> eligibleCollateralSpecificationInstruction.getCommon()).get()))).getMulti());
			
			return Optional.ofNullable(specification)
				.map(o -> o.prune())
				.orElse(null);
		}
	}
}
