package cdm.observable.asset.meta;

import cdm.observable.asset.DividendApplicability;
import cdm.observable.asset.validation.DividendApplicabilityTypeFormatValidator;
import cdm.observable.asset.validation.DividendApplicabilityValidator;
import cdm.observable.asset.validation.exists.DividendApplicabilityOnlyExistsValidator;
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
@RosettaMeta(model=DividendApplicability.class)
public class DividendApplicabilityMeta implements RosettaMetaData<DividendApplicability> {

	@Override
	public List<Validator<? super DividendApplicability>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DividendApplicability, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DividendApplicability> validator() {
		return new DividendApplicabilityValidator();
	}

	@Override
	public Validator<? super DividendApplicability> typeFormatValidator() {
		return new DividendApplicabilityTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DividendApplicability, Set<String>> onlyExistsValidator() {
		return new DividendApplicabilityOnlyExistsValidator();
	}
}
