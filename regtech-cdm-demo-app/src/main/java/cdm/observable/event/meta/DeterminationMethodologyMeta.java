package cdm.observable.event.meta;

import cdm.observable.event.DeterminationMethodology;
import cdm.observable.event.validation.DeterminationMethodologyTypeFormatValidator;
import cdm.observable.event.validation.DeterminationMethodologyValidator;
import cdm.observable.event.validation.exists.DeterminationMethodologyOnlyExistsValidator;
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
@RosettaMeta(model=DeterminationMethodology.class)
public class DeterminationMethodologyMeta implements RosettaMetaData<DeterminationMethodology> {

	@Override
	public List<Validator<? super DeterminationMethodology>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DeterminationMethodology, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DeterminationMethodology> validator() {
		return new DeterminationMethodologyValidator();
	}

	@Override
	public Validator<? super DeterminationMethodology> typeFormatValidator() {
		return new DeterminationMethodologyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DeterminationMethodology, Set<String>> onlyExistsValidator() {
		return new DeterminationMethodologyOnlyExistsValidator();
	}
}
