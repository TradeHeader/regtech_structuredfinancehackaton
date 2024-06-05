package cdm.base.math.meta;

import cdm.base.math.Measure;
import cdm.base.math.validation.MeasureTypeFormatValidator;
import cdm.base.math.validation.MeasureValidator;
import cdm.base.math.validation.exists.MeasureOnlyExistsValidator;
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
@RosettaMeta(model=Measure.class)
public class MeasureMeta implements RosettaMetaData<Measure> {

	@Override
	public List<Validator<? super Measure>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.math.validation.datarule.MeasureValueExists.class)
		);
	}
	
	@Override
	public List<Function<? super Measure, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Measure> validator() {
		return new MeasureValidator();
	}

	@Override
	public Validator<? super Measure> typeFormatValidator() {
		return new MeasureTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Measure, Set<String>> onlyExistsValidator() {
		return new MeasureOnlyExistsValidator();
	}
}
