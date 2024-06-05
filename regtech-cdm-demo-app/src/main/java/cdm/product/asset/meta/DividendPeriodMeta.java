package cdm.product.asset.meta;

import cdm.product.asset.DividendPeriod;
import cdm.product.asset.validation.DividendPeriodTypeFormatValidator;
import cdm.product.asset.validation.DividendPeriodValidator;
import cdm.product.asset.validation.exists.DividendPeriodOnlyExistsValidator;
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
@RosettaMeta(model=DividendPeriod.class)
public class DividendPeriodMeta implements RosettaMetaData<DividendPeriod> {

	@Override
	public List<Validator<? super DividendPeriod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DividendPeriod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DividendPeriod> validator() {
		return new DividendPeriodValidator();
	}

	@Override
	public Validator<? super DividendPeriod> typeFormatValidator() {
		return new DividendPeriodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DividendPeriod, Set<String>> onlyExistsValidator() {
		return new DividendPeriodOnlyExistsValidator();
	}
}
