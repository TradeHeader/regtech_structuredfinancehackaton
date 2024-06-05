package cdm.product.template.meta;

import cdm.product.template.DividendTerms;
import cdm.product.template.validation.DividendTermsTypeFormatValidator;
import cdm.product.template.validation.DividendTermsValidator;
import cdm.product.template.validation.exists.DividendTermsOnlyExistsValidator;
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
@RosettaMeta(model=DividendTerms.class)
public class DividendTermsMeta implements RosettaMetaData<DividendTerms> {

	@Override
	public List<Validator<? super DividendTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DividendTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DividendTerms> validator() {
		return new DividendTermsValidator();
	}

	@Override
	public Validator<? super DividendTerms> typeFormatValidator() {
		return new DividendTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DividendTerms, Set<String>> onlyExistsValidator() {
		return new DividendTermsOnlyExistsValidator();
	}
}
