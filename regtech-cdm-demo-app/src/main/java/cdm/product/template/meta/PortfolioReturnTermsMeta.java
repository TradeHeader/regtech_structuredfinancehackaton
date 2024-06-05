package cdm.product.template.meta;

import cdm.product.template.PortfolioReturnTerms;
import cdm.product.template.validation.PortfolioReturnTermsTypeFormatValidator;
import cdm.product.template.validation.PortfolioReturnTermsValidator;
import cdm.product.template.validation.exists.PortfolioReturnTermsOnlyExistsValidator;
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
@RosettaMeta(model=PortfolioReturnTerms.class)
public class PortfolioReturnTermsMeta implements RosettaMetaData<PortfolioReturnTerms> {

	@Override
	public List<Validator<? super PortfolioReturnTerms>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.ReturnTermsReturnTermsExists.class)
		);
	}
	
	@Override
	public List<Function<? super PortfolioReturnTerms, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PortfolioReturnTerms> validator() {
		return new PortfolioReturnTermsValidator();
	}

	@Override
	public Validator<? super PortfolioReturnTerms> typeFormatValidator() {
		return new PortfolioReturnTermsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PortfolioReturnTerms, Set<String>> onlyExistsValidator() {
		return new PortfolioReturnTermsOnlyExistsValidator();
	}
}
