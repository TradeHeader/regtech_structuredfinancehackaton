package cdm.observable.asset.meta;

import cdm.observable.asset.MultipleCreditNotations;
import cdm.observable.asset.validation.MultipleCreditNotationsTypeFormatValidator;
import cdm.observable.asset.validation.MultipleCreditNotationsValidator;
import cdm.observable.asset.validation.exists.MultipleCreditNotationsOnlyExistsValidator;
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
@RosettaMeta(model=MultipleCreditNotations.class)
public class MultipleCreditNotationsMeta implements RosettaMetaData<MultipleCreditNotations> {

	@Override
	public List<Validator<? super MultipleCreditNotations>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.MultipleCreditNotationsReferenceAgency.class)
		);
	}
	
	@Override
	public List<Function<? super MultipleCreditNotations, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MultipleCreditNotations> validator() {
		return new MultipleCreditNotationsValidator();
	}

	@Override
	public Validator<? super MultipleCreditNotations> typeFormatValidator() {
		return new MultipleCreditNotationsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MultipleCreditNotations, Set<String>> onlyExistsValidator() {
		return new MultipleCreditNotationsOnlyExistsValidator();
	}
}
