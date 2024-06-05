package cdm.event.common.meta;

import cdm.event.common.Exposure;
import cdm.event.common.validation.ExposureTypeFormatValidator;
import cdm.event.common.validation.ExposureValidator;
import cdm.event.common.validation.exists.ExposureOnlyExistsValidator;
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
@RosettaMeta(model=Exposure.class)
public class ExposureMeta implements RosettaMetaData<Exposure> {

	@Override
	public List<Validator<? super Exposure>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Exposure, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Exposure> validator() {
		return new ExposureValidator();
	}

	@Override
	public Validator<? super Exposure> typeFormatValidator() {
		return new ExposureTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Exposure, Set<String>> onlyExistsValidator() {
		return new ExposureOnlyExistsValidator();
	}
}
