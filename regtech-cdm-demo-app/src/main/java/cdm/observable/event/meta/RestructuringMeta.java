package cdm.observable.event.meta;

import cdm.observable.event.Restructuring;
import cdm.observable.event.validation.RestructuringTypeFormatValidator;
import cdm.observable.event.validation.RestructuringValidator;
import cdm.observable.event.validation.exists.RestructuringOnlyExistsValidator;
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
@RosettaMeta(model=Restructuring.class)
public class RestructuringMeta implements RosettaMetaData<Restructuring> {

	@Override
	public List<Validator<? super Restructuring>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Restructuring, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Restructuring> validator() {
		return new RestructuringValidator();
	}

	@Override
	public Validator<? super Restructuring> typeFormatValidator() {
		return new RestructuringTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Restructuring, Set<String>> onlyExistsValidator() {
		return new RestructuringOnlyExistsValidator();
	}
}
