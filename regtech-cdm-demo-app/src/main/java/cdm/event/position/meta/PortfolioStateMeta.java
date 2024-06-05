package cdm.event.position.meta;

import cdm.event.position.PortfolioState;
import cdm.event.position.validation.PortfolioStateTypeFormatValidator;
import cdm.event.position.validation.PortfolioStateValidator;
import cdm.event.position.validation.exists.PortfolioStateOnlyExistsValidator;
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
@RosettaMeta(model=PortfolioState.class)
public class PortfolioStateMeta implements RosettaMetaData<PortfolioState> {

	@Override
	public List<Validator<? super PortfolioState>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.position.validation.datarule.PortfolioStateInitialisation.class)
		);
	}
	
	@Override
	public List<Function<? super PortfolioState, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PortfolioState> validator() {
		return new PortfolioStateValidator();
	}

	@Override
	public Validator<? super PortfolioState> typeFormatValidator() {
		return new PortfolioStateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PortfolioState, Set<String>> onlyExistsValidator() {
		return new PortfolioStateOnlyExistsValidator();
	}
}
