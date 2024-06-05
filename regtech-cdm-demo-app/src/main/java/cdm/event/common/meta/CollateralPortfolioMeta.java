package cdm.event.common.meta;

import cdm.event.common.CollateralPortfolio;
import cdm.event.common.validation.CollateralPortfolioTypeFormatValidator;
import cdm.event.common.validation.CollateralPortfolioValidator;
import cdm.event.common.validation.exists.CollateralPortfolioOnlyExistsValidator;
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
@RosettaMeta(model=CollateralPortfolio.class)
public class CollateralPortfolioMeta implements RosettaMetaData<CollateralPortfolio> {

	@Override
	public List<Validator<? super CollateralPortfolio>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CollateralPortfolio, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralPortfolio> validator() {
		return new CollateralPortfolioValidator();
	}

	@Override
	public Validator<? super CollateralPortfolio> typeFormatValidator() {
		return new CollateralPortfolioTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralPortfolio, Set<String>> onlyExistsValidator() {
		return new CollateralPortfolioOnlyExistsValidator();
	}
}
