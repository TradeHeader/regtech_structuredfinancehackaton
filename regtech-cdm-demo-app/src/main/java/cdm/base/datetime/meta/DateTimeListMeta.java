package cdm.base.datetime.meta;

import cdm.base.datetime.DateTimeList;
import cdm.base.datetime.validation.DateTimeListTypeFormatValidator;
import cdm.base.datetime.validation.DateTimeListValidator;
import cdm.base.datetime.validation.exists.DateTimeListOnlyExistsValidator;
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
@RosettaMeta(model=DateTimeList.class)
public class DateTimeListMeta implements RosettaMetaData<DateTimeList> {

	@Override
	public List<Validator<? super DateTimeList>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DateTimeList, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DateTimeList> validator() {
		return new DateTimeListValidator();
	}

	@Override
	public Validator<? super DateTimeList> typeFormatValidator() {
		return new DateTimeListTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DateTimeList, Set<String>> onlyExistsValidator() {
		return new DateTimeListOnlyExistsValidator();
	}
}
