package cdm.product.asset.meta;

import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.asset.validation.CorrelationReturnTermsTypeFormatValidator;
import cdm.product.asset.validation.CorrelationReturnTermsValidator;
import cdm.product.asset.validation.exists.CorrelationReturnTermsOnlyExistsValidator;
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
@RosettaMeta(model=CorrelationReturnTerms.class)
public class CorrelationReturnTermsMeta implements RosettaMetaData<CorrelationReturnTerms> {

	@Override
	public List<Validator<? super CorrelationReturnTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.CorrelationReturnTermsPositiveNumberOfDataSeries.class),
			factory.create(cdm.product.asset.validation.datarule.CorrelationReturnTermsCorrelationValue.class),
			factory.create(cdm.product.asset.validation.datarule.ReturnTermsBaseInitialLevelOrInitialLevelSource.class),
			factory.create(cdm.product.asset.validation.datarule.ReturnTermsBasePositiveExpectedN.class)
		);
	}
	
	@Override
	public List<Function<? super CorrelationReturnTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CorrelationReturnTerms> validator() {
		return new CorrelationReturnTermsValidator();
	}

	@Override
	public Validator<? super CorrelationReturnTerms> typeFormatValidator() {
		return new CorrelationReturnTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CorrelationReturnTerms, Set<String>> onlyExistsValidator() {
		return new CorrelationReturnTermsOnlyExistsValidator();
	}
}
