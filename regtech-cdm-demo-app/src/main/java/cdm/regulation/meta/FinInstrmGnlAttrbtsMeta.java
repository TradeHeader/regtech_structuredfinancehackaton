package cdm.regulation.meta;

import cdm.regulation.FinInstrmGnlAttrbts;
import cdm.regulation.validation.FinInstrmGnlAttrbtsTypeFormatValidator;
import cdm.regulation.validation.FinInstrmGnlAttrbtsValidator;
import cdm.regulation.validation.exists.FinInstrmGnlAttrbtsOnlyExistsValidator;
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
@RosettaMeta(model=FinInstrmGnlAttrbts.class)
public class FinInstrmGnlAttrbtsMeta implements RosettaMetaData<FinInstrmGnlAttrbts> {

	@Override
	public List<Validator<? super FinInstrmGnlAttrbts>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FinInstrmGnlAttrbts, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FinInstrmGnlAttrbts> validator() {
		return new FinInstrmGnlAttrbtsValidator();
	}

	@Override
	public Validator<? super FinInstrmGnlAttrbts> typeFormatValidator() {
		return new FinInstrmGnlAttrbtsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FinInstrmGnlAttrbts, Set<String>> onlyExistsValidator() {
		return new FinInstrmGnlAttrbtsOnlyExistsValidator();
	}
}
