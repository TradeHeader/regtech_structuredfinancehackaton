package cdm.product.template.meta;

import cdm.product.template.EarlyTerminationProvision;
import cdm.product.template.validation.EarlyTerminationProvisionTypeFormatValidator;
import cdm.product.template.validation.EarlyTerminationProvisionValidator;
import cdm.product.template.validation.exists.EarlyTerminationProvisionOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=EarlyTerminationProvision.class)
public class EarlyTerminationProvisionMeta implements RosettaMetaData<EarlyTerminationProvision> {

	@Override
	public List<Validator<? super EarlyTerminationProvision>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.EarlyTerminationProvisionMandatoryEarlyTermination.class)
		);
	}
	
	@Override
	public List<Function<? super EarlyTerminationProvision, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EarlyTerminationProvision> validator() {
		return new EarlyTerminationProvisionValidator();
	}

	@Override
	public Validator<? super EarlyTerminationProvision> typeFormatValidator() {
		return new EarlyTerminationProvisionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EarlyTerminationProvision, Set<String>> onlyExistsValidator() {
		return new EarlyTerminationProvisionOnlyExistsValidator();
	}
}
