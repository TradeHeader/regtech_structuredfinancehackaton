package cdm.event.position.meta;

import cdm.event.position.Portfolio;
import cdm.event.position.validation.PortfolioTypeFormatValidator;
import cdm.event.position.validation.PortfolioValidator;
import cdm.event.position.validation.exists.PortfolioOnlyExistsValidator;
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
@RosettaMeta(model=Portfolio.class)
public class PortfolioMeta implements RosettaMetaData<Portfolio> {

	@Override
	public List<Validator<? super Portfolio>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Portfolio, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Portfolio> validator() {
		return new PortfolioValidator();
	}

	@Override
	public Validator<? super Portfolio> typeFormatValidator() {
		return new PortfolioTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Portfolio, Set<String>> onlyExistsValidator() {
		return new PortfolioOnlyExistsValidator();
	}
}
