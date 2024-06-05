package cdm.regulation.meta;

import cdm.regulation.UndrlygInstrm;
import cdm.regulation.validation.UndrlygInstrmTypeFormatValidator;
import cdm.regulation.validation.UndrlygInstrmValidator;
import cdm.regulation.validation.exists.UndrlygInstrmOnlyExistsValidator;
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
@RosettaMeta(model=UndrlygInstrm.class)
public class UndrlygInstrmMeta implements RosettaMetaData<UndrlygInstrm> {

	@Override
	public List<Validator<? super UndrlygInstrm>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super UndrlygInstrm, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super UndrlygInstrm> validator() {
		return new UndrlygInstrmValidator();
	}

	@Override
	public Validator<? super UndrlygInstrm> typeFormatValidator() {
		return new UndrlygInstrmTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super UndrlygInstrm, Set<String>> onlyExistsValidator() {
		return new UndrlygInstrmOnlyExistsValidator();
	}
}
