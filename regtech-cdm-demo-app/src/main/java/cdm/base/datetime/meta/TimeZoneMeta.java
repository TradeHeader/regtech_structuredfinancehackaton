package cdm.base.datetime.meta;

import cdm.base.datetime.TimeZone;
import cdm.base.datetime.validation.TimeZoneTypeFormatValidator;
import cdm.base.datetime.validation.TimeZoneValidator;
import cdm.base.datetime.validation.exists.TimeZoneOnlyExistsValidator;
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
@RosettaMeta(model=TimeZone.class)
public class TimeZoneMeta implements RosettaMetaData<TimeZone> {

	@Override
	public List<Validator<? super TimeZone>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TimeZone, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TimeZone> validator() {
		return new TimeZoneValidator();
	}

	@Override
	public Validator<? super TimeZone> typeFormatValidator() {
		return new TimeZoneTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TimeZone, Set<String>> onlyExistsValidator() {
		return new TimeZoneOnlyExistsValidator();
	}
}
