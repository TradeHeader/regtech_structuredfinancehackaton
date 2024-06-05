package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.Security;
import cdm.base.staticdata.asset.common.validation.SecurityTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.SecurityValidator;
import cdm.base.staticdata.asset.common.validation.exists.SecurityOnlyExistsValidator;
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
@RosettaMeta(model=Security.class)
public class SecurityMeta implements RosettaMetaData<Security> {

	@Override
	public List<Validator<? super Security>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.SecurityMortgageSector.class),
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.SecurityDebtSubType.class),
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.SecurityEquitySubType.class),
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.SecurityFundSubType.class),
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.SecurityBondEconomicTerms.class)
		);
	}
	
	@Override
	public List<Function<? super Security, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Security> validator() {
		return new SecurityValidator();
	}

	@Override
	public Validator<? super Security> typeFormatValidator() {
		return new SecurityTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Security, Set<String>> onlyExistsValidator() {
		return new SecurityOnlyExistsValidator();
	}
}
