package cdm.base.math.meta;

import cdm.base.math.MeasureBase;
import cdm.base.math.validation.MeasureBaseTypeFormatValidator;
import cdm.base.math.validation.MeasureBaseValidator;
import cdm.base.math.validation.exists.MeasureBaseOnlyExistsValidator;
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
@RosettaMeta(model=MeasureBase.class)
public class MeasureBaseMeta implements RosettaMetaData<MeasureBase> {

	@Override
	public List<Validator<? super MeasureBase>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super MeasureBase, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MeasureBase> validator() {
		return new MeasureBaseValidator();
	}

	@Override
	public Validator<? super MeasureBase> typeFormatValidator() {
		return new MeasureBaseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MeasureBase, Set<String>> onlyExistsValidator() {
		return new MeasureBaseOnlyExistsValidator();
	}
}
