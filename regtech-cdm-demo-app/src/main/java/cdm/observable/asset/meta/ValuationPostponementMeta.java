package cdm.observable.asset.meta;

import cdm.observable.asset.ValuationPostponement;
import cdm.observable.asset.validation.ValuationPostponementTypeFormatValidator;
import cdm.observable.asset.validation.ValuationPostponementValidator;
import cdm.observable.asset.validation.exists.ValuationPostponementOnlyExistsValidator;
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
@RosettaMeta(model=ValuationPostponement.class)
public class ValuationPostponementMeta implements RosettaMetaData<ValuationPostponement> {

	@Override
	public List<Validator<? super ValuationPostponement>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ValuationPostponement, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ValuationPostponement> validator() {
		return new ValuationPostponementValidator();
	}

	@Override
	public Validator<? super ValuationPostponement> typeFormatValidator() {
		return new ValuationPostponementTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ValuationPostponement, Set<String>> onlyExistsValidator() {
		return new ValuationPostponementOnlyExistsValidator();
	}
}
