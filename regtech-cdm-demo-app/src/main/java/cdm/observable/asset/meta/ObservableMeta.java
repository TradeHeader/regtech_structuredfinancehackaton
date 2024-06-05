package cdm.observable.asset.meta;

import cdm.observable.asset.Observable;
import cdm.observable.asset.validation.ObservableTypeFormatValidator;
import cdm.observable.asset.validation.ObservableValidator;
import cdm.observable.asset.validation.exists.ObservableOnlyExistsValidator;
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
@RosettaMeta(model=Observable.class)
public class ObservableMeta implements RosettaMetaData<Observable> {

	@Override
	public List<Validator<? super Observable>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.ObservableObservableChoice.class)
		);
	}
	
	@Override
	public List<Function<? super Observable, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Observable> validator() {
		return new ObservableValidator();
	}

	@Override
	public Validator<? super Observable> typeFormatValidator() {
		return new ObservableTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Observable, Set<String>> onlyExistsValidator() {
		return new ObservableOnlyExistsValidator();
	}
}
