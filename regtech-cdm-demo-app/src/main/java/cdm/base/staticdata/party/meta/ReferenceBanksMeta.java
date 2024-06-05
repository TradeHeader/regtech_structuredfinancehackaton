package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.ReferenceBanks;
import cdm.base.staticdata.party.validation.ReferenceBanksTypeFormatValidator;
import cdm.base.staticdata.party.validation.ReferenceBanksValidator;
import cdm.base.staticdata.party.validation.exists.ReferenceBanksOnlyExistsValidator;
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
@RosettaMeta(model=ReferenceBanks.class)
public class ReferenceBanksMeta implements RosettaMetaData<ReferenceBanks> {

	@Override
	public List<Validator<? super ReferenceBanks>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ReferenceBanks, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ReferenceBanks> validator() {
		return new ReferenceBanksValidator();
	}

	@Override
	public Validator<? super ReferenceBanks> typeFormatValidator() {
		return new ReferenceBanksTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ReferenceBanks, Set<String>> onlyExistsValidator() {
		return new ReferenceBanksOnlyExistsValidator();
	}
}
