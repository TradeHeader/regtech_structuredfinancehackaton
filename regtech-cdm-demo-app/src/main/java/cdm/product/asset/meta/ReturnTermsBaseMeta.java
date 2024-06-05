package cdm.product.asset.meta;

import cdm.product.asset.ReturnTermsBase;
import cdm.product.asset.validation.ReturnTermsBaseTypeFormatValidator;
import cdm.product.asset.validation.ReturnTermsBaseValidator;
import cdm.product.asset.validation.exists.ReturnTermsBaseOnlyExistsValidator;
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
@RosettaMeta(model=ReturnTermsBase.class)
public class ReturnTermsBaseMeta implements RosettaMetaData<ReturnTermsBase> {

	@Override
	public List<Validator<? super ReturnTermsBase>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.ReturnTermsBaseInitialLevelOrInitialLevelSource.class),
			factory.create(cdm.product.asset.validation.datarule.ReturnTermsBasePositiveExpectedN.class)
		);
	}
	
	@Override
	public List<Function<? super ReturnTermsBase, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ReturnTermsBase> validator() {
		return new ReturnTermsBaseValidator();
	}

	@Override
	public Validator<? super ReturnTermsBase> typeFormatValidator() {
		return new ReturnTermsBaseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ReturnTermsBase, Set<String>> onlyExistsValidator() {
		return new ReturnTermsBaseOnlyExistsValidator();
	}
}
