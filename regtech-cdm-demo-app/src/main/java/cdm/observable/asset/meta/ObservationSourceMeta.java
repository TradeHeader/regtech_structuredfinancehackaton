package cdm.observable.asset.meta;

import cdm.observable.asset.ObservationSource;
import cdm.observable.asset.validation.ObservationSourceTypeFormatValidator;
import cdm.observable.asset.validation.ObservationSourceValidator;
import cdm.observable.asset.validation.exists.ObservationSourceOnlyExistsValidator;
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
@RosettaMeta(model=ObservationSource.class)
public class ObservationSourceMeta implements RosettaMetaData<ObservationSource> {

	@Override
	public List<Validator<? super ObservationSource>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.ObservationSourceCurveInformationSource.class)
		);
	}
	
	@Override
	public List<Function<? super ObservationSource, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ObservationSource> validator() {
		return new ObservationSourceValidator();
	}

	@Override
	public Validator<? super ObservationSource> typeFormatValidator() {
		return new ObservationSourceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ObservationSource, Set<String>> onlyExistsValidator() {
		return new ObservationSourceOnlyExistsValidator();
	}
}
