package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.Equity;
import cdm.base.staticdata.asset.common.validation.EquityTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.EquityValidator;
import cdm.base.staticdata.asset.common.validation.exists.EquityOnlyExistsValidator;
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
@RosettaMeta(model=Equity.class)
public class EquityMeta implements RosettaMetaData<Equity> {

	@Override
	public List<Validator<? super Equity>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Equity, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Equity> validator() {
		return new EquityValidator();
	}

	@Override
	public Validator<? super Equity> typeFormatValidator() {
		return new EquityTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Equity, Set<String>> onlyExistsValidator() {
		return new EquityOnlyExistsValidator();
	}
}
