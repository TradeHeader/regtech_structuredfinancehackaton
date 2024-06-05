package cdm.product.asset.meta;

import cdm.product.asset.VolatilityReturnTerms;
import cdm.product.asset.validation.VolatilityReturnTermsTypeFormatValidator;
import cdm.product.asset.validation.VolatilityReturnTermsValidator;
import cdm.product.asset.validation.exists.VolatilityReturnTermsOnlyExistsValidator;
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
@RosettaMeta(model=VolatilityReturnTerms.class)
public class VolatilityReturnTermsMeta implements RosettaMetaData<VolatilityReturnTerms> {

	@Override
	public List<Validator<? super VolatilityReturnTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.VolatilityReturnTermsUnderlierMustBeSecurity.class),
			factory.create(cdm.product.asset.validation.datarule.ReturnTermsBaseInitialLevelOrInitialLevelSource.class),
			factory.create(cdm.product.asset.validation.datarule.ReturnTermsBasePositiveExpectedN.class)
		);
	}
	
	@Override
	public List<Function<? super VolatilityReturnTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super VolatilityReturnTerms> validator() {
		return new VolatilityReturnTermsValidator();
	}

	@Override
	public Validator<? super VolatilityReturnTerms> typeFormatValidator() {
		return new VolatilityReturnTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super VolatilityReturnTerms, Set<String>> onlyExistsValidator() {
		return new VolatilityReturnTermsOnlyExistsValidator();
	}
}
