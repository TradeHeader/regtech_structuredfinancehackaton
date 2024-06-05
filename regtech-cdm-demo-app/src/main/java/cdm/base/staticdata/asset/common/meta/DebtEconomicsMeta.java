package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.DebtEconomics;
import cdm.base.staticdata.asset.common.validation.DebtEconomicsTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.DebtEconomicsValidator;
import cdm.base.staticdata.asset.common.validation.exists.DebtEconomicsOnlyExistsValidator;
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
@RosettaMeta(model=DebtEconomics.class)
public class DebtEconomicsMeta implements RosettaMetaData<DebtEconomics> {

	@Override
	public List<Validator<? super DebtEconomics>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DebtEconomics, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DebtEconomics> validator() {
		return new DebtEconomicsValidator();
	}

	@Override
	public Validator<? super DebtEconomics> typeFormatValidator() {
		return new DebtEconomicsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DebtEconomics, Set<String>> onlyExistsValidator() {
		return new DebtEconomicsOnlyExistsValidator();
	}
}
