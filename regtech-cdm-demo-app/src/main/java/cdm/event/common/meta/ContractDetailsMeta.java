package cdm.event.common.meta;

import cdm.event.common.ContractDetails;
import cdm.event.common.validation.ContractDetailsTypeFormatValidator;
import cdm.event.common.validation.ContractDetailsValidator;
import cdm.event.common.validation.exists.ContractDetailsOnlyExistsValidator;
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
@RosettaMeta(model=ContractDetails.class)
public class ContractDetailsMeta implements RosettaMetaData<ContractDetails> {

	@Override
	public List<Validator<? super ContractDetails>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.ContractDetailsExecutedAgreement.class)
		);
	}
	
	@Override
	public List<Function<? super ContractDetails, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ContractDetails> validator() {
		return new ContractDetailsValidator();
	}

	@Override
	public Validator<? super ContractDetails> typeFormatValidator() {
		return new ContractDetailsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ContractDetails, Set<String>> onlyExistsValidator() {
		return new ContractDetailsOnlyExistsValidator();
	}
}
