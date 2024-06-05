package cdm.observable.event.meta;

import cdm.observable.event.FailureToPay;
import cdm.observable.event.validation.FailureToPayTypeFormatValidator;
import cdm.observable.event.validation.FailureToPayValidator;
import cdm.observable.event.validation.exists.FailureToPayOnlyExistsValidator;
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
@RosettaMeta(model=FailureToPay.class)
public class FailureToPayMeta implements RosettaMetaData<FailureToPay> {

	@Override
	public List<Validator<? super FailureToPay>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FailureToPay, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FailureToPay> validator() {
		return new FailureToPayValidator();
	}

	@Override
	public Validator<? super FailureToPay> typeFormatValidator() {
		return new FailureToPayTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FailureToPay, Set<String>> onlyExistsValidator() {
		return new FailureToPayOnlyExistsValidator();
	}
}
