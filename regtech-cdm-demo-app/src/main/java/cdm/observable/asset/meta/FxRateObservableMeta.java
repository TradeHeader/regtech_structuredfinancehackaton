package cdm.observable.asset.meta;

import cdm.observable.asset.FxRateObservable;
import cdm.observable.asset.validation.FxRateObservableTypeFormatValidator;
import cdm.observable.asset.validation.FxRateObservableValidator;
import cdm.observable.asset.validation.exists.FxRateObservableOnlyExistsValidator;
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
@RosettaMeta(model=FxRateObservable.class)
public class FxRateObservableMeta implements RosettaMetaData<FxRateObservable> {

	@Override
	public List<Validator<? super FxRateObservable>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FxRateObservable, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FxRateObservable> validator() {
		return new FxRateObservableValidator();
	}

	@Override
	public Validator<? super FxRateObservable> typeFormatValidator() {
		return new FxRateObservableTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FxRateObservable, Set<String>> onlyExistsValidator() {
		return new FxRateObservableOnlyExistsValidator();
	}
}
