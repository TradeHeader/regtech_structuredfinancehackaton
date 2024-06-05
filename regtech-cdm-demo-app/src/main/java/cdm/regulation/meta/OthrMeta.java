package cdm.regulation.meta;

import cdm.regulation.Othr;
import cdm.regulation.validation.OthrTypeFormatValidator;
import cdm.regulation.validation.OthrValidator;
import cdm.regulation.validation.exists.OthrOnlyExistsValidator;
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
@RosettaMeta(model=Othr.class)
public class OthrMeta implements RosettaMetaData<Othr> {

	@Override
	public List<Validator<? super Othr>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Othr, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Othr> validator() {
		return new OthrValidator();
	}

	@Override
	public Validator<? super Othr> typeFormatValidator() {
		return new OthrTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Othr, Set<String>> onlyExistsValidator() {
		return new OthrOnlyExistsValidator();
	}
}
