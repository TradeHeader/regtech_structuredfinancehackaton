package cdm.observable.asset.meta;

import cdm.observable.asset.FxRateSourceFixing;
import cdm.observable.asset.validation.FxRateSourceFixingTypeFormatValidator;
import cdm.observable.asset.validation.FxRateSourceFixingValidator;
import cdm.observable.asset.validation.exists.FxRateSourceFixingOnlyExistsValidator;
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
@RosettaMeta(model=FxRateSourceFixing.class)
public class FxRateSourceFixingMeta implements RosettaMetaData<FxRateSourceFixing> {

	@Override
	public List<Validator<? super FxRateSourceFixing>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FxRateSourceFixing, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FxRateSourceFixing> validator() {
		return new FxRateSourceFixingValidator();
	}

	@Override
	public Validator<? super FxRateSourceFixing> typeFormatValidator() {
		return new FxRateSourceFixingTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FxRateSourceFixing, Set<String>> onlyExistsValidator() {
		return new FxRateSourceFixingOnlyExistsValidator();
	}
}
