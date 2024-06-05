package cdm.observable.asset.meta;

import cdm.observable.asset.SingleValuationDate;
import cdm.observable.asset.validation.SingleValuationDateTypeFormatValidator;
import cdm.observable.asset.validation.SingleValuationDateValidator;
import cdm.observable.asset.validation.exists.SingleValuationDateOnlyExistsValidator;
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
@RosettaMeta(model=SingleValuationDate.class)
public class SingleValuationDateMeta implements RosettaMetaData<SingleValuationDate> {

	@Override
	public List<Validator<? super SingleValuationDate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.SingleValuationDateNonNegativeBusinessDays.class)
		);
	}
	
	@Override
	public List<Function<? super SingleValuationDate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SingleValuationDate> validator() {
		return new SingleValuationDateValidator();
	}

	@Override
	public Validator<? super SingleValuationDate> typeFormatValidator() {
		return new SingleValuationDateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SingleValuationDate, Set<String>> onlyExistsValidator() {
		return new SingleValuationDateOnlyExistsValidator();
	}
}
