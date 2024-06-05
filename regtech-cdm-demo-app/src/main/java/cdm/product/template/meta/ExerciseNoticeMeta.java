package cdm.product.template.meta;

import cdm.product.template.ExerciseNotice;
import cdm.product.template.validation.ExerciseNoticeTypeFormatValidator;
import cdm.product.template.validation.ExerciseNoticeValidator;
import cdm.product.template.validation.exists.ExerciseNoticeOnlyExistsValidator;
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
@RosettaMeta(model=ExerciseNotice.class)
public class ExerciseNoticeMeta implements RosettaMetaData<ExerciseNotice> {

	@Override
	public List<Validator<? super ExerciseNotice>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ExerciseNotice, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ExerciseNotice> validator() {
		return new ExerciseNoticeValidator();
	}

	@Override
	public Validator<? super ExerciseNotice> typeFormatValidator() {
		return new ExerciseNoticeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ExerciseNotice, Set<String>> onlyExistsValidator() {
		return new ExerciseNoticeOnlyExistsValidator();
	}
}
