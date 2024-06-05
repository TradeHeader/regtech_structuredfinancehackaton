package cdm.event.common.meta;

import cdm.event.common.CollateralBalance;
import cdm.event.common.validation.CollateralBalanceTypeFormatValidator;
import cdm.event.common.validation.CollateralBalanceValidator;
import cdm.event.common.validation.exists.CollateralBalanceOnlyExistsValidator;
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
@RosettaMeta(model=CollateralBalance.class)
public class CollateralBalanceMeta implements RosettaMetaData<CollateralBalance> {

	@Override
	public List<Validator<? super CollateralBalance>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CollateralBalance, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralBalance> validator() {
		return new CollateralBalanceValidator();
	}

	@Override
	public Validator<? super CollateralBalance> typeFormatValidator() {
		return new CollateralBalanceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralBalance, Set<String>> onlyExistsValidator() {
		return new CollateralBalanceOnlyExistsValidator();
	}
}
