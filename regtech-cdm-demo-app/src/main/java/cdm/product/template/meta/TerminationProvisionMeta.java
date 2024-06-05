package cdm.product.template.meta;

import cdm.product.template.TerminationProvision;
import cdm.product.template.validation.TerminationProvisionTypeFormatValidator;
import cdm.product.template.validation.TerminationProvisionValidator;
import cdm.product.template.validation.exists.TerminationProvisionOnlyExistsValidator;
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
@RosettaMeta(model=TerminationProvision.class)
public class TerminationProvisionMeta implements RosettaMetaData<TerminationProvision> {

	@Override
	public List<Validator<? super TerminationProvision>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.TerminationProvisionTerminationProvisionChoice.class)
		);
	}
	
	@Override
	public List<Function<? super TerminationProvision, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TerminationProvision> validator() {
		return new TerminationProvisionValidator();
	}

	@Override
	public Validator<? super TerminationProvision> typeFormatValidator() {
		return new TerminationProvisionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TerminationProvision, Set<String>> onlyExistsValidator() {
		return new TerminationProvisionOnlyExistsValidator();
	}
}
