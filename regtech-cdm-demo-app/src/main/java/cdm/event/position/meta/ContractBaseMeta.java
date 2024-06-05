package cdm.event.position.meta;

import cdm.event.position.ContractBase;
import cdm.event.position.validation.ContractBaseTypeFormatValidator;
import cdm.event.position.validation.ContractBaseValidator;
import cdm.event.position.validation.exists.ContractBaseOnlyExistsValidator;
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
@RosettaMeta(model=ContractBase.class)
public class ContractBaseMeta implements RosettaMetaData<ContractBase> {

	@Override
	public List<Validator<? super ContractBase>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ContractBase, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ContractBase> validator() {
		return new ContractBaseValidator();
	}

	@Override
	public Validator<? super ContractBase> typeFormatValidator() {
		return new ContractBaseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ContractBase, Set<String>> onlyExistsValidator() {
		return new ContractBaseOnlyExistsValidator();
	}
}
