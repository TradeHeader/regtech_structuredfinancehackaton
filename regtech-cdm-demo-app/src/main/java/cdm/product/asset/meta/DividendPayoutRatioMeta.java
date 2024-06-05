package cdm.product.asset.meta;

import cdm.product.asset.DividendPayoutRatio;
import cdm.product.asset.validation.DividendPayoutRatioTypeFormatValidator;
import cdm.product.asset.validation.DividendPayoutRatioValidator;
import cdm.product.asset.validation.exists.DividendPayoutRatioOnlyExistsValidator;
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
@RosettaMeta(model=DividendPayoutRatio.class)
public class DividendPayoutRatioMeta implements RosettaMetaData<DividendPayoutRatio> {

	@Override
	public List<Validator<? super DividendPayoutRatio>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.DividendPayoutRatioDividendPayoutRatioTotal.class),
			factory.create(cdm.product.asset.validation.datarule.DividendPayoutRatioDividendPayoutRatioCash.class),
			factory.create(cdm.product.asset.validation.datarule.DividendPayoutRatioDividendPayoutRatioNonCash.class)
		);
	}
	
	@Override
	public List<Function<? super DividendPayoutRatio, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DividendPayoutRatio> validator() {
		return new DividendPayoutRatioValidator();
	}

	@Override
	public Validator<? super DividendPayoutRatio> typeFormatValidator() {
		return new DividendPayoutRatioTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DividendPayoutRatio, Set<String>> onlyExistsValidator() {
		return new DividendPayoutRatioOnlyExistsValidator();
	}
}
