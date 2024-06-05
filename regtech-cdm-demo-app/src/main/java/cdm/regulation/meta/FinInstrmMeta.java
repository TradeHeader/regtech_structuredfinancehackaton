package cdm.regulation.meta;

import cdm.regulation.FinInstrm;
import cdm.regulation.validation.FinInstrmTypeFormatValidator;
import cdm.regulation.validation.FinInstrmValidator;
import cdm.regulation.validation.exists.FinInstrmOnlyExistsValidator;
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
@RosettaMeta(model=FinInstrm.class)
public class FinInstrmMeta implements RosettaMetaData<FinInstrm> {

	@Override
	public List<Validator<? super FinInstrm>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FinInstrm, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FinInstrm> validator() {
		return new FinInstrmValidator();
	}

	@Override
	public Validator<? super FinInstrm> typeFormatValidator() {
		return new FinInstrmTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FinInstrm, Set<String>> onlyExistsValidator() {
		return new FinInstrmOnlyExistsValidator();
	}
}
