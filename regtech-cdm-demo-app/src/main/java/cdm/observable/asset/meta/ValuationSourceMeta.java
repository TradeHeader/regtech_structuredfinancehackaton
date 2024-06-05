package cdm.observable.asset.meta;

import cdm.observable.asset.ValuationSource;
import cdm.observable.asset.validation.ValuationSourceTypeFormatValidator;
import cdm.observable.asset.validation.ValuationSourceValidator;
import cdm.observable.asset.validation.exists.ValuationSourceOnlyExistsValidator;
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
@RosettaMeta(model=ValuationSource.class)
public class ValuationSourceMeta implements RosettaMetaData<ValuationSource> {

	@Override
	public List<Validator<? super ValuationSource>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.ValuationSourceInformationSource.class)
		);
	}
	
	@Override
	public List<Function<? super ValuationSource, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ValuationSource> validator() {
		return new ValuationSourceValidator();
	}

	@Override
	public Validator<? super ValuationSource> typeFormatValidator() {
		return new ValuationSourceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ValuationSource, Set<String>> onlyExistsValidator() {
		return new ValuationSourceOnlyExistsValidator();
	}
}
